import javax.swing.*;
import java.awt.*;

public class Game {
    private JFrame frame;
    private JPanel menubar, menuGroup, menuText;
    private JButton play, score, exit;
    public Game() {
        System.out.println("Let's Go!");
        frame = new JFrame("Bank Pun");
        menubar = new JPanel();
        menuGroup = new JPanel();
        menuText = new JPanel();
        menubar.setLayout(new BorderLayout());
        menuText.setLayout(new GridLayout(3, 1));
        menuGroup.setLayout(new FlowLayout());
        menuText.add(play = new JButton("Play"));
        menuText.add(score = new JButton("Score"));
        menuText.add(exit = new JButton("Exit"));
        menuText.setBackground(Color.gray);
        menuGroup.setBackground(Color.gray);
//      menuText.setBorder(BorderFactory.createEmptyBorder(100,0, 10, 0));
        menuGroup.add(menuText);
        menubar.add(menuGroup, BorderLayout.WEST);

        frame.add(menubar);
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
