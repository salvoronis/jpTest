package japanese;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collections;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.awt.Font;
import java.io.File;


public class Japanese extends JFrame {
	static LinkedList<Slova> slovv = new LinkedList<>();
	static LinkedList<Slova> slovosh = new LinkedList<>();
	//Font msgothic = new Font("Yu Gothic UI Semilight", /*Font.ITALIC*/0, 15);
	Font font = new Font("Calibri", 0, 15);
	Font japFt;
	public static void main (String [] args){
		Japanese jp = new Japanese();
		//UserEditMenu usermenu = new UserEditMenu();
		jp.setVisible(true);
	}


	public static void reader(String put) throws Exception{
		LinkedList<Slova> slova = new LinkedList<>();
		slovv.clear();
		slovosh.clear();
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
					/*for(String a : elemnew){
						System.out.println(a);
					}*/
					String imi = "abstractt";
					String yomi = "abstractt";
					String kanji = "abstractt";
					/*for (String a : elem){
						if (a.contains("imi")){
							imi = a.substring(a.indexOf("=") + 2, a.lastIndexOf("\""));
						}
						else if (a.contains("yomi")){
							yomi = a.substring(a.indexOf("=") + 2, a.lastIndexOf("\""));
						}
						else if (a.contains("kanji")){
							kanji = a.substring(a.indexOf("=") + 2, a.lastIndexOf("\""));
						}
						else {continue;}
					}*/
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
		Collections.sort(slova);
		slovv.addAll(slova);
		slovosh.addAll(slova);
		numb = slovv.size();
	}
	static int numb;
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("Уровень");
	JMenu exitM = new JMenu("Exit");
	JButton button = new JButton("Дальше");
	JButton button2 = new JButton("Не верно");
	JMenuItem exit = new JMenuItem("Exit");
	JMenuItem n5 = new JMenuItem("N5");
	JMenuItem n4 = new JMenuItem("N4");
	JMenuItem n3 = new JMenuItem("N3");
	JMenuItem n2 = new JMenuItem("N2");
	JMenuItem userrmenu = new JMenuItem("menu");
	JLabel label = new JLabel("<html>Выберите уровень</html>", JLabel.CENTER);
	JLabel chtenie = new JLabel("Уровень -> *lvl*");
	int kolosh = 0;
	int vsego;
	static String put;

