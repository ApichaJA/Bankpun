import javax.swing.*;
import java.awt.*;

public class InGameState extends GameState {
    private Background bg;

    private JPanel jp;
    private JButton bt;

    public InGameState(GameStateManager gsm){
        jp = new JPanel();
        jp.setLayout(new FlowLayout());
        bt = new JButton("Hello");
        bt.setBackground(Color.gray);
        jp.add(bt);
        jp.setVisible(true);
        this.gsm = gsm;
        try{
            bg = new Background("/inGame.png");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void init(){}

    public void update(){}
    public void paintComponent(Graphics2D g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        bg.draw(g2);
    }
}
