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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import circuit.Circuit;
import circuit.ElemType;
import circuit.SeriesCircuit;


public class MainDispApp extends JFrame
{
	//メイン画面のペーン(大枠)
	public JPanel contentPane;

	//回路エリアのラベル
	public JLabel labelCircuit;
	//回路エリアを表示するパネル
	public JPanel panelCircuit;

	//素子の値を入力するBOX
	public JTextField textFieldElement1;
	public JTextField textFieldElement2;
	public JTextField textFieldElement4;
	public JTextField textFieldElement3;
	public JTextField textFieldElement5;
	public JTextField textFieldElement6;
	//並列用の素子の値を入力するBOX
	public JTextField textFieldElement1Parallel;
	public JTextField textFieldElement2Parallel;
	public JTextField textFieldElement3Parallel;
	public JTextField textFieldElement4Parallel;
	public JTextField textFieldElement5Parallel;
	public JTextField textFieldElement6Parallel;
	public JTextField textFieldElement7Parallel;
	public JTextField textFieldElement8Parallel;
	public JTextField textFieldElement9Parallel;

	//素子の画像を表示するラベル
	public JLabel labelElement1;
	public JLabel labelElement2;
	public JLabel labelElement3;
	public JLabel labelElement4;
	public JLabel labelElement5;
	public JLabel labelElement6;
	//素子を表示するラベル(並列用)
	public JLabel labelElement1Parallel;
	public JLabel labelElement2Parallel;
	public JLabel labelElement3Parallel;
	public JLabel labelElement4Parallel;
	public JLabel labelElement5Parallel;
	public JLabel labelElement6Parallel;
	public JLabel labelElement7Parallel;
	public JLabel labelElement8Parallel;
	public JLabel labelElement9Parallel;

	//素子の単位を表示するラベル
	public JLabel labelElement1Unit;
	public JLabel labelElement2Unit;
	public JLabel labelElement3Unit;
	public JLabel labelElement4Unit;
	public JLabel labelElement5Unit;
	public JLabel labelElement6Unit;
	//素子の単位を表示するラベル(並列用)
	public JLabel labelElement1UnitParallel;
	public JLabel labelElement2UnitParallel;
	public JLabel labelElement3UnitParallel;
	public JLabel labelElement4UnitParallel;
	public JLabel labelElement5UnitParallel;
	public JLabel labelElement6UnitParallel;
	public JLabel labelElement7UnitParallel;
	public JLabel labelElement8UnitParallel;
	public JLabel labelElement9UnitParallel;

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

	//表示メニュー
	public JMenu meuDisp;
	//回路の表示
	public JMenuItem menuItemCircuitGraph;
	//数式の表示
	public JMenuItem menuItemFormula;
	//素子リストの表示
	public JMenuItem menuItemElement;
	//グラフの表示
	public JMenuItem menuItemGraph;

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
	public JPanel panelGraph;

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
	private JTable table;

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


	//回路の情報を保持するクラス
	public Circuit mainCircuit;

	//素子リストの表のデータ
	private Object[][] tabledata={{new ImageIcon(MainDispApp.class.getResource("/resources/CpacitanceDisp.png")),"キャパシタ"},
			                      {new ImageIcon(MainDispApp.class.getResource("/resources/resisitanceDisp.png")),"抵抗"},
			                      {new ImageIcon(MainDispApp.class.getResource("/resources/InductanceDisp.png")),"インダクタ"},
			                      {new ImageIcon(MainDispApp.class.getResource("/resources/LineDisp.png")),"ライン"}};

	private String[] columnNames = {"IMAGE","NAME"};

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
		capacitancePicture = new ImageIcon(MainDispApp.class.getResource("/resources/Cpacitance.png"));
		capacitancePictureV = new ImageIcon(MainDispApp.class.getResource("/resources/CpacitanceV.png"));

		//回路情報の生成
		mainCircuit = new SeriesCircuit();
		mainCircuit.setElem(0, 1, ElemType.RESISTANCE);
		mainCircuit.setElem(1, 1, ElemType.CAPACITANCE);
		mainCircuit.setElem(2, 1, ElemType.INDUCTANCE);

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
		//開くの生成
		menuItemOpen = new JMenuItem("開く");
		menuFile.add(menuItemOpen);
		//上書きの生成
		menuItemOverWrite = new JMenuItem("上書き保存");
		menuFile.add(menuItemOverWrite);
		//名前を付けて保存の生成
		menuItemSaveAs = new JMenuItem("名前を付けて保存");
		menuFile.add(menuItemSaveAs);
		//波形の出力の生成
		menuItemExportGraph = new JMenuItem("波形を画像として保存");
		menuFile.add(menuItemExportGraph);
		//閉じるの生成
		menuItemClose = new JMenuItem("閉じる");
		menuFile.add(menuItemClose);

