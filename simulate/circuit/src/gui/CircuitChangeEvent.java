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
			disp.textFieldVoltage.setVisible(false);
			disp.textFieldElement1.setVisible(false);
			disp.textFieldElement2.setVisible(false);
			disp.textFieldElement3.setVisible(false);
			disp.textFieldElement4.setVisible(false);
			disp.textFieldElement5.setVisible(false);
			disp.textFieldElement6.setVisible(false);
			disp.labelElement1.setVisible(false);
			disp.labelElement2.setVisible(false);
			disp.labelElement3.setVisible(false);
			disp.labelElement4.setVisible(false);
			disp.labelElement5.setVisible(false);
			disp.labelElement6.setVisible(false);
			disp.labelVoltageUnit.setVisible(false);
			disp.labelElement1Unit.setVisible(false);
			disp.labelElement2Unit.setVisible(false);
			disp.labelElement3Unit.setVisible(false);
			disp.labelElement4Unit.setVisible(false);
			disp.labelElement5Unit.setVisible(false);
			disp.labelElement6Unit.setVisible(false);

			//並列回路のコンポーネントを有効化
			disp.textFieldVoltageParallel.setVisible(true);
			disp.textFieldElement1Parallel.setVisible(true);
			disp.textFieldElement2Parallel.setVisible(true);
			disp.textFieldElement3Parallel.setVisible(true);
			disp.textFieldElement4Parallel.setVisible(true);
			disp.textFieldElement5Parallel.setVisible(true);
			disp.textFieldElement6Parallel.setVisible(true);
			disp.textFieldElement7Parallel.setVisible(true);
			disp.textFieldElement8Parallel.setVisible(true);
			disp.textFieldElement9Parallel.setVisible(true);
			disp.labelElement1Parallel.setVisible(true);
			disp.labelElement2Parallel.setVisible(true);
			disp.labelElement3Parallel.setVisible(true);
			disp.labelElement4Parallel.setVisible(true);
			disp.labelElement5Parallel.setVisible(true);
			disp.labelElement6Parallel.setVisible(true);
			disp.labelElement7Parallel.setVisible(true);
			disp.labelElement8Parallel.setVisible(true);
			disp.labelElement9Parallel.setVisible(true);
			disp.labelVoltageUnitParallel.setVisible(true);
			disp.labelElement1UnitParallel.setVisible(true);
			disp.labelElement2UnitParallel.setVisible(true);
			disp.labelElement3UnitParallel.setVisible(true);
			disp.labelElement4UnitParallel.setVisible(true);
			disp.labelElement5UnitParallel.setVisible(true);
			disp.labelElement6UnitParallel.setVisible(true);
			disp.labelElement7UnitParallel.setVisible(true);
			disp.labelElement8UnitParallel.setVisible(true);
			disp.labelElement9UnitParallel.setVisible(true);

			disp.labelCircuitPicture.setIcon(disp.parallelCircuitPicture);

			disp.mainCircuit = new ParallelCircuit();
		}

		else
		{
			//直列回路のコンポーネントを有効化
			disp.textFieldVoltage.setVisible(true);
			disp.textFieldElement1.setVisible(true);
			disp.textFieldElement2.setVisible(true);
			disp.textFieldElement3.setVisible(true);
			disp.textFieldElement4.setVisible(true);
			disp.textFieldElement5.setVisible(true);
			disp.textFieldElement6.setVisible(true);
			disp.labelElement1.setVisible(true);
			disp.labelElement2.setVisible(true);
			disp.labelElement3.setVisible(true);
			disp.labelElement4.setVisible(true);
			disp.labelElement5.setVisible(true);
			disp.labelElement6.setVisible(true);
			disp.labelVoltageUnit.setVisible(true);
			disp.labelElement1Unit.setVisible(true);
			disp.labelElement2Unit.setVisible(true);
			disp.labelElement3Unit.setVisible(true);
			disp.labelElement4Unit.setVisible(true);
			disp.labelElement5Unit.setVisible(true);
			disp.labelElement6Unit.setVisible(true);
			//並列回路のコンポーネントを非表示に
			disp.textFieldVoltageParallel.setVisible(false);
			disp.textFieldElement1Parallel.setVisible(false);
			disp.textFieldElement2Parallel.setVisible(false);
			disp.textFieldElement3Parallel.setVisible(false);
			disp.textFieldElement4Parallel.setVisible(false);
			disp.textFieldElement5Parallel.setVisible(false);
			disp.textFieldElement6Parallel.setVisible(false);
			disp.textFieldElement7Parallel.setVisible(false);
			disp.textFieldElement8Parallel.setVisible(false);
			disp.textFieldElement9Parallel.setVisible(false);
			disp.labelElement1Parallel.setVisible(false);
			disp.labelElement2Parallel.setVisible(false);
			disp.labelElement3Parallel.setVisible(false);
			disp.labelElement4Parallel.setVisible(false);
			disp.labelElement5Parallel.setVisible(false);
			disp.labelElement6Parallel.setVisible(false);
			disp.labelElement7Parallel.setVisible(false);
			disp.labelElement8Parallel.setVisible(false);
			disp.labelElement9Parallel.setVisible(false);
			disp.labelVoltageUnitParallel.setVisible(false);
			disp.labelElement1UnitParallel.setVisible(false);
			disp.labelElement2UnitParallel.setVisible(false);
			disp.labelElement3UnitParallel.setVisible(false);
			disp.labelElement4UnitParallel.setVisible(false);
			disp.labelElement5UnitParallel.setVisible(false);
			disp.labelElement6UnitParallel.setVisible(false);
			disp.labelElement7UnitParallel.setVisible(false);
			disp.labelElement8UnitParallel.setVisible(false);
			disp.labelElement9UnitParallel.setVisible(false);

			disp.labelCircuitPicture.setIcon(disp.seriesCircuitPicture);
			disp.mainCircuit = new SeriesCircuit();
		}
	}
}