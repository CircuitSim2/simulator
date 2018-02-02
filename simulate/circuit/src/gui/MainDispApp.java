package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import circuit.Circuit;
import circuit.ElemType;
import circuit.ParallelCircuit;
import circuit.SeriesCircuit;
import help.Openhelp;


public class MainDispApp extends JFrame
{
	//メイン画面のペーン(大枠)
	public JPanel contentPane;

	//回路エリアのラベル
	public JLabel labelCircuit;
	//回路エリアを表示するパネル
	public JPanel panelCircuit;

	//素子の値を入力するBOX
	public JFormattedTextField textFieldElement[];
	//並列用の素子の値を入力するBOX
	public JFormattedTextField textFieldElementParallel[];

	//素子の画像を表示するラベル
	public JLabel labelElement[];
	//素子の画像を表示するラベル(並列用)
	public JLabel labelElementParallel[];

	//素子の単位を表示するラベル
	public JLabel labelElementUnit[];
	//素子の単位を表示するラベル(並列用)
	public JLabel labelElementUnitParallel[];

	//電圧を入力するBOX
	public JTextField textFieldVoltage;
	//電圧を入力するBOX並列用
	public JTextField textFieldVoltageParallel;
	//電圧の単位を表示するラベル
	public JLabel labelVoltageUnit;
	//電圧の単位を表示するラベル(並列用)
	public JLabel labelVoltageUnitParallel;

	//回路全体の画像を表示するラベル
	public JLabel labelCircuitPicture;

	//メニューバー
	public JMenuBar menuBar;

	//編集メニュー
	public JMenu menuEdit;
	//戻る
	public JMenuItem menuItemRedo;
	//進む
	public JMenuItem menuItemUndo;
	//コピー
	public JMenuItem menuItemCopy;
	//切り取り
	public JMenuItem menuItemCut;
	//貼り付け
	public JMenuItem menuItemPaste;
	//全消去
	public JMenuItem menuItemAllClear;

	//ファイルメニュー
	public JMenu menuFile;
	//新規作成
	public JMenuItem menuItemNew;
	//開く
	public JMenuItem menuItemOpen;
	//上書き保存
	public JMenuItem menuItemOverWrite;
	//名前を付けて保存
	public JMenuItem menuItemSaveAs;
	//グラフの出力
	public JMenuItem menuItemExportGraph;
	//閉じる
	public JMenuItem menuItemClose;

	//実行メニュー
	public JMenu menuSimulate;
	//シミュレーション
	public JMenuItem menuItemSimulation;

	//ヘルプメニュー
	public JMenu menuHelp;
	//ヘルプビューワ
	public JMenuItem menuItemHelpViewer;

	//回路の種類メニュー
	public JMenu menuCircuitSelect;
	//直列回路
	public JMenuItem menuItemSeriesCircuit;
	//並列回路
	public JMenuItem menuItemParallelCircuit;

	//メニューバー(ボタンの方)
	public JMenuBar menuBarButtons;
	//戻るボタン
	public JButton buttonRedo;
	//進むボタン
	public JButton buttonUndo;
	//開くボタン
	public JButton buttonOpen;
	//保存ボタン
	public JButton buttonSave;
	//開始ボタン
	public JButton buttonStart;
	//終了ボタン
	public JButton buttonEnd;

	//グラフエリアのラベル
	public JLabel labelGraph;
	//グラフエリアを表示するパネル
	public ChartPanel panelGraph;
	//表示するグラフ
	public JFreeChart chart;

	//数式エリアのラベル
	public JLabel labelFormula;
	//数式エリアを表示するパネル
	public JPanel panelFormula;

	//素子リストを表示するパネル
	public JPanel panelElementList;
	//素子リストのラベル
	public JLabel labelElementList;

	//素子リストのスクロールバー用
	public JScrollPane scrollPaneElementList;
	//素子リスト用のテーブル
	public JTable table;

	//直列回路の画像
	public ImageIcon seriesCircuitPicture;
	//並列回路の画像
	public ImageIcon parallelCircuitPicture;
	//線の画像
	public ImageIcon linePicture;
	//線の画像(縦)
	public ImageIcon linePictureV;
	//抵抗の画像
	public ImageIcon resistancePicture;
	//抵抗の画像(縦)
	public ImageIcon resistancePictureV;
	//インダクタの画像
	public ImageIcon inductancePicture;
	//インダクタの画像(縦)
	public ImageIcon inductancePictureV;
	//キャパシタの画像
	public ImageIcon capacitancePicture;
	//キャパシタの画像(縦)
	public ImageIcon capacitancePictureV;

	//数式のテキストエリア
	JTextArea textArea = new JTextArea();

	//回路の情報を保持するクラス
	public Circuit mainCircuit;

	//回路のファイルのパスを保持するフィールド
	public String filePass = "empty";

	String formula = null;