		//表示メニューの生成
		meuDisp = new JMenu("表示");
		menuBar.add(meuDisp);
		//回路図の表示の生成
		menuItemCircuitGraph = new JMenuItem("回路図");
		meuDisp.add(menuItemCircuitGraph);
		//数式の表示の生成
		menuItemFormula = new JMenuItem("数式");
		meuDisp.add(menuItemFormula);
		//素子リストの表示の生成
		menuItemElement = new JMenuItem("素子リスト");
		meuDisp.add(menuItemElement);
		//波形の表示の生成
		menuItemGraph = new JMenuItem("波形");
		meuDisp.add(menuItemGraph);

		//編集メニューの生成
		menuEdit = new JMenu("編集");
		menuBar.add(menuEdit);
		//戻るの生成
		menuItemRedo = new JMenuItem("戻る");
		menuEdit.add(menuItemRedo);
		//進むの生成
		menuItemUndo = new JMenuItem("進む");
		menuEdit.add(menuItemUndo);
		//コピーの生成
		menuItemCopy = new JMenuItem("コピー");
		menuEdit.add(menuItemCopy);
		//切り取りの生成
		menuItemCut = new JMenuItem("切り取り");
		menuEdit.add(menuItemCut);
		//ペーストの生成
		menuItemPaste = new JMenuItem("ペースト");
		menuEdit.add(menuItemPaste);
		//前消去の生成
		menuItemAllClear = new JMenuItem("全消去");
		menuEdit.add(menuItemAllClear);

		//ヘルプメニューの生成
		menuHelp = new JMenu("ヘルプ");
		menuBar.add(menuHelp);
		//ヘルプビューワアイテムの生成
		menuItemHelpViewer = new JMenuItem("ヘルプビューア");
		menuHelp.add(menuItemHelpViewer);

		//実行メニューの生成
		menuSimulate = new JMenu("実行");
		menuBar.add(menuSimulate);
		//シミュレーションの生成
		menuItemSimulation = new JMenuItem("シミュレーション");
		menuSimulate.add(menuItemSimulation);

		//回路の種類メニューの生成
		menuCircuitSelect = new JMenu("回路の種類");
		menuBar.add(menuCircuitSelect);
		//直列回路の生成
		menuItemSeriesCircuit = new JMenuItem("直列回路");
		menuItemSeriesCircuit.addMouseListener(new CircuitChangeEvent(this));
		menuCircuitSelect.add(menuItemSeriesCircuit);
		//並列回路の生成
		menuItemParallelCircuit = new JMenuItem("並列回路");
		menuItemParallelCircuit.addMouseListener(new CircuitChangeEvent(this));
		menuCircuitSelect.add(menuItemParallelCircuit);

		//メニューバー(ボタン)
		menuBarButtons = new JMenuBar();
		menuBarButtons.setBounds(0, 0, 676, 21);
		contentPane.add(menuBarButtons);
		//戻るボタンの生成
		buttonRedo = new JButton("戻る");
		menuBarButtons.add(buttonRedo);
		//進むボタンの生成
		buttonUndo = new JButton("進む");
		menuBarButtons.add(buttonUndo);
		//開くボタンの生成
		buttonOpen = new JButton("開く");
		menuBarButtons.add(buttonOpen);
		//保存ボタンの生成
		buttonSave = new JButton("保存");
		menuBarButtons.add(buttonSave);
		//シミュレーション開始ボタンの生成
		buttonStart = new JButton("シミュレーション開始");
		menuBarButtons.add(buttonStart);
		//シミュレーション終了ボタンの生成
		buttonEnd = new JButton("シミュレーション終了");
		menuBarButtons.add(buttonEnd);

