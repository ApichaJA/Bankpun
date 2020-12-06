import javax.swing.*;
import java.awt.*;

public class GamePanal extends JPanel implements Runnable{

    public static final int WIDTH = 1380;
    public static final int HEIGHT = 820;


    public boolean running = false;

    public GamePanal(){
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }


    public void init(){
        running = true;
        new MenuState();
    }

    public void run(){
        init();

        while (running){
            update();
            drawToScreen();

            try{
                Thread.sleep(1000);
                System.out.println(1);
            }
            catch (Exception e){
                System.out.println(e);
            }

        }
    }

    private void update(){}
    private void drawToScreen(){}

}