	//素子リストの表のデータ
	private Object[][] tabledata={{new ImageIcon(MainDispApp.class.getResource("/resources/CapacitanceDisp.png")),"キャパシタ"},
			                      {new ImageIcon(MainDispApp.class.getResource("/resources/resisitanceDisp.png")),"抵抗"},
			                      {new ImageIcon(MainDispApp.class.getResource("/resources/InductanceDisp.png")),"インダクタ"},
			                      {new ImageIcon(MainDispApp.class.getResource("/resources/LineDisp.png")),"ライン"}};

	private String[] columnNames = {"IMAGE","NAME"};

	//回路の電流か電圧の選択ボタン
	public JRadioButton radioButtonCurrent;
	public JRadioButton radioButtonVoltage;
	public JRadioButton radioButtonSource;

	//シミュレーションモードかどうか
	public boolean isSimulationMode;

	//マウスがどの素子の枠に乗っているか
	public int selectedElemNum;

	//選ばれたテキストボックスの番号
	public int selectedTextField;

	//戻ると進む機能
	public RedoAndUndo redoAndUndo;

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

		setTitle("SpicEasy");

		//必要画像の読み込み
		seriesCircuitPicture = new ImageIcon(MainDispApp.class.getResource("/resources/SeriesCircuit.png"));
		parallelCircuitPicture = new ImageIcon(MainDispApp.class.getResource("/resources/ParallelCircuit.png"));
		//線
		linePicture = new ImageIcon(MainDispApp.class.getResource("/resources/Line.png"));
		linePictureV = new ImageIcon(MainDispApp.class.getResource("/resources/LineV.png"));
		//抵抗
		resistancePicture = new ImageIcon(MainDispApp.class.getResource("/resources/resistance.png"));
		resistancePictureV = new ImageIcon(MainDispApp.class.getResource("/resources/resistanceV.png"));
		//インダクタ
		inductancePicture = new ImageIcon(MainDispApp.class.getResource("/resources/Inductance.png"));
		inductancePictureV = new ImageIcon(MainDispApp.class.getResource("/resources/InductanceV.png"));
		//キャパシタ
		capacitancePicture = new ImageIcon(MainDispApp.class.getResource("/resources/Capacitance.png"));
		capacitancePictureV = new ImageIcon(MainDispApp.class.getResource("/resources/CapacitanceV.png"));

		this.setIconImage(new ImageIcon(MainDispApp.class.getResource("/resources/character.png")).getImage());

		//回路情報の生成
		mainCircuit = new SeriesCircuit();
		mainCircuit.setVoltage(10);
		mainCircuit.setElem(0, 1, ElemType.RESISTANCE);
		//mainCircuit.setElem(1, 1, ElemType.CAPACITANCE);
		//mainCircuit.setElem(2, 1, ElemType.INDUCTANCE);
		//mainCircuit.setElem(3, 1, ElemType.RESISTANCE);
		//mainCircuit.setElem(4, 1, ElemType.CAPACITANCE);
		//mainCircuit.setElem(5, 1, ElemType.INDUCTANCE);
		//mainCircuit.setElem(6, 1, ElemType.RESISTANCE);
		//mainCircuit.setElem(6, 1, ElemType.CAPACITANCE);
		//mainCircuit.setElem(8, 1, ElemType.INDUCTANCE);

		//モードの初期化
		isSimulationMode = false;

		//マウスがのっている素子
		selectedElemNum = -1;

		selectedTextField = 0;

		redoAndUndo = new RedoAndUndo();

		//×ボタンを押したら閉じるように
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//ウィンドウサイズの決定
		setBounds(100, 100, 730, 577);

		//コンテントペーン(大枠)の生成
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//メニューバーの生成
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		//ファイルメニュ―の生成
		menuFile = new JMenu("ファイル");
		menuBar.add(menuFile);
		//新規作成の生成
		menuItemNew = new JMenuItem("新規作成");
		menuFile.add(menuItemNew);
		menuItemNew.addActionListener(new FileFunction(this));

		//開くの生成
		menuItemOpen = new JMenuItem("開く");
		menuFile.add(menuItemOpen);
		menuItemOpen.addActionListener(new FileFunction(this));

		//上書きの生成
		menuItemOverWrite = new JMenuItem("上書き保存");
		menuFile.add(menuItemOverWrite);
		menuItemOverWrite.addActionListener(new FileFunction(this));

		//名前を付けて保存の生成
		menuItemSaveAs = new JMenuItem("名前を付けて保存");
		menuFile.add(menuItemSaveAs);
		menuItemSaveAs.addActionListener(new FileFunction(this));

		//波形の出力の生成
		menuItemExportGraph = new JMenuItem("波形を画像として保存");
		menuFile.add(menuItemExportGraph);
		menuItemExportGraph.addActionListener(new FileFunction(this));

		//閉じるの生成
		menuItemClose = new JMenuItem("閉じる");
		menuFile.add(menuItemClose);
		menuItemClose.addActionListener(new FileFunction());

