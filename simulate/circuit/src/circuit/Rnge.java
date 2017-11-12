package circuit;

public class Rnge {

	public static void main(String[] args) 
	{
		double R = 1;
		double L = 1;
		double C = 1;
		double E = 10;
		double dt = 0.01;
		double i = 0;
		double p = E / L;
		double t = 0;
		
		double i1, i2, i3, i4, p1, p2, p3, p4;
		
		for(int j = 0;j < 1000; j++)
		{
			t = dt * j;
			
			i1 = dt * func1(R, L, C, E, i, p, t);
			p1 = dt * func2(R, L, C, E, i, p, t);
			
			i2 = dt * func1(R, L, C, E, i + i1 / 2, p + p1 / 2, t + dt / 2);
			p2 = dt * func2(R, L, C, E, i + i1 / 2, p + p1 / 2, t + dt / 2);
			
			i3 = dt * func1(R, L, C, E, i + i2 / 2, p + p2 / 2, t + dt / 2);
			p3 = dt * func2(R, L, C, E, i + i2 / 2, p + p2 / 2, t + dt / 2);
			
			i4 = dt * func1(R, L, C, E, i + i3 / 2, p + p3 / 2, t + dt / 2);
			p4 = dt * func2(R, L, C, E, i + i3 / 2, p + p3 / 2, t + dt / 2);
			
			i = i + (i1 + (2 * i2) + (2 * i3) + i4) / 6;
			p = p + (p1 + (2 * p2) + (2 * p3) + p4) / 6;
			
			System.out.println(t + "," + i + ",");
		}

	}
	
	public static double func1(double R, double L, double C, double E,double i, double p, double t)
	{
		return p;
	}
	
	public static double func2(double R, double L, double C, double E,double i, double p, double t)
	{
		return  - ((R / L) * p) - (i / (L * C));
	}

	
}