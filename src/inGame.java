import javax.swing.*;

public class inGame {
    private JFrame frame;
    private JPanel mainFrame;
    private JLabel txtScore;
    public inGame(){
        frame = new JFrame("Bank Pun");
        System.out.println("hello");
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