		//編集メニューの生成
		menuEdit = new JMenu("編集");
		menuBar.add(menuEdit);
		//戻るの生成
		menuItemRedo = new JMenuItem("戻る");
		menuItemRedo.addActionListener(new RedoUndoEvent(this));
		menuEdit.add(menuItemRedo);
		//進むの生成
		menuItemUndo = new JMenuItem("進む");
		menuItemUndo.addActionListener(new RedoUndoEvent(this));
		menuEdit.add(menuItemUndo);
		//コピーの生成
		menuItemCopy = new JMenuItem("コピー");
		menuItemCopy.addActionListener(new Hensyu(this));
		menuEdit.add(menuItemCopy);
		//切り取りの生成
		menuItemCut = new JMenuItem("切り取り");
		menuItemCut.addActionListener(new Hensyu(this));
		menuEdit.add(menuItemCut);
		//ペーストの生成
		menuItemPaste = new JMenuItem("ペースト");
		menuItemPaste.addActionListener(new Hensyu(this));
		menuEdit.add(menuItemPaste);
		//全消去の生成
		menuItemAllClear = new JMenuItem("全消去");
		menuItemAllClear.addActionListener(new CircuitChangeEvent(this));
		menuEdit.add(menuItemAllClear);

		//ヘルプメニューの生成
		menuHelp = new JMenu("ヘルプ");
		menuBar.add(menuHelp);
		//ヘルプビューワアイテムの生成
		menuItemHelpViewer = new JMenuItem("ヘルプビューア");
		menuHelp.add(menuItemHelpViewer);
		menuItemHelpViewer.addActionListener(new Openhelp());

		//実行メニューの生成
		menuSimulate = new JMenu("実行");
		menuBar.add(menuSimulate);
		//シミュレーションの生成
		menuItemSimulation = new JMenuItem("シミュレーション");
		menuItemSimulation.addActionListener(new SimulationSettingEvent(this));
		menuSimulate.add(menuItemSimulation);

		//回路の種類メニューの生成
		menuCircuitSelect = new JMenu("回路の種類");
		menuBar.add(menuCircuitSelect);
		//直列回路の生成
		menuItemSeriesCircuit = new JMenuItem("直列回路");
		menuItemSeriesCircuit.addActionListener(new CircuitChangeEvent(this));
		menuCircuitSelect.add(menuItemSeriesCircuit);
		//並列回路の生成
		menuItemParallelCircuit = new JMenuItem("並列回路");
		menuItemParallelCircuit.addActionListener(new CircuitChangeEvent(this));
		menuCircuitSelect.add(menuItemParallelCircuit);

		//メニューバー(ボタン)
		menuBarButtons = new JMenuBar();
		menuBarButtons.setBounds(0, 0, 676, 21);
		contentPane.add(menuBarButtons);
		//戻るボタンの生成
		buttonRedo = new JButton("戻る");
		buttonRedo.addActionListener(new RedoUndoEvent(this));
		buttonRedo.addKeyListener(new ShortcutKeyEvent(this));
		menuBarButtons.add(buttonRedo);
		//進むボタンの生成
		buttonUndo = new JButton("進む");
		buttonUndo.addActionListener(new RedoUndoEvent(this));
		buttonUndo.addKeyListener(new ShortcutKeyEvent(this));
		menuBarButtons.add(buttonUndo);
		//開くボタンの生成
		buttonOpen = new JButton("開く");
		buttonOpen.addKeyListener(new ShortcutKeyEvent(this));
		menuBarButtons.add(buttonOpen);
		buttonOpen.addActionListener(new FileFunction(this));

		//保存ボタンの生成
		buttonSave = new JButton("保存");
		buttonSave.addKeyListener(new ShortcutKeyEvent(this));
		menuBarButtons.add(buttonSave);
		buttonSave.addActionListener(new FileFunction(this));

		//シミュレーション開始ボタンの生成
		buttonStart = new JButton("シミュレーション開始");
		buttonStart.addKeyListener(new ShortcutKeyEvent(this));
		buttonStart.addActionListener(new SimulationSettingEvent(this));
		menuBarButtons.add(buttonStart);
		//シミュレーション終了ボタンの生成
		buttonEnd = new JButton("シミュレーション終了");
		buttonEnd.addKeyListener(new ShortcutKeyEvent(this));
		menuBarButtons.add(buttonEnd);

		chart = ChartFactory.createXYLineChart(
						null,                     // chart title
						"Time[s]",                // x axis label
						null,    // y axis label
						null,                     // data
						PlotOrientation.VERTICAL,
						true,                     // include legend
						true,                     // tooltips
						false                     // urls
						);

		//グラフエリアを表示するパネルを生成
		panelGraph = new ChartPanel(chart);
		panelGraph.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGraph.setBounds(0, 320, 706, 180);
		contentPane.add(panelGraph);
		//グラフエリアのラベルを生成
		labelGraph = new JLabel("波形");
		labelGraph.setBounds(0, 304, 30, 13);
		contentPane.add(labelGraph);


