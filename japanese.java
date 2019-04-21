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
		LinkedList<Slova> slova = new LinkedList<>();
		try{
			if(new File("slova.xml").canRead()){
				Path path = Paths.get("slova.xml");
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
		slovv.addAll(slova);
		japanese jp = new japanese();
		jp.setVisible(true);
	}
	JButton button = new JButton("вышло");
	JButton button2 = new JButton("неа(");
	JLabel label = new JLabel(slovv.getFirst().imi);
	JLabel chtenie = new JLabel(slovv.getFirst().yomi);
	int kolosh = 0;
	int vsego = slovv.size();

	public japanese(){
		super("Треня");
		slovv.removeFirst();
    	this.setBounds(500,100,250,400);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
				String message = "количество ошибок: " + kolosh + " из " + vsego;
				JOptionPane.showMessageDialog(null,
		    		message,
		    		"Output",
		    	    JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	class ButtonEventListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			label.setText(slovv.getFirst().imi);
			chtenie.setText(slovv.getFirst().yomi);
			slovv.removeFirst();
			kolosh = kolosh + 1;
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
