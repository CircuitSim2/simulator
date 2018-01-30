package gui;

import java.util.LinkedList;

import circuit.Circuit;
import circuit.ParallelCircuit;
import circuit.SeriesCircuit;

public class RedoAndUndo 
{
	private LinkedList<Circuit> listHistory;
	private LinkedList<Circuit> listHistoryF;
	
	public RedoAndUndo()
	{
		listHistory = new LinkedList<Circuit>();
		listHistoryF = new LinkedList<Circuit>();
	}
	
	public void listPush(Circuit circuit)
	{
		Circuit pushCircuit;
		
		if(circuit instanceof SeriesCircuit)
			pushCircuit = new SeriesCircuit();
		
		else if(circuit instanceof ParallelCircuit)
			pushCircuit = new ParallelCircuit();
		else
			pushCircuit = new Circuit();
		
		for(int i = 0;i < circuit.getElem().length;i++)
		{
			pushCircuit.setElem(i, circuit.getElem(i));
		}
		
		listHistory.push(pushCircuit);
		listHistoryF.removeAll(listHistoryF);
	}
	
	public Circuit redo(Circuit circuit)
	{
		Circuit recircuit = listHistory.getFirst();
		listHistoryF.push(circuit);
		listHistory.removeFirst();
		return recircuit;
	}
	
	public Circuit undo(Circuit circuit)
	{
		Circuit uncircuit = listHistoryF.getFirst();
		listHistoryF.removeFirst();
		listHistory.push(circuit);
		return uncircuit;
	}
}
