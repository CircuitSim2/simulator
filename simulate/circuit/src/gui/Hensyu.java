package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import circuit.ParallelCircuit;
import circuit.SeriesCircuit;

public class Hensyu extends JFrame implements ActionListener{

    private static final JFormattedTextField JFormattedTextField = null;
	private MainDispApp disp;

    public Hensyu(MainDispApp area)
    {
    	super();
        this.disp = area;


    }
    public Hensyu()
    {

    }

    public void actionPerformed(ActionEvent e)
    {
        //String cmdName = e.getActionCommand();
        if(e.getSource() == disp.menuItemCopy)
        {
        		//直列
        		for(int i=0;i<6;i++){  //素子の値
        		disp.textFieldElement[i].copy();
        			if(disp.textFieldElement[i]==null){

        			}
        		}
        		disp.textFieldVoltage.copy();	//電圧の値
            		
        		if(disp.textFieldVoltage==null){

            	}
        		//並列
        		for(int i=0;i<9;i++){	//素子の値
            		disp.textFieldElementParallel[i].copy();
            		if(disp.textFieldElementParallel[i]==null){

            		}
            	}
        		
    			disp.textFieldVoltageParallel.copy();	//電圧の値
    			if(disp.textFieldVoltageParallel==null){

    			}
        }

        else if(e.getSource() == disp.menuItemPaste)
        {
        	if(disp.mainCircuit instanceof SeriesCircuit)
        	{
        		disp.textFieldElement[disp.selectedTextField].paste();
        		disp.mainCircuit.getElem(disp.selectedTextField).setValue(Double.parseDouble(disp.textFieldElement[disp.selectedTextField].getText()));
        	}

        	else if(disp.mainCircuit instanceof ParallelCircuit)
        	{
        		disp.textFieldElementParallel[disp.selectedTextField].paste();
        		disp.mainCircuit.getElem(disp.selectedTextField).setValue(Double.parseDouble(disp.textFieldElementParallel[disp.selectedTextField].getText()));
        	}
        }


        else if(e.getSource() == disp.menuItemCut)
        {
        	for(int i=0;i<6;i++){
        		disp.textFieldElement[i].copy();
        		disp.textFieldElement[i].cut();
        	}
    		disp.textFieldVoltage.copy();
    		disp.textFieldVoltage.cut();


    		for(int i=0;i<9;i++){
    			disp.textFieldElementParallel[i].copy();
    			disp.textFieldElementParallel[i].cut();
        	}
			disp.textFieldVoltageParallel.copy();
			disp.textFieldVoltageParallel.cut();
        }

    }
}


