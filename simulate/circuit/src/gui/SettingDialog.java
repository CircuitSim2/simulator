package gui;

import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import circuit.SeriesCircuit;

public class SettingDialog extends JDialog implements ActionListener
{
	private JPanel contentPane;
	private JFormattedTextField textFieldTime;
	private JFormattedTextField textFieldSwitchOnTime;
	private JFormattedTextField textFieldSwitchOffTime;
	private JButton buttonCancel;
	private JButton buttonStart;
	
	private MainDispApp disp;

	public SettingDialog(Frame frame, String title)
	{
		super(frame, title);
		
		this.disp = (MainDispApp) frame;

		setTitle("シミュレーションの設定");
		setBounds(frame.getX() + 100, frame.getY() + 100, 518, 200);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbls = new JLabel("実行時間[s]");
		lbls.setBounds(31, 35, 79, 13);
		contentPane.add(lbls);

		textFieldTime = new JFormattedTextField();
		textFieldTime.setBounds(275, 29, 64, 19);
		textFieldTime.setFormatterFactory(new NumberFormatterFactory());
		contentPane.add(textFieldTime);
		textFieldTime.setColumns(10);


		Label label = new Label("スイッチのON・OFF時間[s]");
		label.setBounds(31, 69, 164, 23);
		contentPane.add(label);

		textFieldSwitchOnTime = new JFormattedTextField();
		textFieldSwitchOnTime.setBounds(274, 69, 66, 19);
		textFieldSwitchOnTime.setFormatterFactory(new NumberFormatterFactory());
		contentPane.add(textFieldSwitchOnTime);
		textFieldSwitchOnTime.setColumns(10);

		buttonStart = new JButton("開始");
		buttonStart.addActionListener(this);
		buttonStart.setBounds(279, 100, 91, 21);
		contentPane.add(buttonStart);

		buttonCancel = new JButton("キャンセル");
		buttonCancel.addActionListener(this);
		buttonCancel.setBounds(382, 100, 108, 21);
		contentPane.add(buttonCancel);
		
		JLabel lblOn = new JLabel("ON");
		lblOn.setBounds(242, 73, 24, 13);
		contentPane.add(lblOn);
		
		JLabel lblOff = new JLabel("OFF");
		lblOff.setBounds(367, 72, 30, 13);
		contentPane.add(lblOff);
		
		textFieldSwitchOffTime = new JFormattedTextField();
		textFieldSwitchOffTime.setColumns(10);
		textFieldSwitchOffTime.setFormatterFactory(new NumberFormatterFactory());
		textFieldSwitchOffTime.setBounds(395, 70, 66, 19);
		contentPane.add(textFieldSwitchOffTime);
		
		JLabel lblS = new JLabel("s");
		lblS.setBounds(343, 32, 10, 13);
		contentPane.add(lblS);
		
		JLabel label_1 = new JLabel("s");
		label_1.setBounds(342, 71, 10, 13);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("s");
		label_2.setBounds(463, 72, 10, 13);
		contentPane.add(label_2);
	}
	
	//ボタン押されたときの処理
	public void actionPerformed(ActionEvent event)
	{
        // ユーザの操作対象を判断
        if(event.getSource() == buttonStart) 
        {
        	if(disp.mainCircuit instanceof SeriesCircuit)
    		{
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

    			((SeriesCircuit) disp.mainCircuit).calcCurrent(0, Double.parseDouble(textFieldTime.getText()));
    			((SeriesCircuit) disp.mainCircuit).calcVoltage(0, 0, Double.parseDouble(textFieldTime.getText()));

    	    	XYSeriesCollection data = new XYSeriesCollection();
    	    	XYSeries series = new XYSeries("current");
    	    	
    	    	if(disp.radioButtonCurrent.isSelected())
    	    	{
	    	    	for(int i = 0; i < (Double.parseDouble(textFieldTime.getText()) / disp.mainCircuit.dt);i++)
	    	    	{
	    	    		series.add(i * disp.mainCircuit.dt ,((SeriesCircuit)disp.mainCircuit).getCurrentList().get(i));
	    	    	}
    	    	}
    	    	
    	    	else if(disp.radioButtonVoltage.isSelected())
    	    	{
	    	    	for(int i = 0; i < (Double.parseDouble(textFieldTime.getText()) / disp.mainCircuit.dt);i++)
	    	    	{
	    	    		series.add(i * disp.mainCircuit.dt ,((SeriesCircuit)disp.mainCircuit).getVoltList().get(i));
	    	    	}
    	    	}

    	    	data.addSeries(series);

    			JFreeChart chart =
    					ChartFactory.createXYLineChart(
    				            null,                      // chart title
    				            "Time[s]",                // x axis label
    				            null,    // y axis label
    				            data,                  // data
    				            PlotOrientation.VERTICAL,
    				            true,                     // include legend
    				            true,                     // tooltips
    				            false                     // urls
    				            );

    			disp.panelGraph.setChart(chart);
    		}
        }
        this.setVisible(false);
	}   
}