		//グラフエリアを表示するパネルを生成
		panelGraph = new JPanel();
		panelGraph.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGraph.setBounds(0, 320, 706, 180);
		contentPane.add(panelGraph);
		//グラフエリアのラベルを生成
		labelGraph = new JLabel("波形");
		labelGraph.setBounds(0, 304, 676, 13);
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
		panelCircuit.add(textFieldVoltageParallel);
		textFieldVoltageParallel.setColumns(10);
		textFieldVoltageParallel.setVisible(false);
		//素子1の値を入力するBOXの生成
		textFieldElement1Parallel = new JTextField();
		textFieldElement1Parallel.setBounds(56, 50, 36, 19);
		panelCircuit.add(textFieldElement1Parallel);
		textFieldElement1Parallel.setColumns(10);
		textFieldElement1Parallel.setVisible(false);
		//素子2の値を入力するBOXの生成
		textFieldElement2Parallel = new JTextField();
		textFieldElement2Parallel.setColumns(10);
		textFieldElement2Parallel.setBounds(112, 49, 36, 19);
		textFieldElement2Parallel.setVisible(false);
		panelCircuit.add(textFieldElement2Parallel);
		//素子3の値を入力するBOXの生成
		textFieldElement3Parallel = new JTextField();
		textFieldElement3Parallel.setColumns(10);
		textFieldElement3Parallel.setBounds(168, 50, 36, 19);
		panelCircuit.add(textFieldElement3Parallel);
		textFieldElement3Parallel.setVisible(false);
		//素子4の値を入力するBOXの生成
		textFieldElement4Parallel = new JTextField();
		textFieldElement4Parallel.setColumns(10);
		textFieldElement4Parallel.setBounds(157, 74, 36, 19);
		panelCircuit.add(textFieldElement4Parallel);
		textFieldElement4Parallel.setVisible(false);
		//素子5の値を入力するBOXの生成
		textFieldElement5Parallel = new JTextField();
		textFieldElement5Parallel.setColumns(10);
		textFieldElement5Parallel.setBounds(157, 123, 36, 19);
		panelCircuit.add(textFieldElement5Parallel);
		textFieldElement5Parallel.setVisible(false);
		//素子6の値を入力するBOXの生成
		textFieldElement6Parallel = new JTextField();
		textFieldElement6Parallel.setColumns(10);
		textFieldElement6Parallel.setBounds(157, 174, 36, 19);
		panelCircuit.add(textFieldElement6Parallel);
		textFieldElement6Parallel.setVisible(false);
		//素子7の値を入力するBOXの生成
		textFieldElement7Parallel = new JTextField();
		textFieldElement7Parallel.setColumns(10);
		textFieldElement7Parallel.setBounds(251, 60, 36, 19);
		panelCircuit.add(textFieldElement7Parallel);
		textFieldElement7Parallel.setVisible(false);
		//素子8の値を入力するBOXの生成
		textFieldElement8Parallel = new JTextField();
		textFieldElement8Parallel.setColumns(10);
		textFieldElement8Parallel.setBounds(252, 120, 36, 19);
		panelCircuit.add(textFieldElement8Parallel);
		textFieldElement8Parallel.setVisible(false);
		//素子9の値を入力するBOXの生成
		textFieldElement9Parallel = new JTextField();
		textFieldElement9Parallel.setColumns(10);
		textFieldElement9Parallel.setBounds(250, 175, 36, 19);
		panelCircuit.add(textFieldElement9Parallel);
		textFieldElement9Parallel.setVisible(false);

		//素子1の画像を表示するラベルを生成
		labelElement1Parallel = new JLabel(resistancePicture);
		labelElement1Parallel.setBounds(59, 19, 43, 29);
		panelCircuit.add(labelElement1Parallel);
		labelElement1Parallel.setVisible(false);
		//素子2の画像を表示するラベルを生成
		labelElement2Parallel = new JLabel(capacitancePicture);
		labelElement2Parallel.setBounds(114, 19, 43, 29);
		panelCircuit.add(labelElement2Parallel);
		labelElement2Parallel.setVisible(false);
		//素子3の画像を表示するラベルを生成
		labelElement3Parallel = new JLabel(inductancePicture);
		labelElement3Parallel.setBounds(170, 20, 43, 29);
		panelCircuit.add(labelElement3Parallel);
		labelElement3Parallel.setVisible(false);
		//素子4の画像を表示するラベルを生成
		labelElement4Parallel = new JLabel(linePictureV);
		labelElement4Parallel.setBounds(218, 52, 29, 43);
		panelCircuit.add(labelElement4Parallel);
		labelElement4Parallel.setVisible(false);
		//素子5の画像を表示するラベルを生成
		labelElement5Parallel = new JLabel(linePictureV);
		labelElement5Parallel.setBounds(217, 109, 29, 43);
		panelCircuit.add(labelElement5Parallel);
		labelElement5Parallel.setVisible(false);
		//素子6の画像を表示するラベルを生成
		labelElement6Parallel = new JLabel(linePictureV);
		labelElement6Parallel.setBounds(217, 162, 29, 43);
		panelCircuit.add(labelElement6Parallel);
		labelElement6Parallel.setVisible(false);
		//素子7の画像を表示するラベルを生成
		labelElement7Parallel = new JLabel(resistancePictureV);
		labelElement7Parallel.setBounds(304, 47, 29, 43);
		panelCircuit.add(labelElement7Parallel);
		labelElement7Parallel.setVisible(false);
		//素子8の画像を表示するラベルを生成
		labelElement8Parallel = new JLabel(resistancePictureV);
		labelElement8Parallel.setBounds(305, 107, 29, 43);
		panelCircuit.add(labelElement8Parallel);
		labelElement8Parallel.setVisible(false);
		//素子9の画像を表示するラベルを生成
		labelElement9Parallel = new JLabel(resistancePictureV);
		labelElement9Parallel.setBounds(305, 162, 29, 43);
		panelCircuit.add(labelElement9Parallel);
		labelElement9Parallel.setVisible(false);

