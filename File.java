package circut_1;

//import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class File extends JFrame implements ActionListener{

  JLabel label;

  public static void main(String[] args){
    File frame = new File();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(10, 10, 300, 200);
    frame.setTitle("タイトル");
    frame.setVisible(true);
  }

  File(){
    JButton button = new JButton("file select");
    button.addActionListener(this);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(button);

    label = new JLabel();

    JPanel labelPanel = new JPanel();
    labelPanel.add(label);

    getContentPane().add(labelPanel, BorderLayout.CENTER);
    getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
  }

  public void actionPerformed(ActionEvent e){
    JFileChooser filechooser = new JFileChooser();
    filechooser.setDialogTitle("名前を付けて保存");

    int selected = filechooser.showSaveDialog(this);
    if (selected == JFileChooser.APPROVE_OPTION){
      java.io.File file = filechooser.getSelectedFile();
      label.setText(file.getName());
    }
  }
}