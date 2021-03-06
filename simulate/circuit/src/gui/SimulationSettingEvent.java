package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class SimulationSettingEvent implements ActionListener
{
	private MainDispApp disp;
	
	public SimulationSettingEvent(MainDispApp disp)
	{
		this.disp = disp;
	}

	public void actionPerformed(ActionEvent e)
	{
		JDialog dialog = new SettingDialog(disp, "シミュレーションの設定");
		dialog.setVisible(true);
    }
}
