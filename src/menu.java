import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu implements ActionListener{
    private JFrame fr;
    private JPanel f ,r, d;
    private JButton playbtn, exitbtn;

    public menu(){
        fr = new JFrame("Teller GUI");
        f = new JPanel();
        d = new JPanel();
        d.setLayout(new BorderLayout());
        playbtn = new JButton("Play");
        exitbtn = new JButton("Exit");

        f.add(playbtn);f.add(exitbtn);
        d.add(f, BorderLayout.CENTER);
        playbtn.addActionListener(this);
        exitbtn.addActionListener(new ActionHandler());

        fr.add(d);
        fr.setVisible(true);
        fr.pack();
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(playbtn)) {
            System.out.println("Play game");
        }
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Close Game");
            System.exit(0);
        }
    }

}
