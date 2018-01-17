package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import circuit.SeriesCircuit;

public class CalcEvent implements ActionListener
{
	private MainDispApp disp;

	public CalcEvent(MainDispApp disp)
	{
		this.disp = disp;
	}

	public void actionPerformed(ActionEvent e)
	{
		if(disp.mainCircuit instanceof SeriesCircuit)
		{
			for(int i = 0;i < 6;i++)
			{
				try{
					disp.mainCircuit.getElem(i).setValue(Double.parseDouble(disp.textFieldElement[i].getText()));
				}
				catch(Exception ex)
				{
					disp.mainCircuit.getElem(i).setValue(0);
				}
			}

			((SeriesCircuit) disp.mainCircuit).calcCurrent(0, 10);

	    	XYSeriesCollection data = new XYSeriesCollection();
	    	XYSeries series = new XYSeries("current");
	    	for(int i = 0; i < (10 / disp.mainCircuit.dt);i++)
	    	{
	    		series.add(i * disp.mainCircuit.dt ,((SeriesCircuit)disp.mainCircuit).getCurrentList().get(i));
	    	}

	    	data.addSeries(series);

			JFreeChart chart =
					ChartFactory.createXYLineChart(
				            null,                      // chart title
				            "Time[s]",                // x axis label
				            "Electric current(i)",    // y axis label
				            data,                  // data
				            PlotOrientation.VERTICAL,
				            true,                     // include legend
				            true,                     // tooltips
				            false                     // urls
				            );

			disp.panelGraph.setChart(chart);
		}
    }
}