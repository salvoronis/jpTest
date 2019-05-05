package japanese;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collections;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.awt.Font;
import java.io.File;

public class UserEditMenu extends JFrame{
	static LinkedList<Slova> slovv = new LinkedList<>();
	UserEditMenu(){
		final JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 1,1));
        panel.setBorder(BorderFactory.createLineBorder(Color.red));

        panel.setPreferredSize(new Dimension(300, (30 * 10)));
        panel.add(new JCheckBox("YRA!"));

        final JScrollPane scroll = new JScrollPane(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        setSize(300, 300);
        setVisible(true);
	}

	public static void reader(String put) throws Exception{
		LinkedList<Slova> slova = new LinkedList<>();
		slovv.clear();
		try{
			Reader unicodeFileReader = new InputStreamReader(new FileInputStream(put), "UTF-8");
			StringBuilder out = new StringBuilder();
			char[] buf = new char[5000];
			int rsz = unicodeFileReader.read(buf, 0, buf.length);
			out.append(buf, 0, rsz);
			String[] opa = out.toString().split("\n");
			for(int i = 0; i<opa.length ; i++){
				String line = opa[i];
				if(line.contains("<Slova ")){
					String[] elem = line.split(" ");
					String[] elemnew = line.split("\"");
					String imi = "abstractt";
					String yomi = "abstractt";
					String kanji = "abstractt";
					imi = elemnew[1];
					yomi = elemnew[3];
					kanji = elemnew[5];
					slova.add(new Slova(imi, yomi, kanji));
				}
			}
		}
		catch(IOException e){
			System.out.println("не найден файл");
		}
		slovv.addAll(slova);
	}
}