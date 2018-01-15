package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	//private Circuit mainCircuit;
	private MainDispApp disp;
	//private String FilePass;

	public FileFunction(Circuit mainCircuit, MainDispApp disp)
	{//FileFunctionにCircuitクラスのインスタンスを与えたときのコンストラクタ
		//this.mainCircuit = mainCircuit;
		this.disp = disp;
		//this.FilePass = disp.filePass;
	}

	public FileFunction()
	{
		//FileFunctionにCircuitクラスのインスタンスを与えないときのコンストラクタ
	}

	public void actionPerformed(ActionEvent e)
	{
		String cmdName = e.getActionCommand();
		if("新規作成".equals(cmdName))//"新規作成"ボタンが押されたとき
		{
			disp.mainCircuit = new SeriesCircuit();

			/*-----test------*/
			for(int z = 0; z < disp.mainCircuit.getElem().length; z++)//test
			{
				Element elem = disp.mainCircuit.getElem(z);//test

				System.out.println(elem.getValue());
				System.out.println(elem.getEt());
			}

		}


		else if("開く".equals(cmdName))//"開く"ボタンが押されたとき
		{
			JFileChooser filechooser = new JFileChooser();

		    int selected = filechooser.showOpenDialog(this);
		    if (selected == JFileChooser.APPROVE_OPTION)
		    {
		      File file = filechooser.getSelectedFile();
		      disp.filePass = file.getAbsolutePath();
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

		else if("上書き保存".equals(cmdName))//"上書き保存"ボタンが押されたとき
		{
			System.out.println(disp.filePass);
		}

		else if("名前を付けて保存".equals(cmdName))//"名前を付けて保存"ボタンが押されたとき
		{
			JFileChooser filechooser = new JFileChooser();

		    int selected = filechooser.showSaveDialog(this);

		    if (selected == JFileChooser.APPROVE_OPTION)
		    {
		      File file = filechooser.getSelectedFile();
		      fileSave(file.getAbsolutePath());
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

		else if("波形を画像として保存".equals(cmdName))//"波形を画像として保存"ボタンが押されたとき
		{

		}

		else if("閉じる".equals(cmdName))//"閉じる"ボタンが押されたとき
		{
			System.exit(0);
		}
	}

	/*------ファイルを開く-----*/
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
				disp.mainCircuit = new SeriesCircuit();
				for(int j = 0; j < disp.mainCircuit.getElem().length; j++)
				{

					disp.mainCircuit.setElem(j, elemList.get(j));
				}

				//disp.setCircuit(mainCircuit);
				//直列回路のコンポーネントを有効化
				disp.changeStateSerial(true);

				//並列回路のコンポーネントを非表示に
				disp.changeStateParallel(false);
				disp.labelCircuitPicture.setIcon(disp.seriesCircuitPicture);
			}
			else if(circuitType.equals("Parallel"))
			{
				disp.mainCircuit = new ParallelCircuit();
				for(int j = 0; j < disp.mainCircuit.getElem().length; j++)
				{
					disp.mainCircuit.setElem(j, elemList.get(j));
				}
				//disp.setCircuit(mainCircuit);
				disp.changeStateSerial(false);

				//並列回路のコンポーネントを有効化
				disp.changeStateParallel(true);
				disp.labelCircuitPicture.setIcon(disp.parallelCircuitPicture);
			}

			br.close();
		}

		catch (IOException e)
		{
			System.out.println(e);
		}

		// TODO 自動生成されたメソッド・スタブ

	}

	public void fileSave(String f)
	{
		try
		{
			FileWriter fw = new FileWriter(f, false);
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

			if(disp.mainCircuit instanceof ParallelCircuit)
			{
				pw.print("Parallel");
			}
			else if(disp.mainCircuit instanceof SeriesCircuit)
			{
				pw.println("Series");
			}

			for(int z = 0; z < disp.mainCircuit.getElem().length; z++)//test
			{
				Element elem = disp.mainCircuit.getElem(z);//test

				String elemName;

				switch(elem.getEt())
				{
					case RESISTANCE:
						elemName = "R";
						break;
					case INDUCTANCE:
						elemName = "I";
						break;
					case CAPACITANCE:
						elemName = "C";
						break;
					case LINE:
						elemName = "L";
						break;
					default:
						elemName = "L";
						break;
				}

				pw.print(elemName);
				pw.print(",");
				pw.print(String.valueOf(elem.getValue()));//値を書き込み
				pw.println();//改行

			}
			pw.close();
		}

		catch(IOException e)
		{

		}

	}
}