		//回路エリアを表示するパネルを生成
		panelCircuit = new JPanel();
		panelCircuit.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCircuit.setBounds(0, 41, 360, 250);
		contentPane.add(panelCircuit);
		panelCircuit.setLayout(null);
		//回路エリアのラベルを生成
		labelCircuit = new JLabel("回路");
		labelCircuit.setBounds(0, 28, 340, 13);
		contentPane.add(labelCircuit);

		//*************並列回路の設計*****************//
		//電圧の値を入力するボックスの生成
		textFieldVoltageParallel = new JTextField();
		textFieldVoltageParallel.setBounds(66, 113, 36, 19);
		textFieldVoltageParallel.addKeyListener(new ShortcutKeyEvent(this));
		textFieldVoltageParallel.addCaretListener(new textFieldCaretEvent(this));
		panelCircuit.add(textFieldVoltageParallel);
		textFieldVoltageParallel.setColumns(10);

		//素子の値を入力するBOXの配列の生成
		textFieldElementParallel = new JFormattedTextField[9];

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(20);

		//素子1の値を入力するBOXの生成
		textFieldElementParallel[0] = new JFormattedTextField(nf);
		textFieldElementParallel[0].setBounds(56, 50, 36, 19);
		textFieldElementParallel[0].addKeyListener(new ShortcutKeyEvent(this));
		textFieldElementParallel[0].addCaretListener(new textFieldCaretEvent(this));
		//textFieldElementParallel[0].setFormatterFactory(new NumberFormatterFactory());
		panelCircuit.add(textFieldElementParallel[0]);
		//素子2の値を入力するBOXの生成
		textFieldElementParallel[1] = new JFormattedTextField(nf);
		//textFieldElementParallel[1].setFormatterFactory(new NumberFormatterFactory());
		textFieldElementParallel[1].setBounds(112, 49, 36, 19);
		textFieldElementParallel[1].addCaretListener(new textFieldCaretEvent(this));
		textFieldElementParallel[1].addKeyListener(new ShortcutKeyEvent(this));
		panelCircuit.add(textFieldElementParallel[1]);
		//素子3の値を入力するBOXの生成
		textFieldElementParallel[2] = new JFormattedTextField(nf);
		//textFieldElementParallel[2].setFormatterFactory(new NumberFormatterFactory());
		textFieldElementParallel[2].setBounds(168, 50, 36, 19);
		textFieldElementParallel[2].addKeyListener(new ShortcutKeyEvent(this));
		textFieldElementParallel[2].addCaretListener(new textFieldCaretEvent(this));
		panelCircuit.add(textFieldElementParallel[2]);
		//素子4の値を入力するBOXの生成
		textFieldElementParallel[3] = new JFormattedTextField(nf);
		//textFieldElementParallel[3].setFormatterFactory(new NumberFormatterFactory());
		textFieldElementParallel[3].setBounds(157, 74, 36, 19);
		textFieldElementParallel[3].addCaretListener(new textFieldCaretEvent(this));
		textFieldElementParallel[3].addKeyListener(new ShortcutKeyEvent(this));
		panelCircuit.add(textFieldElementParallel[3]);
		//素子5の値を入力するBOXの生成
		textFieldElementParallel[4] = new JFormattedTextField(nf);
		//textFieldElementParallel[4].setFormatterFactory(new NumberFormatterFactory());
		textFieldElementParallel[4].setBounds(157, 123, 36, 19);
		textFieldElementParallel[4].addCaretListener(new textFieldCaretEvent(this));
		textFieldElementParallel[4].addKeyListener(new ShortcutKeyEvent(this));
		panelCircuit.add(textFieldElementParallel[4]);
		//素子6の値を入力するBOXの生成
		textFieldElementParallel[5] = new JFormattedTextField(nf);
		//textFieldElementParallel[5].setFormatterFactory(new NumberFormatterFactory());
		textFieldElementParallel[5].setBounds(157, 174, 36, 19);
		textFieldElementParallel[5].addCaretListener(new textFieldCaretEvent(this));
		textFieldElementParallel[5].addKeyListener(new ShortcutKeyEvent(this));
		panelCircuit.add(textFieldElementParallel[5]);
		//素子7の値を入力するBOXの生成
		textFieldElementParallel[6] = new JFormattedTextField(nf);
		//textFieldElementParallel[6].setFormatterFactory(new NumberFormatterFactory());
		textFieldElementParallel[6].setBounds(251, 60, 36, 19);
		textFieldElementParallel[6].addCaretListener(new textFieldCaretEvent(this));
		textFieldElementParallel[6].addKeyListener(new ShortcutKeyEvent(this));
		panelCircuit.add(textFieldElementParallel[6]);
		//素子8の値を入力するBOXの生成
		textFieldElementParallel[7] = new JFormattedTextField(nf);
		//textFieldElementParallel[7].setFormatterFactory(new NumberFormatterFactory());
		textFieldElementParallel[7].setBounds(252, 120, 36, 19);
		textFieldElementParallel[7].addCaretListener(new textFieldCaretEvent(this));
		textFieldElementParallel[7].addKeyListener(new ShortcutKeyEvent(this));
		panelCircuit.add(textFieldElementParallel[7]);
		//素子9の値を入力するBOXの生成
		textFieldElementParallel[8] = new JFormattedTextField(nf);
		//textFieldElementParallel[8].setFormatterFactory(new NumberFormatterFactory());
		textFieldElementParallel[8].setBounds(250, 175, 36, 19);
		textFieldElementParallel[8].addCaretListener(new textFieldCaretEvent(this));
		textFieldElementParallel[8].addKeyListener(new ShortcutKeyEvent(this));
		panelCircuit.add(textFieldElementParallel[8]);

