package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import circuit.Circuit;
import circuit.ElemType;
import circuit.Element;
import circuit.ParallelCircuit;
import circuit.SeriesCircuit;

public class FileFunction extends JFrame implements ActionListener{

	private ArrayList <Element> elemList = new ArrayList<Element>();
	private ElemType elem_type;
	private String circuitType;
	private Circuit mainCircuit;

	public FileFunction(Circuit mainCircuit)
	{
		this.mainCircuit = mainCircuit;
	}

	public void actionPerformed(ActionEvent e)
	{
		JFileChooser filechooser = new JFileChooser();

	    int selected = filechooser.showOpenDialog(this);
	    if (selected == JFileChooser.APPROVE_OPTION)
	    {
	      File file = filechooser.getSelectedFile();
	      fileOpen(file);
	    }
	    else if (selected == JFileChooser.CANCEL_OPTION)
	    {
	      System.out.println("キャンセルされました");
	    }
	    else if (selected == JFileChooser.ERROR_OPTION)
	    {
	    	System.out.println("エラー又は取消しがありました");
	    }
	}

	public void fileOpen(File f)
	{ //過去に作成したシミュレーションを開く(CSV形式)
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(f));

			String line;
			int i = 1;
			while((line = br.readLine()) != null) //１行ごとに読み込み、処理を行う
			{
				String[] element = line.split(",");

				if(i == 1) //回路の形の判別
				{
					if(element[0].equals("Series")) {circuitType = "Series";}
					else if(element[0].equals("Parallel")) {circuitType = "Parallel";}
				}
				else //素子の読み込み
				{
					if(element[0].equals("R")) {elem_type = ElemType.RESISTANCE;}
					else if(element[0].equals("I")) {elem_type = ElemType.INDUCTANCE;}
					else if(element[0].equals("C")) {elem_type = ElemType.CAPACITANCE;}
					else if(element[0].equals("L")) {elem_type = ElemType.LINE;}

					Element elem = new Element(Double.parseDouble(element[1]), elem_type);
					System.out.println(elem_type);

					elemList.add(elem);
				}
				i++;
			}

			if(circuitType.equals("Series"))
			{
				System.out.println("直列開いたよ");
				for(int j = 0; j < elemList.size(); j++)
				{
					mainCircuit = new SeriesCircuit();
					mainCircuit.setElem(j, elemList.get(j));
				}
				System.out.println("直列開いたよ");
			}
			else if(circuitType.equals("Parallel"))
			{
				System.out.println("並列開いたよ");
				for(int j = 0; j < elemList.size(); j++)
				{
					mainCircuit = new ParallelCircuit();
					mainCircuit.setElem(j, elemList.get(j));
				}
				System.out.println("並列開いたよ");
			}

			for(int z = 0; z < elemList.size(); z++)
			{
				Element elem = elemList.get(z);//test

				System.out.println(elem.getValue());
				System.out.println(elem.getEt());
			}

			br.close();
		}

		catch (IOException e)
		{
			System.out.println(e);
		}

		// TODO 自動生成されたメソッド・スタブ

	}
}