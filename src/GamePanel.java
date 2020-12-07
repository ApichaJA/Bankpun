import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener{

    public static final int WIDTH = 1380;
    public static final int HEIGHT = 820;

    private Thread thread;
    private boolean running;
    private long targetTime = 1000 / 60;

    private BufferedImage image;
    private Graphics2D g;

    private GameStateManager gsm;

    private Rectangle mouseRact;

    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();
        if (thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            addMouseListener(this);
            thread.start();
        }
    }

    public void init(){
        image = new BufferedImage(
                WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB
        );

        g = (Graphics2D) image.getGraphics();

        running = true;

        gsm = new GameStateManager();
    }

    public void run(){
        init();
        long start;
        long elapsed;
        long wait;

        while (running){

            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;
            if (wait < 0)
                wait = 5;

            try{
                Thread.sleep(wait);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public long getWait(){
        return targetTime;
    }

    private void update(){
        gsm.update();
    }
    private void draw(){
        gsm.draw(g);
    }
    private void drawToScreen(){
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);

        g2.dispose();
    }

    public void keyTyped(KeyEvent key){}
    public void keyPressed(KeyEvent key){
        gsm.keyPressed(key.getKeyCode());
    }
    public void keyReleased(KeyEvent key){
        gsm.keyReleased(key.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getX());
        mouseRact = new Rectangle(e.getX(), e.getY(), 20, 20);
        if (popUp.showSelect == 0) {
            if (mouseRact.intersects(new Rectangle(600 % (GamePanel.WIDTH), 140 % (GamePanel.HEIGHT), 350, 350))) {
                new popUp(1);
            }
        }
        if (popUp.showSelect == 1){
            if (mouseRact.intersects(new Rectangle(424, 720, 200, 75)))
                popUp.showSelect = 2;
            for (int d1 = 0; d1 < 9; d1++){
                    if (mouseRact.intersects(new Rectangle(InGameState.ListItemGen[d1][1], InGameState.ListItemGen[d1][2], 200, 100))){
                        if (InGameState.clickItem[d1] != InGameState.item[InGameState.ListItemGen[d1][0]])
                            InGameState.clickItem[d1] = InGameState.item[InGameState.ListItemGen[d1][0]];
                        else if (InGameState.clickItem[d1] == InGameState.item[InGameState.ListItemGen[d1][0]])
                            InGameState.clickItem[d1] = null;
                    }
            }

        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}