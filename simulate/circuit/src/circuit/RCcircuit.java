package circuit;

public class RCcircuit
{
	public static void main(String args[])
	{
		double R = 1;
		double C = 1;
		double L = 1;
		double E = 10;
		double dt = 0.01;
		double t = 0;
		double i = 0;
		double iSum = 0;

		for(int j = 0;j < 200;j++)
		{
			t += dt;
			i = (E + (L * i / dt) - (iSum * C)) / (R + L / dt + dt * C);;
			iSum += i * dt;
			System.out.println(t + "," + i + ",");
		}
	}
}
