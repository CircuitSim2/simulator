package help;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
public class Openhelp implements ActionListener{

	public void actionPerformed(ActionEvent e){
		Runtime r = Runtime.getRuntime();
	
		try {
			//htmlのパス取得
			File file = new File("src/help/simhelp.chm");
			String path = file.getAbsolutePath();
		
			//IEでwebページ表示
			r.exec(new String[]{
					"C:\\Program Files\\Internet Explorer\\iexplore.exe",
					"-private",
					path
			});
		
			//System.out.println(path);
		} catch (IOException n) {
			n.printStackTrace();
		}	
	}	
}