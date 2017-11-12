package circuit;

public class Element
{
	private double value;
	private ElemType et;

	public Element(double num, ElemType et)
	{
		this.value = num;
		this.et = et;
	}

	public double getValue()
	{
		return this.value;
	}

	public ElemType getEt()
	{
		return this.et;
	}
}
