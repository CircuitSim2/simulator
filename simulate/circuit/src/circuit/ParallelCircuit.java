package circuit;

import java.util.ArrayList;

public class ParallelCircuit extends Circuit
{
	private ArrayList<Double> currentListA;
	private ArrayList<Double> currentListB;
	private ArrayList<Double> voltageList;
	private double RSum[];
	private double LSum[];
	private double CInvSum[];
	private static final double dt = 0.01;
	
	public ParallelCircuit()
	{
		this.elem = new Element[9];
		for(int i = 0; i < 9; i++)
		{
			elem[i] = new Element(0, ElemType.LINE);
		}
		this.voltage = 0;
		
		currentListA = new ArrayList<Double>();
		currentListB = new ArrayList<Double>();
		RSum = new double[3];
		LSum = new double[3];
		CInvSum = new double[3];
	}

	public ParallelCircuit(Element elem[], double voltage)
	{
		this.elem = elem;
		this.voltage = voltage;
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
	private double form1FixedNum(double Ia, double Ib, double IaSum ,double IbSum)
	{
		//簡単のため代入
		double R1 = RSum[0];
		double R2 = RSum[1];
		double L1 = LSum[0];
		double L2 = LSum[1];
		double C1Inv = CInvSum[0];
		double C2Inv = CInvSum[1];
		
		return voltage + (L1 + L2) * Ia / dt - (C1Inv + C2Inv) * IaSum - L2 * Ib / dt + IbSum * C2Inv; 
	}
	
	//2式のIbの係数
	private double form2FixedNum(double Ia, double Ib, double IaSum ,double IbSum)
	{
		//簡単のため代入
		double R3 = RSum[2];
		double R2 = RSum[1];
		double L3 = LSum[2];
		double L2 = LSum[1];
		double C3Inv = CInvSum[2];
		double C2Inv = CInvSum[1];
		
		return - L2 * Ia / dt + IaSum * C2Inv + (L2 + L3) * Ib  / dt - (C2Inv + C3Inv) * IbSum;
	}
	
	//電流を計算してリストに格納
	public void calcCurrent(double start, double end)
	{
		double current = 0;			
		double currentSum = 0;
		int times = (int)((end - start) / dt);			
	}
}