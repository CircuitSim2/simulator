package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ShortcutKeyEvent extends KeyAdapter
{
	private MainDispApp disp;
	
	public ShortcutKeyEvent(MainDispApp disp)
	{
		this.disp = disp;
	}
	
	public void keyPressed(KeyEvent e)
	{		
		//ctrl+Z
		if(e.getModifiers() == KeyEvent.CTRL_MASK && e.getKeyCode() == KeyEvent.VK_Z)
		{
			disp.mainCircuit = disp.redoAndUndo.redo(disp.mainCircuit);
			disp.loadCircuit();
		}
		
		//ctrl+Y
		if(e.getModifiers() == KeyEvent.CTRL_MASK && e.getKeyCode() == KeyEvent.VK_Y)
		{
			disp.mainCircuit = disp.redoAndUndo.undo(disp.mainCircuit);
			disp.loadCircuit();
		}
	}
}
