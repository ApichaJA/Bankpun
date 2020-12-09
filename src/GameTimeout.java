import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameTimeout extends GameState {
    private static Rectangle mouseRact;
    private Font font, titleFont;
    private Background bg;
    private BufferedImage scoreText;

    public GameTimeout(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        GameControler.setStamina(1);
        bg = new Background("/open.png");
        try {
            scoreText = ImageIO.read(
                    getClass().getResourceAsStream("/Score.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update() {
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setColor(Color.BLACK);
        titleFont = new Font("Arial", Font.PLAIN, 48);
        font = new Font("Arial", Font.PLAIN, 68);
        g.setFont(font);
        g.drawString(GameControler.getScore()+"",440, 400);
        g.drawImage(scoreText, 200, 50, 4000,2080, null);
        g.setFont(titleFont);
        g.setColor(Color.ORANGE);
        g.fillRect(120, 550, 155,70);
        g.setColor(Color.BLACK);
        g.drawString("Retry",140, 600);
        g.setColor(Color.ORANGE);
        g.fillRect(420, 550, 155,70);
        g.setColor(Color.BLACK);
        g.drawString("Menu", 440, 600);
        g.setColor(Color.ORANGE);
        g.fillRect(720, 550, 125,70);
        g.setColor(Color.BLACK);
        g.drawString("Exit", 740, 600);
    }

    public static void mousePressed(int x, int y) {
        mouseRact = new Rectangle(x, y, 20, 20);
        if (mouseRact.intersects(new Rectangle(120, 550, 155,70))) {
            gsm.setState(GameStateManager.INGAME);
        }
        if (mouseRact.intersects(new Rectangle(420, 550, 155,70))) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if (mouseRact.intersects(new Rectangle(720, 550, 155,70))) {
            System.exit(1);
        }
    }
}
