package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedoUndoEvent implements ActionListener
{
	private MainDispApp disp;
	
	public RedoUndoEvent(MainDispApp disp)
	{
		this.disp = disp;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("進む"))
		{
			disp.mainCircuit = disp.redoAndUndo.undo(disp.mainCircuit);
			disp.loadCircuit();
		}
		
		else if(e.getActionCommand().equals("戻る"))
		{
			disp.mainCircuit = disp.redoAndUndo.redo(disp.mainCircuit);
			disp.loadCircuit();
		}
	}
}