	public Japanese(){
		super("Тест иероглифы");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int locationX = (screenSize.width - 300) / 2;
		int locationY = (screenSize.height - 200) / 2;
    	this.setBounds(locationX, locationY,300,200);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setJMenuBar(menuBar);


    	//UserEditMenu usermenu/* = new UserEditMenu()*/;


    	try{
    		japFt = Font.createFont(Font.TRUETYPE_FONT, new File("japanese/yugothil.ttf"));
    		japFt = japFt.deriveFont(Font.BOLD, 18);
    	}catch(Exception e){e.printStackTrace();}

    	//label.setFont(msgothic);

    	menuBar.add(fileMenu);
    	menuBar.add(exitM);
    	exitM.add(exit);
    	fileMenu.add(n5);
    	fileMenu.add(n4);
    	fileMenu.add(n3);
    	fileMenu.add(n2);
    	fileMenu.add(userrmenu);

    	exit.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                System.exit(0);             
            }           
        });
        userrmenu.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		//this.setVisible(false);
        		UserEditMenu usermenu = new UserEditMenu();
        		usermenu.setVisible(true);
        	}
        });
    	n4.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                put = "japanese/slova4.xml";
                try{
                	reader(put);
            	}catch(Exception ex){}
                label.setText(slovv.getFirst().imi);
				chtenie.setText(slovv.getFirst().yomi);
				chtenie.setFont(japFt);
				slovv.removeFirst();
            }           
        });
        n5.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                put = "japanese/slova5.xml";
                try{
                	reader(put);
                } catch(Exception ex){};
                label.setText(slovv.getFirst().imi);
				chtenie.setText(slovv.getFirst().yomi);
				chtenie.setFont(japFt);
				slovv.removeFirst();
            }           
        });
        n3.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
            	put = "japanese/slova3.xml";
            	try{
            		reader(put);
                }catch(Exception ex){}
                label.setText(slovv.getFirst().imi);
				chtenie.setText(slovv.getFirst().yomi);
				chtenie.setFont(japFt);
				slovv.removeFirst();
            }           
        });
        n2.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                put = "japanese/slova2.xml";
                try{
                	reader(put);
                } catch(Exception ex){}
                System.out.println(slovv);
                label.setText(slovv.getFirst().imi);
				chtenie.setText(slovv.getFirst().yomi);
				chtenie.setFont(japFt);
				slovv.removeFirst();
            }           
        });

    	Container container = this.getContentPane();
	    container.setLayout(new GridLayout(2,2));
	    container.add(label);
	    container.add(chtenie);
	    label.setFont(font);
	    chtenie.setFont(font);
	    button.setFont(font);
	    button2.setFont(font);

	    button.addActionListener(new ButtonEventListener());
	    button2.addActionListener(new ButtonEventListener2());
	    container.add(button);
	    container.add(button2);
	}
	class ButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (slovv.size() != 0){
				label.setText("<html>" + slovv.getFirst().imi + "</html>");
				chtenie.setText(slovv.getFirst().yomi);
				slovv.removeFirst();
			}
			else {
				String message = "Количество ошибок: " + kolosh + " из " + numb + " ";
				for (Slova a : slovosh){
					message += a.getKanji() + " ";
				}
				String[] mess = message.split(" ");
				for (int i = 4; i < mess.length; i+=12){
					mess[i] = mess[i] + "\n";
				}
				String[] viviv = String.join(" ",mess).split("\n");
				JLabel[] vivods = new JLabel[viviv.length];
				JLabel ftfirst = new JLabel(viviv[0]);
				vivods[0] = ftfirst;
				ftfirst.setFont(font);
				for(int i = 1; i<viviv.length; i++){
					JLabel ft = new JLabel(viviv[i]);
					ft.setFont(japFt);
					vivods[i] = ft;
				}
				chtenie.setFont(font);
				slovosh.clear();
				kolosh = 0;
				label.setText("Выберите уровень");
				chtenie.setText("Уровень -> *lvl*");
				JOptionPane.showMessageDialog(null,
		    		vivods,
		    		"Результат",
		    	    JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	class ButtonEventListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (slovv.size() != 0){
				label.setText("<html>" + slovv.getFirst().imi + "</html>");
				chtenie.setText(slovv.getFirst().yomi);
				slovv.removeFirst();
				kolosh = kolosh + 1;
			}
			else {
				kolosh = kolosh + 1;
				String message = "Количество ошибок: " + kolosh + " из " + numb + " ";
				for (Slova a : slovosh){
					message += a.getKanji() + " ";
				}
				String[] mess = message.split(" ");
				for (int i = 4; i < mess.length; i+=12){
					mess[i] = mess[i] + "\n";
				}
				String[] viviv = String.join(" ",mess).split("\n");
				JLabel[] vivods = new JLabel[viviv.length];
				for(int i = 0; i<viviv.length; i++){
					JLabel ft = new JLabel(viviv[i]);
					ft.setFont(japFt);
					vivods[i] = ft;
				}
				slovosh.clear();
				kolosh = 0;
				label.setText("Выберите уровень");
				chtenie.setText("Уровень -> *lvl*");
				JOptionPane.showMessageDialog(null,
		    		vivods,
		    		"Результат",
		    	    JOptionPane.PLAIN_MESSAGE);
			}
		}
	}	
}

class Slova implements Comparable<Slova>{
	String imi;
	String yomi;
	String kanji;
	public Slova(String imi, String yomi, String kanji){
		this.imi = imi;
		this.yomi = yomi;
		this.kanji = kanji;
	}
	public String getKanji(){
		return this.kanji;
	}
	@Override
	public int compareTo(Slova odin){
		int i = (int)Math.round(Math.random()*50 - 25);
		return i;
	}
	@Override
	public String toString(){
		return imi;
	}
}
