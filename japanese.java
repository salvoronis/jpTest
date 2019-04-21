import java.util.LinkedList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.util.Scanner;
import java.util.Collections;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class japanese extends JFrame{
	static LinkedList<Slova> slovv = new LinkedList<>();
	public static void main (String [] args){
		japanese jp = new japanese();
		jp.setVisible(true);
	}


	public static void reader(String put){
		LinkedList<Slova> slova = new LinkedList<>();
		slovv = slova;
		try{
			if(new File(put).canRead()){
				Path path = Paths.get(put);
				Scanner scan = new Scanner(path);
				while(scan.hasNext()){
					String line = scan.nextLine();
					if(line.contains("<Slova ")){
						String imi = line.substring(line.indexOf("=") + 2, line.lastIndexOf(" ") - 1);
						String yomi = line.substring(line.lastIndexOf("=") + 2, line.length() - 3);
						slova.add(new Slova(imi, yomi));
					}
				}
			}
			else{System.out.println("Дайте права для чтения файла");}
		}
		catch(IOException e){
			System.out.println("не найден файл");
		}
		Collections.sort(slova);
		slovv = slova;
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
	JLabel label = new JLabel("Выберите уровень");
	JLabel chtenie = new JLabel("Уровень -> *lvl*");
	int kolosh = 0;
	int vsego;
	static String put = "slova.xml";

	public japanese(){
		super("Тест иероглифы");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int locationX = (screenSize.width - 300) / 2;
		int locationY = (screenSize.height - 200) / 2;
    	this.setBounds(locationX, locationY,300,200);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setJMenuBar(menuBar);

    	menuBar.add(fileMenu);
    	menuBar.add(exitM);
    	exitM.add(exit);
    	fileMenu.add(n5);
    	fileMenu.add(n4);
    	fileMenu.add(n3);
    	fileMenu.add(n2);

    	exit.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                System.exit(0);             
            }           
        });
    	n4.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                put = "slova4.xml";
                reader(put);
                label.setText(slovv.getFirst().imi);
				chtenie.setText(slovv.getFirst().yomi);
				slovv.removeFirst();
            }           
        });
        n5.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                put = "slova5.xml";
                reader(put);
                label.setText(slovv.getFirst().imi);
				chtenie.setText(slovv.getFirst().yomi);
				slovv.removeFirst();
            }           
        });
        n3.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                put = "slova3.xml";
                reader(put);
                label.setText(slovv.getFirst().imi);
				chtenie.setText(slovv.getFirst().yomi);
				slovv.removeFirst();
            }           
        });
        n2.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                put = "slova2.xml";
                reader(put);
                System.out.println(slovv);
                label.setText(slovv.getFirst().imi);
				chtenie.setText(slovv.getFirst().yomi);
				slovv.removeFirst();
            }           
        });

    	Container container = this.getContentPane();
	    container.setLayout(new GridLayout(2,2));
	    container.add(label);
	    container.add(chtenie);

	    button.addActionListener(new ButtonEventListener());
	    button2.addActionListener(new ButtonEventListener2());
	    container.add(button);
	    container.add(button2);
	}
	class ButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (slovv.size() != 0){
				label.setText(slovv.getFirst().imi);
				chtenie.setText(slovv.getFirst().yomi);
				slovv.removeFirst();
			}
			else {
				String message = "Количество ошибок: " + kolosh + " из " + numb;
				kolosh = 0;
				label.setText("Выберите уровень");
				chtenie.setText("Уровень -> *lvl*");
				JOptionPane.showMessageDialog(null,
		    		message,
		    		"Output",
		    	    JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	class ButtonEventListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (slovv.size() != 0){
				label.setText(slovv.getFirst().imi);
				chtenie.setText(slovv.getFirst().yomi);
				slovv.removeFirst();
				kolosh = kolosh + 1;
			}
			else {
				kolosh = kolosh + 1;
				String message = "Количество ошибок: " + kolosh + " из " + numb;
				kolosh = 0;
				label.setText("Выберите уровень");
				chtenie.setText("Уровень -> *lvl*");
				JOptionPane.showMessageDialog(null,
		    		message,
		    		"Output",
		    	    JOptionPane.PLAIN_MESSAGE);
			}
		}
	}	
}

class Slova implements Comparable<Slova>{
	String imi;
	String yomi;
	public Slova(String imi, String yomi){
		this.imi = imi;
		this.yomi = yomi;
	}
	@Override
	public int compareTo(Slova odin){
		int i = (int)Math.round(Math.random()*50 - 25);
		return i;
	}
}
