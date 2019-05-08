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
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class UserEditMenu extends JFrame{
	JFrame choose = new JFrame("chose your level");





	static LinkedList<Slova> slovv = new LinkedList<>();
	UserEditMenu(){
		super("chose your words");
		setVisible(false);
		choose.setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int locationX = (screenSize.width - 300) / 2;
		int locationY = (screenSize.height - 200) / 2;
    	choose.setBounds(locationX, locationY,250,80);
		Container container = choose.getContentPane();
	    container.setLayout(new FlowLayout());
	    JButton n2 = new JButton("N2");
	    JButton n3 = new JButton("N3");
	    JButton n4 = new JButton("N4");
	    JButton n5 = new JButton("N5");
	    container.add(n2);
	    container.add(n3);
	    container.add(n4);
	    container.add(n5);

	    n4.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
            	chooser("4");
            }           
        });
        n3.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
            	chooser("3");
            }           
        });
        n2.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
            	chooser("2");
            }           
        });
        n5.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
            	chooser("5");
            }           
        });
	}
	public void chooser(String option){
		try{
        			reader("japanese/slova"+ option + ".xml");
        		}catch(Exception ee){}
        		String[] colle = new String[slovv.size()];
        		int i = 0;
        		for (Slova col : slovv){
        			colle[i] = col.getKanji() + " " + col.getImi() + " " + col.getYomi();
        			i++;
        		}
        		CheckBoxGroup group = new CheckBoxGroup(option, colle);
        		add(group);
        		setSize(300, 300);
        		choose.setVisible(false);
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

class CheckBoxGroup extends JPanel {

        private JCheckBox all;
        private List<JCheckBox> checkBoxes;
        ArrayList<String> userEdit = new ArrayList<String>();
        public ArrayList<String> getColl(){
        	return this.userEdit;
        }

        public CheckBoxGroup(String option,String... options) {
            checkBoxes = new ArrayList<>(25);
            setLayout(new BorderLayout());
            JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 1));
            all = new JCheckBox("Выбрать все");
            all.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (JCheckBox cb : checkBoxes) {
                    	cb.setFont(Japanese.japFt);
                        cb.setSelected(all.isSelected());
                    }
                }
            });
            header.add(all);
            add(header, BorderLayout.NORTH);

            JPanel content = new ScrollablePane(new GridBagLayout());
            content.setBackground(UIManager.getColor("List.background"));
            if (options.length > 0) {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.anchor = GridBagConstraints.NORTHWEST;
                gbc.weightx = 1;
                for (int index = 0; index < options.length - 1; index++) {
                    JCheckBox cb = new JCheckBox(options[index]);
                    final String str = options[index];
                    cb.setOpaque(false);
                    checkBoxes.add(cb);
                    content.add(cb, gbc);
                    cb.addItemListener(new ItemListener() {
    				public void itemStateChanged(ItemEvent e) {
    					if(cb.isSelected()){
        					System.out.println(str);
        					userEdit.add(str);
        					System.out.println(userEdit);
        					try{
        						save(userEdit,option);
        					}catch(IOException ex){}
        				}
        				else{
        					userEdit.remove(str);
        				}
    				}
                });}

                JCheckBox cb = new JCheckBox(options[options.length - 1]);
                cb.setOpaque(false);
                checkBoxes.add(cb);
                gbc.weighty = 1;
                content.add(cb, gbc);

            }

            add(new JScrollPane(content));

        }
        public void save(ArrayList<String> opt, String option) throws IOException
		    {
		        String userPort = "japanese/"+"userEditor"+ option +".xml";

		        BufferedWriter pw = new BufferedWriter(new FileWriter(userPort));
		        try{
		            pw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
		            pw.write("<Slova>\n");
		            opt.stream().sorted().forEach(p -> {
		            	String[] ayayaya = p.split(" ");
		            	String[] strtr = new String[ayayaya.length-2];
		            	for(int i = 1; i<(ayayaya.length-1); i++){
		               		strtr[i-1] = ayayaya[i];
		                }
		                String fin = String.join(" ",strtr);
		                try{
		                    pw.write("<Slova imi=\"" + fin + "\" yomi=\"" + ayayaya[ayayaya.length-1] + "\" kanji=\"" + ayayaya[0] + "\"/>\n");
		                }catch(IOException aiai){aiai.printStackTrace();}

		            });
		            pw.write("</Slova>");
		            pw.close();
		        }catch(IOException oioi){oioi.printStackTrace();}
			}

        public class ScrollablePane extends JPanel implements Scrollable {

            public ScrollablePane(LayoutManager layout) {
                super(layout);
            }

            public ScrollablePane() {
            }

            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(100, 100);
            }

            @Override
            public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
                return 32;
            }

            @Override
            public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
                return 32;
            }

            @Override
            public boolean getScrollableTracksViewportWidth() {
                boolean track = false;
                Container parent = getParent();
                if (parent instanceof JViewport) {
                    JViewport vp = (JViewport) parent;
                    track = vp.getWidth() > getPreferredSize().width;
                }
                return track;
            }

            @Override
            public boolean getScrollableTracksViewportHeight() {
                boolean track = false;
                Container parent = getParent();
                if (parent instanceof JViewport) {
                    JViewport vp = (JViewport) parent;
                    track = vp.getHeight() > getPreferredSize().height;
                }
                return track;
            }

    	}
}