package circuit;

public class Circuit
{
	protected Element elem[]; //配置されている素子
	protected double voltage; //電源電圧
	public final double dt = 0.001;//微分,積分間隔
	protected double simulationEndTime;
	protected double switchOnTime;
	protected double switchOffTime;

	//*****セッター******//
	//電圧
	public void setVoltage(double voltage)
	{
		this.voltage = voltage;
	}
	//素子
	public void setElem(int num, double value, ElemType et)
	{
		this.elem[num] = new Element(value, et);
	}
	//素子
	public void setElem(int num, Element elem)
	{
		this.elem[num] = elem;
	}

	//シミュレーション終了時間
	public void setSimulationEndTime(double time)
	{
		this.simulationEndTime = time;
	}

	//スイッチオン時間
	public void setSwitchOnTime(double time)
	{
		this.switchOnTime = time;
	}

	//スイッチオフ時間
	public void setSwitchOffTime(double time)
	{
		this.switchOffTime = time;
	}

	//*****ゲッター****//
	//エレメント
	public Element getElem(int num)
	{
		return this.elem[num];
	}

	public Element [] getElem()
	{
		return this.elem;
	}

	//電圧
	public double getVoltage()
	{
		return this.voltage;
	}

	//シミュレーション終了時間
	public double getSimulationEndTime()
	{
		return this.simulationEndTime;
	}

	//スイッチオン時間
	public double getSwitchOnTime()
	{
		return this.switchOnTime;
	}

	//スイッチオフ時間
	public double getSwitchOffTime()
	{
		return this.switchOffTime;
	}
}