		//電圧の単位を表示するラベルを生成
		labelVoltageUnitParallel = new JLabel("V");
		labelVoltageUnitParallel.setBounds(105, 119, 19, 13);
		panelCircuit.add(labelVoltageUnitParallel);
		labelVoltageUnitParallel.setVisible(false);
		//素子1の単位を表示するラベルを生成
		labelElement1UnitParallel = new JLabel("Ω");
		labelElement1UnitParallel.setBounds(93, 53, 19, 13);
		panelCircuit.add(labelElement1UnitParallel);
		labelElement1UnitParallel.setVisible(false);
		//素子2の単位を表示するラベルを生成
		labelElement2UnitParallel = new JLabel("Ω");
		labelElement2UnitParallel.setBounds(149, 52, 19, 13);
		panelCircuit.add(labelElement2UnitParallel);
		labelElement2UnitParallel.setVisible(false);
		//素子3の単位を表示するラベルを生成
		labelElement3UnitParallel = new JLabel("Ω");
		labelElement3UnitParallel.setBounds(205, 52, 19, 13);
		panelCircuit.add(labelElement3UnitParallel);
		labelElement3UnitParallel.setVisible(false);
		//素子4の単位を表示するラベルを生成
		labelElement4UnitParallel = new JLabel("Ω");
		labelElement4UnitParallel.setBounds(193, 76, 19, 13);
		panelCircuit.add(labelElement4UnitParallel);
		labelElement4UnitParallel.setVisible(false);
		//素子5の単位を表示するラベルを生成
		labelElement5UnitParallel = new JLabel("Ω");
		labelElement5UnitParallel.setBounds(193, 125, 19, 13);
		panelCircuit.add(labelElement5UnitParallel);
		labelElement5UnitParallel.setVisible(false);
		//素子6の単位を表示するラベルを生成
		labelElement6UnitParallel = new JLabel("Ω");
		labelElement6UnitParallel.setBounds(196, 176, 19, 13);
		panelCircuit.add(labelElement6UnitParallel);
		labelElement6UnitParallel.setVisible(false);
		//素子7の単位を表示するラベルを生成
		labelElement7UnitParallel = new JLabel("Ω");
		labelElement7UnitParallel.setBounds(287, 63, 19, 13);
		panelCircuit.add(labelElement7UnitParallel);
		labelElement7UnitParallel.setVisible(false);
		//素子8の単位を表示するラベルを生成
		labelElement8UnitParallel = new JLabel("Ω");
		labelElement8UnitParallel.setBounds(288, 122, 19, 13);
		panelCircuit.add(labelElement8UnitParallel);
		labelElement8UnitParallel.setVisible(false);
		//素子9の単位を表示するラベルを生成
		labelElement9UnitParallel = new JLabel("Ω");
		labelElement9UnitParallel.setBounds(286, 177, 19, 13);
		panelCircuit.add(labelElement9UnitParallel);
		labelElement9UnitParallel.setVisible(false);

		//回路の画像を表示するラベルを作成
		labelCircuitPicture = new JLabel(parallelCircuitPicture);
		labelCircuitPicture.setBounds(1, 1, 359, 249);
		panelCircuit.add(labelCircuitPicture);
		//******************************************//

