package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import circuit.Circuit;
import circuit.ElemType;
import circuit.SeriesCircuit;


public class MainDispApp extends JFrame
{
	public JTextField textFieldElement1;
	public JTextField textFieldElement2;
	public JTextField textFieldElement5;
	public JTextField textFieldElement6;
	public JTextField textFieldElement3;
	public JTextField textFieldElement4;
	public JTextField textFieldElement1Parallel;
	public JTextField textFieldElement2Parallel;
	public JTextField textFieldElement5Parallel;
	public JTextField textFieldElement6Parallel;
	public JTextField textFieldElement3Parallel;
	public JTextField textFieldElement4Parallel;
	public JLabel labelCircuitPicture;
	public JPanel contentPane;
	public JMenuBar menuBar;
	public JMenu meuDisp;
	public JMenu menuSimulate;
	public JMenu menuHelp;
	public JMenu menuEdit;
	public JMenu menuFile;
	public JMenuBar menuBarButtons;
	public JPanel panelGraph;
	public JLabel labelGraph;
	public JPanel panelCircuit;
	public JTextField textFieldVoltage;
	public JTextField textFieldVoltageParallel;
	public JLabel labelElement1;
	public JLabel labelElement2;
	public JLabel labelElement3;
	public JLabel labelElement6;
	public JLabel labelElement5;
	public JLabel labelElement4;
	public JLabel labelElement1Parallel;
	public JLabel labelElement2Parallel;
	public JLabel labelElement3Parallel;
	public JLabel labelElement6Parallel;
	public JLabel labelElement5Parallel;
	public JLabel labelElement4Parallel;
	public JPanel panelFormula;
	public JLabel labelCircuit;
	public JLabel labelElementList;
	public JLabel labelFormula;
	public JPanel panelElementList;
	public JScrollPane scrollPaneElementList;
	public JPanel scrollPanelElementListPanel;
	public JMenuItem menuItemNew;
	public JMenuItem menuItemOpen;
	public JMenuItem menuItemOverWrite;
	public JMenuItem menuItemSaveAs;
	public JMenuItem menuItemExportGraph;
	public JMenuItem menuItemClose;
	public JMenuItem menuItemCircuitGraph;
	public JMenuItem menuItemFormula;
	public JMenuItem menuItemElement;
	public JMenuItem menuItemGraph;
	public JMenuItem menuItemRedo;
	public JMenuItem menuItemUndo;
	public JMenuItem menuItemCopy;
	public JMenuItem menuItemCut;
	public JMenuItem menuItemPaste;
	public JMenuItem menuItemAllClear;
	public JMenuItem menuItemHelpViewer;
	public JMenuItem menuItemSimulation;
	public JButton buttonRedo;
	public JButton buttonUndo;
	public JButton buttonOpen;
	public JButton buttonSave;
	public JButton buttonStart;
	public JButton buttonEnd;
	public ImageIcon seriesCircuitPicture;
	public ImageIcon parallelCircuitPicture;
	public ImageIcon linePicture;
	public ImageIcon resistancePicture;
	public ImageIcon inductancePicture;
	public ImageIcon capacitancePicture;
	public ImageIcon linePictureV;
	public ImageIcon resistancePictureV;
	public ImageIcon inductancePictureV;
	public ImageIcon capacitancePictureV;
	public JLabel labelElement1Unit;
	public JLabel labelElement2Unit;
	public JLabel labelElement3Unit;
	public JLabel labelElement4Unit;
	public JLabel labelElement5Unit;
	public JLabel labelElement6Unit;
	public JLabel labelVoltageUnit;
	public JLabel labelElement1UnitParallel;
	public JLabel labelElement2UnitParallel;
	public JLabel labelElement3UnitParallel;
	public JLabel labelElement4UnitParallel;
	public JLabel labelElement5UnitParallel;
	public JLabel labelElement6UnitParallel;
	public JLabel labelVoltageUnitParallel;
	public JLabel labelLineDisp;
	public JLabel labelInductanceDisp;
	public JLabel labelCapacitanceDisp;
	public JLabel labelResistanceDisp;

