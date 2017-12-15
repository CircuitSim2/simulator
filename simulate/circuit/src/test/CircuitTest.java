/*package test;

import java.io.FileWriter;
import java.io.PrintWriter;

import circuit.ElemType;
import circuit.ParallelCircuit;

public class CircuitTest {

	public static void main(String[] args)
	{
		ParallelCircuit circuit = new ParallelCircuit();
		circuit.setVoltage(10);

		for(int i = 0;i < 9;i+=3)
		{
			circuit.setElem(i, 1, ElemType.RESISTANCE);
			circuit.setElem(i + 1, 1, ElemType.CAPACITANCE);
			circuit.setElem(i + 2, 1, ElemType.INDUCTANCE);
		}

		circuit.calcCurrent(0, 10);

		try
		{
			FileWriter fw = new FileWriter("C:\\Users\\郁生\\Desktop\\kekka.csv");
			PrintWriter pw = new PrintWriter(fw);

			for(int i = 0; i < (10 / 0.005);i++)
			{
				pw.println(circuit.getCurrentListA().get(i) + "," + circuit.getCurrentListB().get(i) + ",");
			}

			pw.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
*/