		//素子の画像を表示するラベルの配列を作成
		labelElementParallel = new JLabel[9];

		//素子1の画像を表示するラベルを生成
		labelElementParallel[0] = new JLabel(resistancePicture);
		labelElementParallel[0].setBounds(59, 19, 43, 29);
		labelElementParallel[0].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElementParallel[0]);
		//素子2の画像を表示するラベルを生成
		labelElementParallel[1] = new JLabel(capacitancePicture);
		labelElementParallel[1].setBounds(114, 19, 43, 29);
		labelElementParallel[1].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElementParallel[1]);
		//素子3の画像を表示するラベルを生成
		labelElementParallel[2] = new JLabel(inductancePicture);
		labelElementParallel[2].setBounds(170, 20, 43, 29);
		labelElementParallel[2].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElementParallel[2]);
		//素子4の画像を表示するラベルを生成
		labelElementParallel[3] = new JLabel(linePictureV);
		labelElementParallel[3].setBounds(218, 52, 29, 43);
		labelElementParallel[3].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElementParallel[3]);
		//素子5の画像を表示するラベルを生成
		labelElementParallel[4] = new JLabel(linePictureV);
		labelElementParallel[4].setBounds(217, 109, 29, 43);
		labelElementParallel[4].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElementParallel[4]);
		//素子6の画像を表示するラベルを生成
		labelElementParallel[5] = new JLabel(linePictureV);
		labelElementParallel[5].setBounds(217, 162, 29, 43);
		labelElementParallel[5].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElementParallel[5]);
		//素子7の画像を表示するラベルを生成
		labelElementParallel[6] = new JLabel(resistancePictureV);
		labelElementParallel[6].setBounds(304, 47, 29, 43);
		labelElementParallel[6].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElementParallel[6]);
		//素子8の画像を表示するラベルを生成
		labelElementParallel[7] = new JLabel(resistancePictureV);
		labelElementParallel[7].setBounds(305, 107, 29, 43);
		labelElementParallel[7].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElementParallel[7]);
		//素子9の画像を表示するラベルを生成
		labelElementParallel[8] = new JLabel(resistancePictureV);
		labelElementParallel[8].setBounds(305, 162, 29, 43);
		labelElementParallel[8].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElementParallel[8]);

		//電圧の単位を表示するラベルを生成
		labelVoltageUnitParallel = new JLabel("V");
		labelVoltageUnitParallel.setBounds(105, 119, 19, 13);
		panelCircuit.add(labelVoltageUnitParallel);

		//素子の単位を表示するラベルの配列を生成
		labelElementUnitParallel = new JLabel[9];

		//素子1の単位を表示するラベルを生成
		labelElementUnitParallel[0] = new JLabel("Ω");
		labelElementUnitParallel[0].setBounds(93, 53, 19, 13);
		panelCircuit.add(labelElementUnitParallel[0]);
		//素子2の単位を表示するラベルを生成
		labelElementUnitParallel[1] = new JLabel("Ω");
		labelElementUnitParallel[1].setBounds(149, 52, 19, 13);
		panelCircuit.add(labelElementUnitParallel[1]);
		//素子3の単位を表示するラベルを生成
		labelElementUnitParallel[2] = new JLabel("Ω");
		labelElementUnitParallel[2].setBounds(205, 52, 19, 13);
		panelCircuit.add(labelElementUnitParallel[2]);
		//素子4の単位を表示するラベルを生成
		labelElementUnitParallel[3] = new JLabel("Ω");
		labelElementUnitParallel[3].setBounds(193, 76, 19, 13);
		panelCircuit.add(labelElementUnitParallel[3]);
		//素子5の単位を表示するラベルを生成
		labelElementUnitParallel[4] = new JLabel("Ω");
		labelElementUnitParallel[4].setBounds(193, 125, 19, 13);
		panelCircuit.add(labelElementUnitParallel[4]);;
		//素子6の単位を表示するラベルを生成
		labelElementUnitParallel[5] = new JLabel("Ω");
		labelElementUnitParallel[5].setBounds(196, 176, 19, 13);
		panelCircuit.add(labelElementUnitParallel[5]);
		//素子7の単位を表示するラベルを生成
		labelElementUnitParallel[6] = new JLabel("Ω");
		labelElementUnitParallel[6].setBounds(287, 63, 19, 13);
		panelCircuit.add(labelElementUnitParallel[6]);
		//素子8の単位を表示するラベルを生成
		labelElementUnitParallel[7] = new JLabel("Ω");
		labelElementUnitParallel[7].setBounds(288, 122, 19, 13);
		panelCircuit.add(labelElementUnitParallel[7]);
		//素子9の単位を表示するラベルを生成
		labelElementUnitParallel[8] = new JLabel("Ω");
		labelElementUnitParallel[8].setBounds(286, 177, 19, 13);
		panelCircuit.add(labelElementUnitParallel[8]);

		this.changeStateParallel(false);
		//******************************************//

		//*************直列回路*****************//
		//電圧の値を入力するBOXの生成
		textFieldVoltage = new JTextField();
		textFieldVoltage.setBounds(72, 115, 36, 19);
		textFieldVoltage.addKeyListener(new ShortcutKeyEvent(this));
		textFieldVoltage.addCaretListener(new textFieldCaretEvent(this));
		panelCircuit.add(textFieldVoltage);
		textFieldVoltage.setColumns(10);

		//素子の値を入力するボックスの配列を生成
		textFieldElement = new JFormattedTextField[6];


		//素子1の値を入力するボックスの生成
		textFieldElement[0] = new JFormattedTextField(nf);
		textFieldElement[0].setBounds(111, 53, 36, 19);
		//textFieldElement[0].setFormatterFactory(new NumberFormatterFactory());
		textFieldElement[0].addCaretListener(new textFieldCaretEvent(this));
		textFieldElement[0].addKeyListener(new ShortcutKeyEvent(this));
		panelCircuit.add(textFieldElement[0]);
		//素子2の値を入力するボックスの生成
		textFieldElement[1] = new JFormattedTextField(nf);
		//textFieldElement[1].setFormatterFactory(new NumberFormatterFactory());
		textFieldElement[1].setBounds(195, 53, 36, 19);
		textFieldElement[1].addCaretListener(new textFieldCaretEvent(this));
		textFieldElement[1].addKeyListener(new ShortcutKeyEvent(this));
		panelCircuit.add(textFieldElement[1]);
		//素子3の値を入力するボックスの生成
		textFieldElement[2] = new JFormattedTextField(nf);
		//textFieldElement[2].setFormatterFactory(new NumberFormatterFactory());
		textFieldElement[2].setBounds(242, 80, 36, 19);
		textFieldElement[2].addCaretListener(new textFieldCaretEvent(this));
		textFieldElement[2].addKeyListener(new ShortcutKeyEvent(this));
		panelCircuit.add(textFieldElement[2]);
		//素子4の値を入力するボックスの生成
		textFieldElement[3] = new JFormattedTextField(nf);
		//textFieldElement[3].setFormatterFactory(new NumberFormatterFactory());
		textFieldElement[3].setBounds(243, 148, 36, 19);
		textFieldElement[3].addCaretListener(new textFieldCaretEvent(this));
		textFieldElement[3].addKeyListener(new ShortcutKeyEvent(this));
		panelCircuit.add(textFieldElement[3]);
		//素子5の値を入力するボックスの生成
		textFieldElement[4] = new JFormattedTextField(nf);
		//textFieldElement[4].setFormatterFactory(new NumberFormatterFactory());
		textFieldElement[4].setBounds(198, 171, 36, 19);
		textFieldElement[4].addCaretListener(new textFieldCaretEvent(this));
		textFieldElement[4].addKeyListener(new ShortcutKeyEvent(this));
		panelCircuit.add(textFieldElement[4]);
		//素子6の値を入力するボックスの生成
		textFieldElement[5] = new JFormattedTextField(nf);
		//textFieldElement[5].setFormatterFactory(new NumberFormatterFactory());
		textFieldElement[5].setBounds(107, 171, 36, 19);
		textFieldElement[5].addCaretListener(new textFieldCaretEvent(this));
		textFieldElement[5].addKeyListener(new ShortcutKeyEvent(this));
		panelCircuit.add(textFieldElement[5]);

		//素子の画像を表示するラベルの配列を作成
		labelElement = new JLabel[6];

		//素子1の画像を表示するラベルを生成
		labelElement[0] = new JLabel(resistancePicture);
		labelElement[0].setBounds(111, 19, 43, 29);
		labelElement[0].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElement[0]);
		//素子2の画像を表示するラベルを生成
		labelElement[1] = new JLabel(capacitancePicture);
		labelElement[1].setBounds(196, 19, 43, 29);
		labelElement[1].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElement[1]);
		//素子3の画像を表示するラベルを生成
		labelElement[2] = new JLabel(inductancePictureV);
		labelElement[2].setBounds(304, 66, 29, 43);
		labelElement[2].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElement[2]);
		//素子4の画像を表示するラベルを生成
		labelElement[3] = new JLabel(linePictureV);
		labelElement[3].setBounds(305, 135, 29, 43);
		labelElement[3].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElement[3]);
		//素子5の画像を表示するラベルを生成
		labelElement[4] = new JLabel(linePicture);
		labelElement[4].setBounds(201, 200, 43, 29);
		labelElement[4].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElement[4]);
		//素子6の画像を表示するラベルを生成
		labelElement[5] = new JLabel(linePicture);
		labelElement[5].setBounds(110, 199, 44, 29);
		labelElement[5].addMouseListener(new ElementSelectedEvent(this));
		panelCircuit.add(labelElement[5]);

		//電圧の単位を表示するラベルを作成
		labelVoltageUnit = new JLabel("V");
		labelVoltageUnit.setBounds(110, 118, 19, 13);
		panelCircuit.add(labelVoltageUnit);

		//素子の単位を表示するラベルの配列を作成
		labelElementUnit = new JLabel[6];

		//素子1の単位を表示するラベルを作成
		labelElementUnit[0] = new JLabel("Ω");
		labelElementUnit[0].setBounds(149, 58, 19, 13);
		panelCircuit.add(labelElementUnit[0]);
		//素子2の単位を表示するラベルを作成
		labelElementUnit[1] = new JLabel("Ω");
		labelElementUnit[1].setBounds(235, 57, 19, 13);
		panelCircuit.add(labelElementUnit[1]);
		//素子3の単位を表示するラベルを作成
		labelElementUnit[2] = new JLabel("Ω");
		labelElementUnit[2].setBounds(281, 83, 19, 13);
		panelCircuit.add(labelElementUnit[2]);
		//素子4の単位を表示するラベルを作成
		labelElementUnit[3] = new JLabel("Ω");
		labelElementUnit[3].setBounds(279, 150, 19, 13);
		panelCircuit.add(labelElementUnit[3]);
		//素子5の単位を表示するラベルを作成
		labelElementUnit[4] = new JLabel("Ω");
		labelElementUnit[4].setBounds(236, 174, 19, 13);
		panelCircuit.add(labelElementUnit[4]);
		//素子6の単位を表示するラベルを作成
		labelElementUnit[5] = new JLabel("Ω");
		labelElementUnit[5].setBounds(147, 174, 19, 13);
		panelCircuit.add(labelElementUnit[5]);
		//回路の画像を表示するラベルを作成
		labelCircuitPicture = new JLabel(seriesCircuitPicture);
		labelCircuitPicture.setBounds(1, 1, 359, 249);
		panelCircuit.add(labelCircuitPicture);
		//************************************//

		//数式エリアのラベルを作成
		labelFormula = new JLabel("数式");
		labelFormula.setBounds(552, 28, 144, 13);
		contentPane.add(labelFormula);
		//数式エリアを表示するパネルを生成
		panelFormula = new JPanel();
		panelFormula.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFormula.setBounds(552, 41, 152, 250);
		contentPane.add(panelFormula);
		panelFormula.setLayout(null);
		//数式を表示するテキストエリアを生成
		//JTextArea textArea = new JTextArea();
		textArea.setBounds(1, 1, 150, 248);
		textArea.setEditable(false);
		textArea.addKeyListener(new ShortcutKeyEvent(this));
		panelFormula.add(textArea);

		//素子リストのラベルを生成
		labelElementList = new JLabel("素子リスト");
		labelElementList.setBounds(367, 28, 154, 13);
		contentPane.add(labelElementList);
		//素子リスト
		panelElementList = new JPanel();
		panelElementList.setBorder(null);
		panelElementList.setBounds(361, 41, 191, 250);
		contentPane.add(panelElementList);
		panelElementList.setLayout(null);

		//編集不可に
		DefaultTableModel tableModel = new MyTableModel(columnNames, 0){
			@Override public boolean isCellEditable(int row, int col) {
                return false;
			}
		};
		table = new JTable(tableModel);
		table.setRowHeight(100);
		table.addMouseListener(new ElementChangeEvent(this));
		table.addKeyListener(new ShortcutKeyEvent(this));

		for(int i=0;i<4;i++){
		      tableModel.addRow(tabledata[i]);
		}

		scrollPaneElementList = new JScrollPane(table);
		scrollPaneElementList.setViewportBorder(null);
		scrollPaneElementList.setBounds(0, 0, 191, 250);
		panelElementList.add(scrollPaneElementList);

		//グラフを作成するものを選択
		radioButtonVoltage = new JRadioButton("電圧");
		radioButtonVoltage.setBounds(30, 303, 55, 14);
		radioButtonVoltage.addKeyListener(new ShortcutKeyEvent(this));
		contentPane.add(radioButtonVoltage);

		radioButtonCurrent = new JRadioButton("電流");
		radioButtonCurrent.setBounds(85, 301, 54, 17);
		radioButtonCurrent.setSelected(true);
		radioButtonCurrent.addKeyListener(new ShortcutKeyEvent(this));
		contentPane.add(radioButtonCurrent);

		radioButtonSource = new JRadioButton("電源電圧");
		radioButtonSource.setBounds(135, 298, 113, 21);
		radioButtonSource.addKeyListener(new ShortcutKeyEvent(this));;
		contentPane.add(radioButtonSource);

		loadCircuit();
	}

	public void changeStateSerial(Boolean state)
	{
		//直列回路のコンポーネントを非表示に
		textFieldVoltage.setVisible(state);
		labelVoltageUnit.setVisible(state);

		for(int i = 0;i < 6;i++)
		{
			textFieldElement[i].setVisible(state);
			labelElement[i].setVisible(state);
			labelElementUnit[i].setVisible(state);
		}
	}

	public void changeStateParallel(Boolean state)
	{
		//並列回路のコンポーネントの状態を変更
		textFieldVoltageParallel.setVisible(state);
		labelVoltageUnitParallel.setVisible(state);

		for(int i = 0;i < 9;i++)
		{
			textFieldElementParallel[i].setVisible(state);
			labelElementParallel[i].setVisible(state);
			labelElementUnitParallel[i].setVisible(state);
		}
	}

	public void loadCircuit()
	{
		Icon icon;

		if(mainCircuit instanceof SeriesCircuit)
		{
			//直列回路のコンポーネントを有効化
			changeStateSerial(true);

			//並列回路のコンポーネントを非表示に
			changeStateParallel(false);

			labelCircuitPicture.setIcon(seriesCircuitPicture);

			textFieldVoltage.setText(Double.toString(mainCircuit.getVoltage()));

			for(int i = 0;i < mainCircuit.getElem().length;i++)
			{
				icon = labelElement[i].getIcon();

				textFieldElement[i].setText(Double.toString(mainCircuit.getElem(i).getValue()));

				switch(mainCircuit.getElem(i).getEt())
				{
				case RESISTANCE:
					if(icon.getIconHeight() == linePicture.getIconHeight())
						labelElement[i].setIcon(resistancePicture);
					else
						labelElement[i].setIcon(resistancePictureV);

					labelElementUnit[i].setText("Ω");
					break;
				case CAPACITANCE:
					if(icon.getIconHeight() == linePicture.getIconHeight())
						labelElement[i].setIcon(capacitancePicture);
					else
						labelElement[i].setIcon(capacitancePictureV);

					labelElementUnit[i].setText("F");
					break;
				case INDUCTANCE:
					if(icon.getIconHeight() == linePicture.getIconHeight())
						labelElement[i].setIcon(inductancePicture);
					else
						labelElement[i].setIcon(inductancePictureV);

					labelElementUnit[i].setText("H");
					break;
				case LINE:
					if(icon.getIconHeight() == linePicture.getIconHeight())
						labelElement[i].setIcon(linePicture);
					else
						labelElement[i].setIcon(linePictureV);

					labelElementUnit[i].setText(null);
					break;
				}
			}
		}

		else if(mainCircuit instanceof ParallelCircuit)
		{
			//直列回路のコンポーネントを非表示に
			changeStateSerial(false);

			//並列回路のコンポーネントを有効化
			changeStateParallel(true);

			textFieldVoltage.setText(Double.toString(mainCircuit.getVoltage()));

			labelCircuitPicture.setIcon(parallelCircuitPicture);

			for(int i = 0;i < mainCircuit.getElem().length;i++)
			{
				textFieldElementParallel[i].setText(Double.toString(mainCircuit.getElem(i).getValue()));

				icon = labelElementParallel[i].getIcon();

				switch(mainCircuit.getElem(i).getEt())
				{
				case RESISTANCE:
					if(icon.getIconHeight() == linePicture.getIconHeight())
						labelElementParallel[i].setIcon(resistancePicture);
					else
						labelElementParallel[i].setIcon(resistancePictureV);

					labelElementUnitParallel[i].setText("Ω");
					break;
				case CAPACITANCE:
					if(icon.getIconHeight() == linePicture.getIconHeight())
						labelElementParallel[i].setIcon(capacitancePicture);
					else
						labelElementParallel[i].setIcon(capacitancePictureV);

					labelElementUnitParallel[i].setText("F");
					break;
				case INDUCTANCE:
					if(icon.getIconHeight() == linePicture.getIconHeight())
						labelElementParallel[i].setIcon(inductancePicture);
					else
						labelElementParallel[i].setIcon(inductancePictureV);

					labelElementUnitParallel[i].setText("H");
					break;
				case LINE:
					if(icon.getIconHeight() == linePicture.getIconHeight())
						labelElementParallel[i].setIcon(linePicture);
					else
						labelElementParallel[i].setIcon(linePictureV);

					labelElementUnitParallel[i].setText(null);
					break;
				}
			}
		}
	}

	class MyTableModel extends DefaultTableModel{
	    MyTableModel(String[] columnNames, int rowNum){
	      super(columnNames, rowNum);
	    }

	    public Class getColumnClass(int col){
	      return getValueAt(0, col).getClass();
	    }
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

