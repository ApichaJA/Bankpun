import javax.swing.*;

public class Game {

    public static void main(String[] args) {
        JFrame window = new JFrame("Bank pun");
        window.setContentPane(new GamePanal());
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
