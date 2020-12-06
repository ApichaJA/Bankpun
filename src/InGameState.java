import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class InGameState extends GameState{
    private BufferedImage human;
    private BufferedImage window;
    private Background bg;
    private Background counter;

    private String[] item = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
    };

    private Font font;

    public InGameState(GameStateManager gsm){
        this.gsm = gsm;
        try{
            window = ImageIO.read(
                    getClass().getResourceAsStream("/window.png"));
            bg = new Background("/inGame.png");
            human = ImageIO.read(
                    getClass().getResourceAsStream("/human.png"));
            counter = new Background("/counter.png");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void init(){
        while(true){
            try{
                Thread.sleep(new GamePanel().getWait());
            }
            catch (Exception e){
                e.printStackTrace();
            }


        }
    }

    public void update(){

    }
    public void draw(Graphics2D g){
        bg.draw(g);
        g.drawImage(human, 600 % (GamePanel.WIDTH), 140 % (GamePanel.HEIGHT), 350,350, null);
        counter.draw(g);
        if (popUp.showSelect == 1){
            g.drawImage(window, 0, 0, 1380,820, null);
            font = new Font("Arial", Font.PLAIN, 48);
            g.setFont(font);
            g.setColor(Color.RED);
            int itemHposition = 50;
            int itemWposition = 80;
            for (int i = 0; i < item.length; i++){
                if (i%3==0){
                    itemHposition = itemHposition+=200;
                    itemWposition = 80;
                }

                g.drawString(item[i], itemWposition+= 200, itemHposition);
            }
        }
        if (popUp.showSelect == 2){
            g.drawImage(window, 0, 0, 1380,820, null);
            font = new Font("Arial", Font.PLAIN, 48);
            g.setFont(font);
            g.setColor(Color.RED);
            int itemHposition = 50;
            int itemWposition = 80;
            for (int i = 0; i < item.length; i++){
                if (i%3==0){
                    itemHposition = itemHposition+=200;
                    itemWposition = 80;
                }

                g.drawString(item[i], itemWposition+= 200, itemHposition);
            }
        }
        if (popUp.showSelect == 3){
            g.drawString("hello", 100, 100);
        }
    }
}
