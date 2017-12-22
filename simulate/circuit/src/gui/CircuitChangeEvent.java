package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import circuit.ParallelCircuit;
import circuit.SeriesCircuit;

//回路の種類を選択した時のイベント
public class CircuitChangeEvent extends MouseAdapter
{
	private MainDispApp disp;

	public CircuitChangeEvent(MainDispApp disp)
	{
		super();
		this.disp = disp;
	}

	//マウスを話したとき選択した回路に切り替える
	public void mouseReleased(MouseEvent e)
	{
		//並列回路に切り替え
		if(e.getComponent() == disp.menuItemParallelCircuit)
		{
			//直列回路のコンポーネントを非表示に
			disp.changeStateSerial(false);

			//並列回路のコンポーネントを有効化
			disp.changeStateParallel(true);

			disp.labelCircuitPicture.setIcon(disp.parallelCircuitPicture);

			disp.mainCircuit = new ParallelCircuit();
		}

		else
		{
			//直列回路のコンポーネントを有効化
			disp.changeStateSerial(true);

			//並列回路のコンポーネントを非表示に
			disp.changeStateParallel(false);

			disp.labelCircuitPicture.setIcon(disp.seriesCircuitPicture);
			disp.mainCircuit = new SeriesCircuit();
		}
	}
}
