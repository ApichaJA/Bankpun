import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener{
    private JFrame frame;
    private JPanel mainFrame, menuGroup, menuText, showScore;
    private JButton play, score, exit;
    private JLabel txtScore;

    public Game() {
        System.out.println("Let's Go!");
        frame = new JFrame("Bank Pun");

        //Menu Frame
        mainFrame = new JPanel();
        menuGroup = new JPanel();
        menuText = new JPanel();
        mainFrame.setLayout(new BorderLayout());
        menuText.setLayout(new GridLayout(3, 1));
        menuGroup.setLayout(new FlowLayout());
        menuText.add(play = new JButton("Play"));
        menuText.add(score = new JButton("Score"));
        menuText.add(exit = new JButton("Exit"));
        menuText.setBackground(Color.gray);
        menuGroup.setBackground(Color.gray);
        menuGroup.add(menuText);
        mainFrame.add(menuGroup, BorderLayout.WEST);
        play.addActionListener(this);
        score.addActionListener(this);
        exit.addActionListener(new ActionHandler());
        mainFrame.setVisible(true);


        //Show Score Frame
        showScore = new JPanel();
        showScore.setLayout(new FlowLayout());
        txtScore = new JLabel("All Player Score");
        showScore.add(txtScore);
        showScore.setVisible(false);
        mainFrame.add(showScore);


        //Main Frame
        frame.add(mainFrame);
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(play)){
            new inGame();
        }
        if(e.getSource().equals(score)){
            System.out.println("Show Score");
            showScore.setVisible(true);
        }

        }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
