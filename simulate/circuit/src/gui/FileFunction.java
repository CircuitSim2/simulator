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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartUtilities;

import circuit.ElemType;
import circuit.Element;
import circuit.ParallelCircuit;
import circuit.SeriesCircuit;

public class FileFunction extends JFrame implements ActionListener{

	private ArrayList <Element> elemList = new ArrayList<Element>();
	private ElemType elem_type;
	private String circuitType;
	private MainDispApp disp;
	private double voltage;

	public FileFunction(MainDispApp disp)
	{//FileFunctionにCircuitクラスのインスタンスを与えたときのコンストラクタ
		this.disp = disp;
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
			disp.loadCircuit();
		}


		else if("開く".equals(cmdName))//"開く"ボタンが押されたとき
		{
			JFileChooser filechooser = new JFileChooser();

			FileFilter filter = new FileNameExtensionFilter("simtファイル", "simt");
			filechooser.addChoosableFileFilter(filter);
			filechooser.setAcceptAllFileFilterUsed(false);


		    int selected = filechooser.showOpenDialog(this);
		    if (selected == JFileChooser.APPROVE_OPTION)
		    {
		      File file = filechooser.getSelectedFile();
		      disp.filePass = file.getAbsolutePath();
		      fileOpen(file);
		    }
		}

		else if("上書き保存".equals(cmdName) || "保存".equals(cmdName))//"上書き保存"ボタンが押されたとき
		{
			if(disp.filePass.equals("empty"))
			{
				JFileChooser filechooser = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("simtファイル", "simt");
				filechooser.addChoosableFileFilter(filter);
				filechooser.setAcceptAllFileFilterUsed(false);

			    int selected = filechooser.showSaveDialog(this);

			    if (selected == JFileChooser.APPROVE_OPTION)
			    {
			      File file = filechooser.getSelectedFile();
			      fileSave(file.getAbsolutePath());
			    }
			}
			else
			{
				fileSave(disp.filePass);
			}
		}

		else if("名前を付けて保存".equals(cmdName))//"名前を付けて保存"ボタンが押されたとき
		{
			JFileChooser filechooser = new JFileChooser();
			FileFilter filter = new FileNameExtensionFilter("simtファイル", "simt");
			filechooser.addChoosableFileFilter(filter);
			filechooser.setAcceptAllFileFilterUsed(false);

		    int selected = filechooser.showSaveDialog(this);

		    if (selected == JFileChooser.APPROVE_OPTION)
		    {
		      File file = filechooser.getSelectedFile();
		      fileSave(file.getAbsolutePath());
		    }
		}

		else if("波形を画像として保存".equals(cmdName))//"波形を画像として保存"ボタンが押されたとき
		{
			JFileChooser filechooser = new JFileChooser();
			FileFilter filter = new FileNameExtensionFilter("PNGファイル", "png");
			filechooser.addChoosableFileFilter(filter);
			filechooser.setAcceptAllFileFilterUsed(false);

		    int selected = filechooser.showSaveDialog(this);

		    if (selected == JFileChooser.APPROVE_OPTION)
		    {
		      File file = filechooser.getSelectedFile();
		      waveSave(file.getAbsolutePath());
		    }
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
				else if(i == 2) //回路の電圧の読み込み
				{
					voltage = Double.parseDouble(element[0]);
				}
				else //素子の読み込み
				{
					if(element[0].equals("R")) {elem_type = ElemType.RESISTANCE;}
					else if(element[0].equals("I")) {elem_type = ElemType.INDUCTANCE;}
					else if(element[0].equals("C")) {elem_type = ElemType.CAPACITANCE;}
					else if(element[0].equals("L")) {elem_type = ElemType.LINE;}

					Element elem = new Element(Double.parseDouble(element[1]), elem_type);

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

				disp.loadCircuit();

			}
			else if(circuitType.equals("Parallel"))
			{
				disp.mainCircuit = new ParallelCircuit();
				for(int j = 0; j < disp.mainCircuit.getElem().length; j++)
				{
					disp.mainCircuit.setElem(j, elemList.get(j));
				}

				disp.loadCircuit();

			}

			br.close();
		}

		catch (IOException e)
		{
			System.out.println(e);
		}

		// TODO 自動生成されたメソッド・スタブ

	}

	/*--------ファイルを保存するメソッド----------*/
	public void fileSave(String f)
	{
		disp.filePass = f;
		if(disp.mainCircuit instanceof SeriesCircuit)
		{
			//電圧の取得
			try
			{
				disp.mainCircuit.setVoltage(Double.parseDouble(disp.textFieldVoltage.getText()));
			}
			catch(Exception ex)
			{
				disp.mainCircuit.setVoltage(1);
				disp.textFieldVoltage.setText("1");
			}

			for(int i = 0;i < 6;i++)
			{
				try{
					disp.mainCircuit.getElem(i).setValue(Double.parseDouble(disp.textFieldElement[i].getText()));
				}
				catch(Exception ex)
				{
					disp.mainCircuit.getElem(i).setValue(0);
				}
			}
		}

		else if(disp.mainCircuit instanceof ParallelCircuit)
		{
			//電圧の取得
			try
			{
				disp.mainCircuit.setVoltage(Double.parseDouble(disp.textFieldVoltageParallel.getText()));
			}
			catch(Exception ex)
			{
				disp.mainCircuit.setVoltage(1);
				disp.textFieldVoltageParallel.setText("1");
			}

			for(int i = 0;i < 9;i++)
			{
				try{
					disp.mainCircuit.getElem(i).setValue(Double.parseDouble(disp.textFieldElementParallel[i].getText()));
				}
				catch(Exception ex)
				{
					disp.mainCircuit.getElem(i).setValue(0);
				}
			}
		}

		try
		{
			if(f.substring(f.length() - 5).equals(".simt"))
			{
				//falseのとき，ファイル拡張子を設定する．trueのときは何もしない
			}
			else
			{
				f = f + ".simt";
			}

			FileWriter fw = new FileWriter(f, false);
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));


			if(disp.mainCircuit instanceof ParallelCircuit)
			{
				pw.println("Parallel");
			}
			else if(disp.mainCircuit instanceof SeriesCircuit)
			{
				pw.println("Series");
			}

			pw.println(String.valueOf(disp.mainCircuit.getVoltage()));

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

	/*-------波形を保存するメソッド------*/
	public void waveSave(String f)
	{
		try
		{
			if(f.substring(f.length() - 5).equals(".png"))
			{
				//falseのとき，ファイル拡張子を設定する．trueのときは何もしない
			}
			else
			{
				f = f + ".png";
			}

			File waveFile = new File(f);
			ChartUtilities.saveChartAsPNG(waveFile, disp.chart, 400, 300);
		}
		catch(IOException e)
		{

		}
	}
}