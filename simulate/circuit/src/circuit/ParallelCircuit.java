package circuit;

import java.util.ArrayList;

public class ParallelCircuit extends Circuit
{
	//電流を格納するリスト
	private ArrayList<Double> currentListA;
	private ArrayList<Double> currentListB;
	//電圧を格納するリスト
	private ArrayList<Double> voltList;
	//各素子の合計
	private double RSum[];
	private double LSum[];
	private double CInvSum[];
	
	private double volt;

	//コンストラクタ
	public ParallelCircuit()
	{
		this.elem = new Element[9];
		for(int i = 0; i < 9; i++)
		{
			elem[i] = new Element(0, ElemType.LINE);
		}
		this.voltage = 0;

		RSum = new double[3];
		LSum = new double[3];
		CInvSum = new double[3];

		this.simulationEndTime = 10;
		this.switchOnTime = 0;
		this.switchOffTime = 5;
		
		volt = 0;
	}

	public ParallelCircuit(Element elem[], double voltage)
	{
		this();

		this.elem = elem;
		this.voltage = voltage;
	}

	//*****ゲッター****//
	//Ia
	public ArrayList<Double> getCurrentListA()
	{
		return this.currentListA;
	}
	//Ib
	public ArrayList<Double> getCurrentListB()
	{
		return this.currentListB;
	}

	//電圧
	public ArrayList<Double> getVoltList()
	{
		return this.voltList;
	}

	//各枝の素子を足し上げる 1~3 0除算を防ぐためキャパシタは逆数の合計
	private void calcBranchSum()
	{
		for(int i = 0; i < 3;i++)
		{
			RSum[i] = 0;
			LSum[i] = 0;
			CInvSum[i] = 0;

			for(int j = i * 3;j < 3 * (i + 1);j++)
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
					CInvSum[i] += (1 / elem[j].getValue());
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
		double C2Inv = CInvSum[1];

		return -R2 - L2 / dt - C2Inv * dt;
	}

	//2式のIaの係数
	private double form2IaCoeff()
	{
		//簡単のため代入
		double R2 = RSum[1];
		double L2 = LSum[1];
		double C2Inv = CInvSum[1];

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

		return volt + (L1 + L2) * Ia / dt - (C1Inv + C2Inv) * IaSum - L2 * Ib / dt + IbSum * C2Inv;
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

	//電圧を計算してリストに格納
	public void calcVoltage(int num)
	{
		double volt;
		double currentSum = 0;
		int times = (int)(simulationEndTime / dt);

		voltList = new ArrayList<Double>();
		calcCurrent();

		voltList.add(0.0);

		switch(elem[num].getEt())
		{
		case RESISTANCE:
			if(num / 3 == 0)
			{
				for(int i = 1;i < times;i++)
				{
					volt = elem[num].getValue() * currentListA.get(i);
					voltList.add(volt);
				}
			}

			else if(num / 3 == 1)
			{
				for(int i = 1;i < times;i++)
				{
					volt = elem[num].getValue() * (currentListA.get(i) - currentListB.get(i));
					voltList.add(volt);
				}
			}

			else if(num /3 == 2)
			{
				for(int i = 1;i < times;i++)
				{
					volt = elem[num].getValue() * currentListB.get(i);
					voltList.add(volt);
				}
			}
			break;

		case CAPACITANCE:
			if(num / 3 == 0)
			{
				for(int i = 1;i < times;i++)
				{
					volt = (currentListA.get(i) * dt + currentSum);
					currentSum += (currentListA.get(i) * dt);
					voltList.add(volt);
				}
			}

			else if(num /3 == 1)
			{
				for(int i = 1;i < times;i++)
				{
					volt = (((currentListA.get(i) - currentListB.get(i)) * dt) + currentSum);
					currentSum += ((currentListA.get(i) - currentListB.get(i)) * dt);
					voltList.add(volt);
				}
			}

			else if(num / 3 == 2)
			{
				for(int i = 1;i < times;i++)
				{
					volt = (currentListB.get(i) * dt + currentSum);
					currentSum += (currentListB.get(i) * dt);
					voltList.add(volt);
				}
			}
			break;

		case INDUCTANCE:
			if(num / 3 == 0)
			{
				for(int i = 1;i < times;i++)
				{
					volt = elem[num].getValue() * (currentListA.get(i) - currentListA.get(i - 1)) / dt;
					voltList.add(volt);
				}
			}

			else if(num / 3 == 1)
			{
				for(int i = 1;i < times;i++)
				{
					volt = elem[num].getValue() * ((currentListA.get(i) - currentListB.get(i)) - (currentListA.get(i - 1) - currentListB.get(i - 1))) / dt;
					voltList.add(volt);
				}
			}

			else if(num / 3 == 2)
			{
				for(int i = 1;i < times;i++)
				{
					volt = elem[num].getValue() * (currentListB.get(i) - currentListB.get(i - 1)) / dt;
					voltList.add(volt);
				}
			}
			break;

		default :
		}
	}

	//電流を計算してリストに格納
	public void calcCurrent()
	{
		double currentA = 0; //Iaを格納する変数
		double IaSum = 0;
		double currentB = 0; //Ibを格納する変数
		double IbSum = 0;
		double a1, a2, b1, b2, c1, c2;//係数，定数を格納する変数
		int times = (int)(simulationEndTime / dt);

		//電流をリセット
		currentListA = new ArrayList<Double>();
		currentListB = new ArrayList<Double>();

		//素子の値を足し上げる
		calcBranchSum();

		//a1Ia + b1Ib = c1
		//a2Ia + b2Ib = c2
		currentListA.add(0.0);
		currentListB.add(0.0);

		for(int j = 0;j < times;j++)
		{
			if(j * dt >= switchOffTime)
				volt = 0;
			else if(j * dt >= switchOnTime)
				volt = voltage;
			
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