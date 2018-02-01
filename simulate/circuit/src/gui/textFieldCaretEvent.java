package gui;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import circuit.ParallelCircuit;
import circuit.SeriesCircuit;

public class textFieldCaretEvent implements CaretListener
{
	public MainDispApp disp;

	public textFieldCaretEvent(MainDispApp disp)
	{
		this.disp = disp;
	}

	public void caretUpdate(CaretEvent e)
	{
		if(disp.mainCircuit instanceof SeriesCircuit)
		{
			for(int i = 0;i < 6;i++)
			{
				if(e.getSource().equals(disp.textFieldElement[i]))
				{
					disp.selectedTextField = i;
				}

				try
				{
					disp.mainCircuit.getElem(i).setValue(Double.parseDouble(disp.textFieldElement[i].getText()));
				}
				catch(NumberFormatException ex)
				{
					disp.mainCircuit.getElem(i).setValue(0);
				}
			}
		}

		else if(disp.mainCircuit instanceof ParallelCircuit)
		{
			for(int i = 0;i < 9;i++)
			{
				if(e.getSource().equals(disp.textFieldElementParallel[i]))
				{
					disp.selectedTextField = i;
				}
				try
				{
					disp.mainCircuit.getElem(i).setValue(Double.parseDouble(disp.textFieldElementParallel[i].getText()));
				}
				catch(NumberFormatException ex)
				{
					disp.mainCircuit.getElem(i).setValue(0);
				}
			}
		}
	}
}
