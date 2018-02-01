package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import circuit.ParallelCircuit;
import circuit.SeriesCircuit;

//回路の種類を選択した時のイベント
public class CircuitChangeEvent implements ActionListener
{
	private MainDispApp disp;

	public CircuitChangeEvent(MainDispApp disp)
	{
		super();
		this.disp = disp;
	}

	//マウスを離したとき選択した回路に切り替える
	public void actionPerformed(ActionEvent e)
	{
		//並列回路に切り替え
		if(e.getSource() == disp.menuItemParallelCircuit)
		{
			disp.mainCircuit = new ParallelCircuit();

			disp.loadCircuit();
		}
		
		else if(e.getSource() == disp.menuItemSeriesCircuit)
		{
			disp.mainCircuit = new SeriesCircuit();

			disp.loadCircuit();
		}
		
			
		else if(e.getSource() == disp.menuItemAllClear)
		{
			if(disp.mainCircuit instanceof SeriesCircuit)
			{
				disp.mainCircuit = new SeriesCircuit();

				disp.loadCircuit();
			}
			
			else if(disp.mainCircuit instanceof ParallelCircuit)
			{
				disp.mainCircuit = new ParallelCircuit();

				disp.loadCircuit();
			}
		}
	}
}
