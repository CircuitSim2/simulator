package circuit;

public class Circuit
{
	protected Element elem[]; //配置されている素子
	protected double voltage; //電源電圧
	protected static final double dt = 0.005;//微分,積分間隔

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
}