		//*************直列回路*****************//
		//電圧の値を入力するBOXの生成
		/*textFieldVoltage = new JTextField();
		textFieldVoltage.setBounds(72, 115, 36, 19);
		panelCircuit.add(textFieldVoltage);
		textFieldVoltage.setColumns(10);

		//素子1の値を入力するボックスの生成
		textFieldElement1 = new JTextField();
		textFieldElement1.setBounds(111, 53, 36, 19);
		panelCircuit.add(textFieldElement1);
		textFieldElement1.setColumns(10);
		//素子2の値を入力するボックスの生成
		textFieldElement2 = new JTextField();
		textFieldElement2.setColumns(10);
		textFieldElement2.setBounds(195, 53, 36, 19);
		panelCircuit.add(textFieldElement2);
		//素子3の値を入力するボックスの生成
		textFieldElement3 = new JTextField();
		textFieldElement3.setColumns(10);
		textFieldElement3.setBounds(242, 80, 36, 19);
		panelCircuit.add(textFieldElement3);
		//素子4の値を入力するボックスの生成
		textFieldElement4 = new JTextField();
		textFieldElement4.setColumns(10);
		textFieldElement4.setBounds(243, 148, 36, 19);
		panelCircuit.add(textFieldElement4);
		//素子5の値を入力するボックスの生成
		textFieldElement5 = new JTextField();
		textFieldElement5.setColumns(10);
		textFieldElement5.setBounds(198, 171, 36, 19);
		panelCircuit.add(textFieldElement5);
		//素子6の値を入力するボックスの生成
		textFieldElement6 = new JTextField();
		textFieldElement6.setColumns(10);
		textFieldElement6.setBounds(107, 171, 36, 19);
		panelCircuit.add(textFieldElement6);

		//素子1の画像を表示するラベルを生成
		labelElement1 = new JLabel(resistancePicture);
		labelElement1.setBounds(111, 19, 43, 29);
		panelCircuit.add(labelElement1);
		//素子2の画像を表示するラベルを生成
		labelElement2 = new JLabel(capacitancePicture);
		labelElement2.setBounds(196, 19, 43, 29);
		panelCircuit.add(labelElement2);
		//素子3の画像を表示するラベルを生成
		labelElement3 = new JLabel(inductancePictureV);
		labelElement3.setBounds(305, 66, 29, 43);
		panelCircuit.add(labelElement3);
		//素子4の画像を表示するラベルを生成
		labelElement4 = new JLabel(linePictureV);
		labelElement4.setBounds(305, 135, 29, 43);
		panelCircuit.add(labelElement4);
		//素子5の画像を表示するラベルを生成
		labelElement5 = new JLabel(linePicture);
		labelElement5.setBounds(201, 200, 43, 29);
		panelCircuit.add(labelElement5);
		//素子6の画像を表示するラベルを生成
		labelElement6 = new JLabel(linePicture);
		labelElement6.setBounds(110, 200, 44, 29);
		panelCircuit.add(labelElement6);

		//電圧の単位を表示するラベルを作成
		labelVoltageUnit = new JLabel("V");
		labelVoltageUnit.setBounds(110, 118, 19, 13);
		panelCircuit.add(labelVoltageUnit);
		//素子1の単位を表示するラベルを作成
		labelElement1Unit = new JLabel("Ω");
		labelElement1Unit.setBounds(149, 58, 19, 13);
		panelCircuit.add(labelElement1Unit);
		//素子2の単位を表示するラベルを作成
		labelElement2Unit = new JLabel("Ω");
		labelElement2Unit.setBounds(235, 57, 19, 13);
		panelCircuit.add(labelElement2Unit);
		//素子3の単位を表示するラベルを作成
		labelElement3Unit = new JLabel("Ω");
		labelElement3Unit.setBounds(281, 83, 19, 13);
		panelCircuit.add(labelElement3Unit);
		//素子4の単位を表示するラベルを作成
		labelElement4Unit = new JLabel("Ω");
		labelElement4Unit.setBounds(279, 150, 19, 13);
		panelCircuit.add(labelElement4Unit);
		//素子5の単位を表示するラベルを作成
		labelElement5Unit = new JLabel("Ω");
		labelElement5Unit.setBounds(236, 174, 19, 13);
		panelCircuit.add(labelElement5Unit);
		//素子6の単位を表示するラベルを作成
		labelElement6Unit = new JLabel("Ω");
		labelElement6Unit.setBounds(147, 174, 19, 13);
		panelCircuit.add(labelElement6Unit);
		//回路の画像を表示するラベルを作成
		labelCircuitPicture = new JLabel(seriesCircuitPicture);
		labelCircuitPicture.setBounds(1, 1, 359, 249);
		panelCircuit.add(labelCircuitPicture);*/
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
		JTextArea textArea = new JTextArea();
		textArea.setBounds(1, 1, 150, 248);
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

		DefaultTableModel tableModel = new MyTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setRowHeight(100);

		for(int i=0;i<4;i++){
		      tableModel.addRow(tabledata[i]);
		}

		scrollPaneElementList = new JScrollPane(table);
		scrollPaneElementList.setViewportBorder(null);
		scrollPaneElementList.setBounds(0, 0, 191, 250);
		panelElementList.add(scrollPaneElementList);

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
