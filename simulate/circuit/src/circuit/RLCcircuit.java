package circuit;

public class RLCcircuit
{
	public static void main(String[] args)
	{	
		double t = 0;
		int times = 0;
		
		SeriesCircuit circuit = new SeriesCircuit();
		circuit.setElem(0, new Element(1, ElemType.RESISTANCE));
		circuit.setElem(1, new Element(1, ElemType.INDUCTANCE));
		//circuit.setElem(2, new Element(1, ElemType.CAPACITANCE));
		circuit.setVoltage(10);
		
		circuit.calcCurrent(0, 10);
		//circuit.calcVoltage(0, 0, 10);
		
		for(double current : circuit.getCurrentList())
		{
			t = 0.01 * times;
			System.out.println(t + "," + current + ",");
			times++;
		}
	}

}
