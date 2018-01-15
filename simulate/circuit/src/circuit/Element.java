package circuit;

//素子クラス
public class Element
{
	private double value;//値
	private ElemType et;//種類

	//コンストラクタ
	public Element(double num, ElemType et)
	{
		this.value = num;
		this.et = et;
	}

	//*****ゲッター***//
	//値
	public double getValue()
	{
		return this.value;
	}
	//種類
	public ElemType getEt()
	{
		return this.et;
	}
}
