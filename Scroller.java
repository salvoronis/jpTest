import java.awt.*;

import javax.swing.*;

public class Scroller extends JFrame {

    public Scroller() throws HeadlessException {
        final JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.red));
        panel.setPreferredSize(new Dimension(400, 600));

        final JScrollPane scroll = new JScrollPane(panel);

        //JPanel hui = new JPanel();
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));
        panel.add(new JLabel("pidorstvo"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        //add(hui, BorderLayout.CENTER);
        setSize(300, 300);
        setVisible(true);
    }

    public static void main(final String[] args) throws Exception {
        //SwingUtilities.invokeLater(new Runnable() {
          //  @Override
            //public void run() {
                new Scroller().setVisible(true);
            //}
        //});
    }
}