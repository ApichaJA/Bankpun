import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState{

    private Background bg;
    private int currentChoice = 0;
    private String[] options = {
            "Start",
            "Score",
            "Quit"
    };

    private Color titleColor;
    private Font titleFont;

    private Font font;

    public MenuState(GameStateManager gsm){

        this.gsm = gsm;

        try{
            bg = new Background("/open.png");

            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Bankpun", Font.PLAIN, 64);

            font = new Font("Arial", Font.PLAIN, 48);


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
        g2.setColor(titleColor);
        g2.setFont(titleFont);
        g2.drawString("Bankpun", 100, 100);

        g2.setFont(font);
        for (int i = 0; i < options.length; i++){
            if (i == currentChoice){
                g2.setColor(Color.BLACK);
            }
            else {
                g2.setColor(Color.RED);
            }
            g2.drawString(options[i], 100, 220 + i * 70);
        }
    }
    private void select(){
        if (currentChoice == 0){
            gsm.setState(GameStateManager.INGAME);
        }
        if (currentChoice == 1){

        }
        if (currentChoice == 2){
            System.exit(0);
        }
    }
    public void keyPressed(int k){
        if (k == KeyEvent.VK_ENTER){
            select();
        }
        if (k == KeyEvent.VK_DOWN){
            if (currentChoice != options.length-1){
                currentChoice++;
            }
        }
        if (k == KeyEvent.VK_UP){
            if (currentChoice != 0){
                currentChoice--;
            }
        }
    }
}
