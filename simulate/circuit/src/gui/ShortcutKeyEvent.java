package gui;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ShortcutKeyEvent extends KeyAdapter
{
	Component eE;
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

		//ctrl+S
		if(e.getModifiers() == KeyEvent.CTRL_MASK && e.getKeyCode() == KeyEvent.VK_S)
		{
			FileFunction FF = new FileFunction(disp);
			if(disp.filePass.equals("empty"))
			{
				JFileChooser filechooser = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("simtファイル", "simt");
				filechooser.addChoosableFileFilter(filter);
				filechooser.setAcceptAllFileFilterUsed(false);

			    int selected = filechooser.showSaveDialog(eE);

			    if (selected == JFileChooser.APPROVE_OPTION)
			    {
			      File file = filechooser.getSelectedFile();
			      FF.fileSave(file.getAbsolutePath());
			    }
			}
			else
			{
				FF.fileSave(disp.filePass);
			}
		}
	}
}
