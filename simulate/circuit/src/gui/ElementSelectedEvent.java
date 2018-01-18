package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

public class ElementSelectedEvent extends MouseAdapter
{
	private MainDispApp disp;
	
	public ElementSelectedEvent(MainDispApp disp)
	{
		this.disp = disp;
	}
	
	public void mouseClicked(MouseEvent e)
	{
		for(int i = 0;i < disp.mainCircuit.getElem().length;i++)
		{
			if(e.getComponent() == disp.labelElement[i])
			{
				disp.labelElement[i].setBorder(new LineBorder(new Color(0, 0, 0), 2));
			}
				
			else if(e.getComponent() == disp.labelElementParallel[i])
			{
				disp.labelElementParallel[i].setBorder(new LineBorder(new Color(0, 0, 0), 2));
			}
			
			else
			{
				disp.labelElement[i].setBorder(null);
				disp.labelElementParallel[i].setBorder(null);
			}
		}
	}
}
