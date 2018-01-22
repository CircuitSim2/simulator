package circuit;

import java.util.ArrayList;

//回路の情報を格納するクラス(直列)

public class SeriesCircuit extends Circuit
{
	private double RSum;    //抵抗の値の合計
	private double LSum;    //インダクタンスの合計
	private double CinvSum; //キャパシタンスの逆数の合計
	private ArrayList<Double> currentList; //電流の値のList
	private ArrayList<Double> voltList;//電圧を格納するリスト

	//コンストラクタ
	public SeriesCircuit()
	{
		this.elem = new Element[6];
		for(int i = 0; i < 6; i++)
		{
			elem[i] = new Element(0, ElemType.LINE);
		}
		this.RSum = 0;
		this.LSum = 0;
		this.CinvSum = 0;
		this.voltage = 0;

		this.simulationEndTime = 10;
		this.switchOnTime = 0;
		this.switchOffTime = 5;
	}

	public SeriesCircuit(Element elem[], double voltage)
	{
		this.elem = elem;
		this.voltage = voltage;
		this.RSum = 0;
		this.LSum = 0;
		this.CinvSum = 0;
	}

	//セッター

	//ゲッター
	public ArrayList<Double> getCurrentList()
	{
		return this.currentList;
	}

	public ArrayList<Double> getVoltList()
	{
		return this.voltList;
	}

	//素子の値をまとめる
	public void elemSum()
	{
		RSum = 0;
		LSum = 0;
		CinvSum = 0;

		for(Element elem : elem)
		{
			switch(elem.getEt())
			{
				case RESISTANCE:
					RSum += elem.getValue();
					break;

				case CAPACITANCE:
					CinvSum += 1 / elem.getValue();
					break;

				case INDUCTANCE:
					LSum += elem.getValue();
					break;

				default :
			}
		}
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
			for(int i = 1;i < times;i++)
			{
				volt = elem[num].getValue() * currentList.get(i);
				voltList.add(volt);
			}
			break;

		case CAPACITANCE:
			for(int i = 1;i < times;i++)
			{
				volt = (currentList.get(i) * dt + currentSum);
				currentSum += (currentList.get(i) * dt);
				voltList.add(volt);
			}
			break;

		case INDUCTANCE:
			for(int i = 1;i < times;i++)
			{
				volt = elem[num].getValue() * (currentList.get(i) - currentList.get(i - 1)) / dt;
				voltList.add(volt);
			}
			break;

		default :
		}
	}

	//電流を計算してリストに格納
	public void calcCurrent()
	{
		double current = 0;
		double currentSum = 0;
		int times = (int)(simulationEndTime / dt);
		double volt = 0;
		
		currentList = new ArrayList<Double>();

		elemSum();

		currentList.add(0.0);

		for(int j = 0;j < times;j++)
		{
			if(j * dt >= switchOffTime)
				volt = 0;
			else if(j * dt >= switchOnTime)
				volt = voltage;
			
			current = (volt + LSum * current / dt - currentSum * CinvSum) / (RSum + LSum / dt + dt * CinvSum);
			currentList.add(current);
			currentSum += current * dt;
		}
	}
}
