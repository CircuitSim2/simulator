package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import circuit.ElemType;
import circuit.Element;

public class ElementChangeEvent extends MouseAdapter
{
	private Element selectedElem;
	private MainDispApp disp;
	
	public ElementChangeEvent(MainDispApp disp)
	{
		this.disp = disp;
		selectedElem = null;
	}
	
	public void mousePressed(MouseEvent e)
	{
		String elemName = (String) disp.table.getValueAt(disp.table.getSelectedRow(), 1);
		
		if(elemName.equals("抵抗"))
			selectedElem = new Element(0.0, ElemType.RESISTANCE);
		else if(elemName.equals("キャパシタ"))
			selectedElem = new Element(0.0, ElemType.CAPACITANCE);
		else if(elemName.equals("インダクタ"))
			selectedElem = new Element(0.0, ElemType.INDUCTANCE);
		else if(elemName.equals("ライン"))
			selectedElem = new Element(0.0, ElemType.LINE);
		
		System.out.println(elemName);
	}
	
	public void mouseReleased(MouseEvent e)
	{
		if(disp.selectedElemNum != -1)
			disp.mainCircuit.setElem(disp.selectedElemNum, selectedElem);
		
		disp.loadCircuit();
	}
}