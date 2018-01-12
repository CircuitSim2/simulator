//package test;
//
//import java.awt.BorderLayout;
//
//import javax.swing.JFrame;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.category.DefaultCategoryDataset;
//
//import circuit.ElemType;
//import circuit.ParallelCircuit;
//
//public class CircuitTest extends JFrame {
//
//	public static void main(String[] args)
//	{
//		 CircuitTest frame = new CircuitTest();
//
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setBounds(10, 10, 500, 500);
//		frame.setTitle("グラフサンプル");
//		frame.setVisible(true);
//
//	}
//
//	 CircuitTest(){
//		ParallelCircuit circuit = new ParallelCircuit();
//		circuit.setVoltage(10);
//
//		for(int i = 0;i < 9;i+=3)
//		{
//		 circuit.setElem(i, 1, ElemType.RESISTANCE);
//		 circuit.setElem(i + 1, 1, ElemType.CAPACITANCE);
//		 circuit.setElem(i + 2, 1, ElemType.INDUCTANCE);
//		}
//
//		circuit.calcCurrent(0, 10);
//
//
//    	DefaultCategoryDataset data = new DefaultCategoryDataset();
//    	for(int i = 0; i < (10 / 0.005);i++)
//    	{
//    		data.addValue(circuit.getCurrentListA().get(i), "current", Double.toString(i * 0.005));
//    	}
//
//
//		JFreeChart chart =
//		  ChartFactory.createLineChart("",
//		                               "",
//		                               "",
//		                               data,
//		                               PlotOrientation.VERTICAL,
//		                               true,
//		                               false,
//		                               false);
//
//		ChartPanel cpanel = new ChartPanel(chart);
//		getContentPane().add(cpanel, BorderLayout.CENTER);
//	 }
//
//}