	public Circuit mainCircuit;
	public JMenu menuCircuitSelect;
	public JMenuItem menuItemSeriesCircuit;
	public JMenuItem menuItemParallelCircuit;
	public JLabel labelElement7Parallel;
	public JLabel labelElement8Parallel;

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
		parallelCircuitPicture = new ImageIcon(MainDispApp.class.getResource("/resources/PrallelCircuit.png"));
		linePicture = new ImageIcon(MainDispApp.class.getResource("/resources/Line.png"));
		resistancePicture = new ImageIcon(MainDispApp.class.getResource("/resources/resistance.png"));
		inductancePicture = new ImageIcon(MainDispApp.class.getResource("/resources/Inductance.png"));
		capacitancePicture = new ImageIcon(MainDispApp.class.getResource("/resources/Cpacitance.png"));
		linePictureV = new ImageIcon(MainDispApp.class.getResource("/resources/LineV.png"));
		resistancePictureV = new ImageIcon(MainDispApp.class.getResource("/resources/resistanceV.png"));
		inductancePictureV = new ImageIcon(MainDispApp.class.getResource("/resources/InductanceV.png"));
		capacitancePictureV = new ImageIcon(MainDispApp.class.getResource("/resources/CpacitanceV.png"));

		mainCircuit = new SeriesCircuit();
		mainCircuit.setElem(0, 1, ElemType.RESISTANCE);
		mainCircuit.setElem(1, 1, ElemType.CAPACITANCE);
		mainCircuit.setElem(2, 1, ElemType.INDUCTANCE);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 577);

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

		menuCircuitSelect = new JMenu("回路の種類");
		menuBar.add(menuCircuitSelect);

		menuItemSeriesCircuit = new JMenuItem("直列回路");
		menuItemSeriesCircuit.addMouseListener(new CircuitChangeEvent(this));
		menuCircuitSelect.add(menuItemSeriesCircuit);

		menuItemParallelCircuit = new JMenuItem("並列回路");
		menuItemParallelCircuit.addMouseListener(new CircuitChangeEvent(this));
		menuCircuitSelect.add(menuItemParallelCircuit);
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
		panelGraph.setBounds(0, 320, 706, 180);
		contentPane.add(panelGraph);

		labelGraph = new JLabel("波形");
		labelGraph.setBounds(0, 304, 676, 13);
		contentPane.add(labelGraph);

		panelCircuit = new JPanel();
		panelCircuit.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCircuit.setBounds(0, 41, 360, 250);
		contentPane.add(panelCircuit);

		panelCircuit.setLayout(null);

		//*************並列回路の設計*****************//

		textFieldVoltageParallel = new JTextField();
		textFieldVoltageParallel.setBounds(66, 113, 36, 19);
		panelCircuit.add(textFieldVoltageParallel);
		textFieldVoltageParallel.setColumns(10);
		textFieldVoltageParallel.setVisible(false);

		textFieldElement2Parallel = new JTextField();
		textFieldElement2Parallel.setColumns(10);
		textFieldElement2Parallel.setBounds(153, 79, 36, 19);
		textFieldElement2Parallel.setVisible(false);
		panelCircuit.add(textFieldElement2Parallel);

		textFieldElement1Parallel = new JTextField();
		textFieldElement1Parallel.setBounds(88, 80, 36, 19);
		panelCircuit.add(textFieldElement1Parallel);
		textFieldElement1Parallel.setColumns(10);
		textFieldElement1Parallel.setVisible(false);

		textFieldElement5Parallel = new JTextField();
		textFieldElement5Parallel.setColumns(10);
		textFieldElement5Parallel.setBounds(156, 148, 36, 19);
		panelCircuit.add(textFieldElement5Parallel);
		textFieldElement5Parallel.setVisible(false);

		textFieldElement6Parallel = new JTextField();
		textFieldElement6Parallel.setColumns(10);
		textFieldElement6Parallel.setBounds(84, 149, 36, 19);
		panelCircuit.add(textFieldElement6Parallel);
		textFieldElement6Parallel.setVisible(false);

		textFieldElement3Parallel = new JTextField();
		textFieldElement3Parallel.setColumns(10);
		textFieldElement3Parallel.setBounds(175, 126, 36, 19);
		panelCircuit.add(textFieldElement3Parallel);
		textFieldElement3Parallel.setVisible(false);

		textFieldElement4Parallel = new JTextField();
		textFieldElement4Parallel.setColumns(10);
		textFieldElement4Parallel.setBounds(176, 101, 36, 19);
		panelCircuit.add(textFieldElement4Parallel);
		textFieldElement4Parallel.setVisible(false);

		labelElement1Parallel = new JLabel(resistancePicture);
		labelElement1Parallel.setBounds(89, 47, 43, 29);
		panelCircuit.add(labelElement1Parallel);
		labelElement1Parallel.setVisible(false);

		labelElement2Parallel = new JLabel(capacitancePicture);
		labelElement2Parallel.setBounds(155, 47, 43, 29);
		panelCircuit.add(labelElement2Parallel);
		labelElement2Parallel.setVisible(false);

		labelElement3Parallel = new JLabel(inductancePictureV);
		labelElement3Parallel.setBounds(306, 77, 29, 43);
		panelCircuit.add(labelElement3Parallel);
		labelElement3Parallel.setVisible(false);

		labelElement4Parallel = new JLabel(linePictureV);
		labelElement4Parallel.setBounds(306, 129, 29, 43);
		panelCircuit.add(labelElement4Parallel);
		labelElement4Parallel.setVisible(false);

		labelElement5Parallel = new JLabel(linePicture);
		labelElement5Parallel.setBounds(157, 173, 43, 29);
		panelCircuit.add(labelElement5Parallel);
		labelElement5Parallel.setVisible(false);

		labelElement6Parallel = new JLabel(linePicture);
		labelElement6Parallel.setBounds(87, 173, 44, 29);
		panelCircuit.add(labelElement6Parallel);
		labelElement6Parallel.setVisible(false);

		labelElement7Parallel = new JLabel(resistancePictureV);
		labelElement7Parallel.setBounds(230, 76, 29, 43);
		panelCircuit.add(labelElement7Parallel);
		labelElement7Parallel.setVisible(false);

		labelElement8Parallel = new JLabel(resistancePictureV);
		labelElement8Parallel.setBounds(231, 130, 29, 43);
		panelCircuit.add(labelElement8Parallel);
		labelElement8Parallel.setVisible(false);

		labelVoltageUnitParallel = new JLabel("V");
		labelVoltageUnitParallel.setBounds(105, 119, 19, 13);
		panelCircuit.add(labelVoltageUnitParallel);
		labelVoltageUnitParallel.setVisible(false);

		labelElement1UnitParallel = new JLabel("Ω");
		labelElement1UnitParallel.setBounds(125, 83, 19, 13);
		panelCircuit.add(labelElement1UnitParallel);
		labelElement1UnitParallel.setVisible(false);

		labelElement2UnitParallel = new JLabel("Ω");
		labelElement2UnitParallel.setBounds(190, 82, 19, 13);
		panelCircuit.add(labelElement2UnitParallel);
		labelElement2UnitParallel.setVisible(false);

		labelElement3UnitParallel = new JLabel("Ω");
		labelElement3UnitParallel.setBounds(211, 129, 19, 13);
		panelCircuit.add(labelElement3UnitParallel);
		labelElement3UnitParallel.setVisible(false);

		labelElement4UnitParallel = new JLabel("Ω");
		labelElement4UnitParallel.setBounds(215, 105, 19, 13);
		panelCircuit.add(labelElement4UnitParallel);
		labelElement4UnitParallel.setVisible(false);

		labelElement5UnitParallel = new JLabel("Ω");
		labelElement5UnitParallel.setBounds(193, 152, 19, 13);
		panelCircuit.add(labelElement5UnitParallel);
		labelElement5UnitParallel.setVisible(false);

		labelElement6UnitParallel = new JLabel("Ω");
		labelElement6UnitParallel.setBounds(122, 154, 19, 13);
		panelCircuit.add(labelElement6UnitParallel);
		labelElement6UnitParallel.setVisible(false);
		//******************************************//

		textFieldVoltage = new JTextField();
		textFieldVoltage.setBounds(63, 115, 36, 19);
		panelCircuit.add(textFieldVoltage);
		textFieldVoltage.setColumns(10);

		textFieldElement2 = new JTextField();
		textFieldElement2.setColumns(10);
		textFieldElement2.setBounds(202, 83, 36, 19);
		panelCircuit.add(textFieldElement2);

		textFieldElement1 = new JTextField();
		textFieldElement1.setBounds(123, 83, 36, 19);
		panelCircuit.add(textFieldElement1);
		textFieldElement1.setColumns(10);

		textFieldElement5 = new JTextField();
		textFieldElement5.setColumns(10);
		textFieldElement5.setBounds(204, 152, 36, 19);
		panelCircuit.add(textFieldElement5);

		textFieldElement6 = new JTextField();
		textFieldElement6.setColumns(10);
		textFieldElement6.setBounds(106, 150, 36, 19);
		panelCircuit.add(textFieldElement6);

		textFieldElement3 = new JTextField();
		textFieldElement3.setColumns(10);
		textFieldElement3.setBounds(242, 106, 36, 19);
		panelCircuit.add(textFieldElement3);

		textFieldElement4 = new JTextField();
		textFieldElement4.setColumns(10);
		textFieldElement4.setBounds(243, 130, 36, 19);
		panelCircuit.add(textFieldElement4);

		labelElement1 = new JLabel(resistancePicture);
		labelElement1.setBounds(110, 48, 43, 29);
		panelCircuit.add(labelElement1);

		labelElement2 = new JLabel(capacitancePicture);
		labelElement2.setBounds(204, 48, 43, 29);
		panelCircuit.add(labelElement2);

		labelElement3 = new JLabel(inductancePictureV);
		labelElement3.setBounds(306, 77, 29, 43);
		panelCircuit.add(labelElement3);

		labelElement4 = new JLabel(linePictureV);
		labelElement4.setBounds(306, 130, 29, 43);
		panelCircuit.add(labelElement4);

		labelElement5 = new JLabel(linePicture);
		labelElement5.setBounds(204, 173, 43, 29);
		panelCircuit.add(labelElement5);

		labelElement6 = new JLabel(linePicture);
		labelElement6.setBounds(110, 173, 44, 29);
		panelCircuit.add(labelElement6);

		labelVoltageUnit = new JLabel("V");
		labelVoltageUnit.setBounds(102, 117, 19, 13);
		panelCircuit.add(labelVoltageUnit);

		labelElement1Unit = new JLabel("Ω");
		labelElement1Unit.setBounds(164, 84, 19, 13);
		panelCircuit.add(labelElement1Unit);

		labelElement2Unit = new JLabel("Ω");
		labelElement2Unit.setBounds(240, 86, 19, 13);
		panelCircuit.add(labelElement2Unit);

		labelElement3Unit = new JLabel("Ω");
		labelElement3Unit.setBounds(282, 107, 19, 13);
		panelCircuit.add(labelElement3Unit);

		labelElement4Unit = new JLabel("Ω");
		labelElement4Unit.setBounds(283, 134, 19, 13);
		panelCircuit.add(labelElement4Unit);

		labelElement5Unit = new JLabel("Ω");
		labelElement5Unit.setBounds(243, 156, 19, 13);
		panelCircuit.add(labelElement5Unit);

		labelElement6Unit = new JLabel("Ω");
		labelElement6Unit.setBounds(147, 154, 19, 13);
		panelCircuit.add(labelElement6Unit);

		labelCircuitPicture = new JLabel(seriesCircuitPicture);
		labelCircuitPicture.setBounds(1, 1, 359, 249);
		panelCircuit.add(labelCircuitPicture);

		panelFormula = new JPanel();
		panelFormula.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFormula.setBounds(552, 41, 152, 250);
		contentPane.add(panelFormula);
		panelFormula.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(1, 1, 150, 248);
		panelFormula.add(textArea);

		labelCircuit = new JLabel("回路");
		labelCircuit.setBounds(0, 28, 340, 13);
		contentPane.add(labelCircuit);

		labelElementList = new JLabel("素子リスト");
		labelElementList.setBounds(367, 28, 154, 13);

		contentPane.add(labelElementList);

		labelFormula = new JLabel("数式");
		labelFormula.setBounds(552, 28, 144, 13);
		contentPane.add(labelFormula);

		panelElementList = new JPanel();
		panelElementList.setBorder(null);
		panelElementList.setBounds(367, 41, 178, 250);
		contentPane.add(panelElementList);
		panelElementList.setLayout(null);

		scrollPaneElementList = new JScrollPane();
		scrollPaneElementList.setViewportBorder(null);
		scrollPaneElementList.setBounds(0, 0, 176, 250);
		panelElementList.add(scrollPaneElementList);

		scrollPanelElementListPanel = new JPanel();
		scrollPanelElementListPanel.setBorder(null);
		scrollPaneElementList.setViewportView(scrollPanelElementListPanel);

		labelCapacitanceDisp = new JLabel(new ImageIcon(MainDispApp.class.getResource("/resources/CpacitanceDisp.png")));
		labelCapacitanceDisp.setAlignmentY(Component.TOP_ALIGNMENT);

		labelResistanceDisp = new JLabel(new ImageIcon(MainDispApp.class.getResource("/resources/resisitanceDisp.png")));
		labelResistanceDisp.setAlignmentY(Component.TOP_ALIGNMENT);

		labelInductanceDisp = new JLabel(new ImageIcon(MainDispApp.class.getResource("/resources/InductanceDisp.png")));
		labelInductanceDisp.setAlignmentY(Component.TOP_ALIGNMENT);

		labelLineDisp = new JLabel(new ImageIcon(MainDispApp.class.getResource("/resources/LineDisp.png")));
		labelLineDisp.setAlignmentY(100.0f);
		GroupLayout gl_scrollPanelElementListPanel = new GroupLayout(scrollPanelElementListPanel);
		gl_scrollPanelElementListPanel.setHorizontalGroup(
			gl_scrollPanelElementListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_scrollPanelElementListPanel.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_scrollPanelElementListPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_scrollPanelElementListPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(labelLineDisp)
							.addGap(5)
							.addComponent(labelResistanceDisp)
							.addGap(35))
						.addGroup(Alignment.TRAILING, gl_scrollPanelElementListPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(labelCapacitanceDisp)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(labelInductanceDisp, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_scrollPanelElementListPanel.setVerticalGroup(
			gl_scrollPanelElementListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_scrollPanelElementListPanel.createSequentialGroup()
					.addGroup(gl_scrollPanelElementListPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_scrollPanelElementListPanel.createSequentialGroup()
							.addComponent(labelResistanceDisp)
							.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_scrollPanelElementListPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelLineDisp)
							.addGap(5)))
					.addGroup(gl_scrollPanelElementListPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_scrollPanelElementListPanel.createSequentialGroup()
							.addComponent(labelInductanceDisp, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_scrollPanelElementListPanel.createSequentialGroup()
							.addComponent(labelCapacitanceDisp)
							.addGap(17))))
		);
		scrollPanelElementListPanel.setLayout(gl_scrollPanelElementListPanel);
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
