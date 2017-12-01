package circuit;

import java.util.ArrayList;

public class ParallelCircuit extends Circuit
{
	private ArrayList<Double> currentListA;
	private ArrayList<Double> currentListB;
	//private ArrayList<Double> voltageList;
	private double RSum[];
	private double LSum[];
	private double CInvSum[];

	public ParallelCircuit()
	{
		this.elem = new Element[9];
		for(int i = 0; i < 9; i++)
		{
			elem[i] = new Element(0, ElemType.LINE);
		}
		this.voltage = 0;

		//currentListA = new ArrayList<Double>();
		//currentListB = new ArrayList<Double>();
		RSum = new double[3];
		LSum = new double[3];
		CInvSum = new double[3];
	}

	public ParallelCircuit(Element elem[], double voltage)
	{
		this.elem = elem;
		this.voltage = voltage;
	}

	public ArrayList<Double> getCurrentListA()
	{
		return this.currentListA;
	}

	public ArrayList<Double> getCurrentListB()
	{
		return this.currentListB;
	}

	//各枝の素子を足し上げる 1~3 0除算を防ぐためキャパシタは逆数の合計
	private void calcBranchSum()
	{
		for(int i = 0; i < 3;i++)
		{
			for(int j = i;j < 3 * (i + 1);j++)
			{
				switch(elem[j].getEt())
				{
				case RESISTANCE:
					RSum[i] += elem[j].getValue();
					break;
				case INDUCTANCE:
					LSum[i] += elem[j].getValue();
					break;
				case CAPACITANCE:
					CInvSum[i] += 1 / elem[j].getValue();
					break;
				default:
				}
			}
		}
	}

	//1式のIaの係数
	private double form1IaCoeff()
	{
		//簡単のため代入
		double R1 = RSum[0];
		double R2 = RSum[1];
		double L1 = LSum[0];
		double L2 = LSum[1];
		double C1Inv = CInvSum[0];
		double C2Inv = CInvSum[1];

		return R1 + R2 + (L1 + L2) / dt + (C1Inv + C2Inv) * dt;
	}

	//1式のIbの係数
	private double form1IbCoeff()
	{
		//簡単のため代入
		double R2 = RSum[1];
		double L2 = LSum[1];
		double C2Inv = CInvSum[0];

		return -R2 - L2 / dt - C2Inv * dt;
	}

	//2式のIaの係数
	private double form2IaCoeff()
	{
		//簡単のため代入
		double R2 = RSum[1];
		double L2 = LSum[1];
		double C2Inv = CInvSum[0];

		return -R2 - L2 / dt - C2Inv * dt;
	}

	//2式のIbの係数
	private double form2IbCoeff()
	{
		//簡単のため代入
		double R3 = RSum[2];
		double R2 = RSum[1];
		double L3 = LSum[2];
		double L2 = LSum[1];
		double C3Inv = CInvSum[2];
		double C2Inv = CInvSum[1];

		return R3 + R2 + (L3 + L2) / dt + (C3Inv + C2Inv) * dt;
	}

	//1式の定数
	private double fixedNum1(double Ia, double Ib, double IaSum ,double IbSum)
	{
		//簡単のため代入
		double L1 = LSum[0];
		double L2 = LSum[1];
		double C1Inv = CInvSum[0];
		double C2Inv = CInvSum[1];

		return voltage + (L1 + L2) * Ia / dt - (C1Inv + C2Inv) * IaSum - L2 * Ib / dt + IbSum * C2Inv;
	}

	//2式のIbの係数
	private double fixedNum2(double Ia, double Ib, double IaSum ,double IbSum)
	{
		//簡単のため代入
		double L3 = LSum[2];
		double L2 = LSum[1];
		double C3Inv = CInvSum[2];
		double C2Inv = CInvSum[1];

		return - L2 * Ia / dt + IaSum * C2Inv + (L2 + L3) * Ib  / dt - (C2Inv + C3Inv) * IbSum;
	}

	//電流を計算してリストに格納
	public void calcCurrent(double start, double end)
	{
		double currentA = 0; //Iaを格納する変数
		double IaSum = 0;
		double currentB = 0; //Ibを格納する変数
		double IbSum = 0;
		double a1, a2, b1, b2, c1, c2;
		int times = (int)((end - start) / dt);

		currentListA = new ArrayList<Double>();
		currentListB = new ArrayList<Double>();

		calcBranchSum();

		//a1Ia + b1Ib = c1
		//a2Ia + b2Ib = c2

		for(int j = 0;j < times;j++)
		{
			a1 = form1IaCoeff();
			b1 = form1IbCoeff();
			a2 = form2IaCoeff();
			b2 = form2IbCoeff();
			c1 = fixedNum1(currentA, currentB, IaSum, IbSum);
			c2 = fixedNum2(currentA, currentB, IaSum, IbSum);

			currentA = (c1 / b1 - c2 / b2)/(a1 / b1 - a2 / b2);
			currentB = (c1 / a1 - c2 / a2)/(b1 / a1 - b2 / a2);

			IaSum += currentA * dt;
			IbSum += currentB * dt;

			currentListA.add(currentA);
			currentListB.add(currentB);
		}
	}
}