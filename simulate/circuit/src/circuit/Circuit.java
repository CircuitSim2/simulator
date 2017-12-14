package circuit;

public class Circuit
{
	protected Element elem[]; //配置されている素子
	protected double voltage; //電源電圧
	protected static final double dt = 0.005;

	//セッター
	public void setVoltage(double voltage)
	{
		this.voltage = voltage;
	}

	public void setElem(int num, double value, ElemType et)
	{
		this.elem[num] = new Element(value, et);
	}

	public void setElem(int num, Element elem)
	{
		this.elem[num] = elem;
	}

	//ゲッター
	public Element getElem(int num)
	{
		return this.elem[num];
	}
}
