package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class MainDispApp extends JFrame {
	private JTextField textFieldElement1;
	private JTextField textFieldElement2;
	private JTextField textFieldElement5;
	private JTextField textFieldElement6;
	private JTextField textFieldElement3;
	private JTextField textFieldElement4;
	private JLabel labelCircuitPicture;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu meuDisp;
	private JMenu menuSimulate;
	private JMenu menuHelp;
	private JMenu menuEdit;
	private JMenu menuFile;
	private JMenuBar menuBarButtons;
	private JPanel panelGraph;
	private JLabel labelGraph;
	private JPanel panelCircuit;
	private JTextField textFieldVoltage;
	private JLabel labelElement1;
	private JLabel labelElement2;
	private JLabel labelElement3;
	private JLabel labelElement6;
	private JLabel labelElement5;
	private JLabel labelElement4;
	private JPanel panelFormula;
	private JLabel labelCircuit;
	private JLabel labelElementList;
	private JLabel labelFormula;
	private JPanel panelElementList;
	private JScrollPane scrollPaneElementList;
	private JPanel scrollPanelElementList;
	private JMenuItem menuItemNew;
	private JMenuItem menuItemOpen;
	private JMenuItem menuItemOverWrite;
	private JMenuItem menuItemSaveAs;
	private JMenuItem menuItemExportGraph;
	private JMenuItem menuItemClose;
	private JMenuItem menuItemCircuitGraph;
	private JMenuItem menuItemFormula;
	private JMenuItem menuItemElement;
	private JMenuItem menuItemGraph;
	private JMenuItem menuItemRedo;
	private JMenuItem menuItemUndo;
	private JMenuItem menuItemCopy;
	private JMenuItem menuItemCut;
	private JMenuItem menuItemPaste;
	private JMenuItem menuItemAllClear;
	private JMenuItem menuItemHelpViewer;
	private JMenuItem menuItemSimulation;
	private JButton buttonRedo;
	private JButton buttonUndo;
	private JButton buttonOpen;
	private JButton buttonSave;
	private JButton buttonStart;
	private JButton buttonEnd;
	private ImageIcon seriesCircuitPicture;
	private ImageIcon linePicture;
	private ImageIcon resistancePicture;
	private ImageIcon inductancePicture;
	private ImageIcon capacitancePicture;
	private ImageIcon linePictureV;
	private ImageIcon resistancePictureV;
	private ImageIcon inductancePictureV;
	private ImageIcon capacitancePictureV;
	private JLabel labelElement4Unit;
	private JLabel labelElement5Unit;
	private JLabel labelElement6Unit;
	private JLabel labelElement3Unit;
	private JLabel labelElement2Unit;
	private JLabel labelElement1Unit;
	private JLabel labelVoltageUnit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDispApp frame = new MainDispApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainDispApp() {
		//必要画像の読み込み
		 seriesCircuitPicture = new ImageIcon(MainDispApp.class.getResource("/resources/SeriesCircuit.png"));
		 linePicture = new ImageIcon(MainDispApp.class.getResource("/resources/Line.png"));
		 resistancePicture = new ImageIcon(MainDispApp.class.getResource("/resources/resistance.png"));
		 inductancePicture = new ImageIcon(MainDispApp.class.getResource("/resources/Inductance.png"));
		 capacitancePicture = new ImageIcon(MainDispApp.class.getResource("/resources/Cpacitance.png"));
		 linePictureV = new ImageIcon(MainDispApp.class.getResource("/resources/LineV.png"));
		 resistancePictureV = new ImageIcon(MainDispApp.class.getResource("/resources/resistanceV.png"));
		 inductancePictureV = new ImageIcon(MainDispApp.class.getResource("/resources/InductanceV.png"));
		 capacitancePictureV = new ImageIcon(MainDispApp.class.getResource("/resources/CpacitanceV.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 433);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuFile = new JMenu("ファイル");
		menuBar.add(menuFile);

		menuItemNew = new JMenuItem("新規作成");
		menuFile.add(menuItemNew);

		menuItemOpen = new JMenuItem("開く");
		menuFile.add(menuItemOpen);

		menuItemOverWrite = new JMenuItem("上書き保存");
		menuFile.add(menuItemOverWrite);

		menuItemSaveAs = new JMenuItem("名前を付けて保存");
		menuFile.add(menuItemSaveAs);

		menuItemExportGraph = new JMenuItem("波形を画像として保存");
		menuFile.add(menuItemExportGraph);

		menuItemClose = new JMenuItem("閉じる");
		menuFile.add(menuItemClose);

		meuDisp = new JMenu("表示");
		menuBar.add(meuDisp);

		menuItemCircuitGraph = new JMenuItem("回路図");
		meuDisp.add(menuItemCircuitGraph);

		menuItemFormula = new JMenuItem("数式");
		meuDisp.add(menuItemFormula);

		menuItemElement = new JMenuItem("素子リスト");
		meuDisp.add(menuItemElement);

		menuItemGraph = new JMenuItem("波形");
		meuDisp.add(menuItemGraph);

		menuEdit = new JMenu("編集");
		menuBar.add(menuEdit);

		menuItemRedo = new JMenuItem("戻る");
		menuEdit.add(menuItemRedo);

		menuItemUndo = new JMenuItem("進む");
		menuEdit.add(menuItemUndo);

		menuItemCopy = new JMenuItem("コピー");
		menuEdit.add(menuItemCopy);

		menuItemCut = new JMenuItem("切り取り");
		menuEdit.add(menuItemCut);

		menuItemPaste = new JMenuItem("ペースト");
		menuEdit.add(menuItemPaste);

		menuItemAllClear = new JMenuItem("全消去");
		menuEdit.add(menuItemAllClear);

		menuHelp = new JMenu("ヘルプ");
		menuBar.add(menuHelp);

		menuItemHelpViewer = new JMenuItem("ヘルプビューア");
		menuHelp.add(menuItemHelpViewer);

		menuSimulate = new JMenu("実行");
		menuBar.add(menuSimulate);

		menuItemSimulation = new JMenuItem("シミュレーション");
		menuSimulate.add(menuItemSimulation);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		menuBarButtons = new JMenuBar();
		menuBarButtons.setBounds(0, 0, 676, 21);
		contentPane.add(menuBarButtons);

		buttonRedo = new JButton("戻る");
		menuBarButtons.add(buttonRedo);

		buttonUndo = new JButton("進む");
		menuBarButtons.add(buttonUndo);

		buttonOpen = new JButton("開く");
		menuBarButtons.add(buttonOpen);

		buttonSave = new JButton("保存");
		menuBarButtons.add(buttonSave);

		buttonStart = new JButton("シミュレーション開始");
		menuBarButtons.add(buttonStart);

		buttonEnd = new JButton("シミュレーション終了");
		menuBarButtons.add(buttonEnd);

		panelGraph = new JPanel();
		panelGraph.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGraph.setBounds(0, 238, 676, 125);
		contentPane.add(panelGraph);

		labelGraph = new JLabel("波形");
		labelGraph.setBounds(0, 222, 676, 13);
		contentPane.add(labelGraph);

		panelCircuit = new JPanel();
		panelCircuit.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCircuit.setBounds(0, 41, 342, 178);
		contentPane.add(panelCircuit);

		panelCircuit.setLayout(null);

		textFieldVoltage = new JTextField();
		textFieldVoltage.setBounds(61, 80, 36, 19);
		panelCircuit.add(textFieldVoltage);
		textFieldVoltage.setColumns(10);

		textFieldElement2 = new JTextField();
		textFieldElement2.setColumns(10);
		textFieldElement2.setBounds(197, 47, 36, 19);
		panelCircuit.add(textFieldElement2);

		textFieldElement1 = new JTextField();
		textFieldElement1.setBounds(104, 47, 36, 19);
		panelCircuit.add(textFieldElement1);
		textFieldElement1.setColumns(10);

		textFieldElement5 = new JTextField();
		textFieldElement5.setColumns(10);
		textFieldElement5.setBounds(197, 115, 36, 19);
		panelCircuit.add(textFieldElement5);

		textFieldElement6 = new JTextField();
		textFieldElement6.setColumns(10);
		textFieldElement6.setBounds(104, 115, 36, 19);
		panelCircuit.add(textFieldElement6);

		textFieldElement3 = new JTextField();
		textFieldElement3.setColumns(10);
		textFieldElement3.setBounds(234, 69, 36, 19);
		panelCircuit.add(textFieldElement3);

		textFieldElement4 = new JTextField();
		textFieldElement4.setColumns(10);
		textFieldElement4.setBounds(234, 91, 36, 19);
		panelCircuit.add(textFieldElement4);

		labelElement1 = new JLabel(resistancePicture);
		labelElement1.setBounds(101, 11, 43, 29);
		panelCircuit.add(labelElement1);

		labelElement2 = new JLabel(capacitancePicture);
		labelElement2.setBounds(195, 11, 43, 29);
		panelCircuit.add(labelElement2);

		labelElement3 = new JLabel(inductancePictureV);
		labelElement3.setBounds(297, 41, 29, 43);
		panelCircuit.add(labelElement3);
		
		labelElement4 = new JLabel(linePictureV);
		labelElement4.setBounds(297, 94, 29, 43);
		panelCircuit.add(labelElement4);

		labelElement5 = new JLabel(linePicture);
		labelElement5.setBounds(195, 137, 43, 29);
		panelCircuit.add(labelElement5);
		
		labelElement6 = new JLabel(linePicture);
		labelElement6.setBounds(101, 137, 44, 29);
		panelCircuit.add(labelElement6);
		labelCircuitPicture = new JLabel(seriesCircuitPicture);
		labelCircuitPicture.setBounds(1, 1, 340, 176);
		panelCircuit.add(labelCircuitPicture);
		
		labelVoltageUnit = new JLabel("V");
		labelVoltageUnit.setBounds(101, 83, 19, 13);
		panelCircuit.add(labelVoltageUnit);
		
		labelElement1Unit = new JLabel("Ω");
		labelElement1Unit.setBounds(143, 50, 19, 13);
		panelCircuit.add(labelElement1Unit);
		
		labelElement2Unit = new JLabel("Ω");
		labelElement2Unit.setBounds(237, 50, 19, 13);
		panelCircuit.add(labelElement2Unit);
		
		labelElement3Unit = new JLabel("Ω");
		labelElement3Unit.setBounds(272, 72, 19, 13);
		panelCircuit.add(labelElement3Unit);
		
		labelElement4Unit = new JLabel("Ω");
		labelElement4Unit.setBounds(272, 94, 19, 13);
		panelCircuit.add(labelElement4Unit);
		
		labelElement5Unit = new JLabel("Ω");
		labelElement5Unit.setBounds(234, 118, 19, 13);
		panelCircuit.add(labelElement5Unit);
		
		labelElement6Unit = new JLabel("Ω");
		labelElement6Unit.setBounds(143, 118, 19, 13);
		panelCircuit.add(labelElement6Unit);

		panelFormula = new JPanel();
		panelFormula.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFormula.setBounds(512, 41, 164, 178);
		contentPane.add(panelFormula);

		labelCircuit = new JLabel("回路");
		labelCircuit.setBounds(0, 28, 340, 13);
		contentPane.add(labelCircuit);

		labelElementList = new JLabel("素子リスト");
		labelElementList.setBounds(339, 28, 173, 13);

		contentPane.add(labelElementList);

		labelFormula = new JLabel("数式");
		labelFormula.setBounds(512, 28, 164, 13);
		contentPane.add(labelFormula);
		
		panelElementList = new JPanel();
		panelElementList.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelElementList.setBounds(339, 41, 173, 178);
		contentPane.add(panelElementList);
		panelElementList.setLayout(null);
		
		scrollPaneElementList = new JScrollPane();
		scrollPaneElementList.setBounds(1, 1, 171, 176);
		panelElementList.add(scrollPaneElementList);
				
		scrollPanelElementList = new JPanel();
		scrollPaneElementList.setViewportView(scrollPanelElementList);
		scrollPanelElementList.setLayout(null);
	}



	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
