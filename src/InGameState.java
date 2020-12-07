import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;


public class InGameState extends GameState{
    private BufferedImage human;
    private BufferedImage window;
    private BufferedImage submit;
    BufferedImage[] listItem = new BufferedImage[9];
    public static int[][] ListItemGen = new int[9][3];

    public static String[] clickItem = new String[9];

    private Background bg;
    private Background counter;

    public static String[] item = new String[]{
            "baget",
            "cakeblue",
            "cakefrozen",
            "donut",
            "cupcakestr",
            "milktea",
            "pie",
            "whitebread",
    };

    private Font font;

    public InGameState(GameStateManager gsm){
        this.gsm = gsm;
        try{
            for (int j = 0; j < item.length; j++){
                listItem[j] = ImageIO.read(
                        getClass().getResourceAsStream("/"+item[j]+".png"));
            }
            window = ImageIO.read(
                    getClass().getResourceAsStream("/window.png"));
            submit = ImageIO.read(
                    getClass().getResourceAsStream("/submit.png"));
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
            g.drawImage(submit, 0, 0, 1380,820, null);
            int itemHposition = 0;
            int itemWposition = 0;
            for (int i = 0; i < item.length; i++){
                if (i%2==0){
                    itemHposition += 120;
                    itemWposition = 95;
                }
                ListItemGen[i][0] = i;
                    for (int d2 = 1; d2 < 3; d2++){
                        if (d2 == 1){
                            ListItemGen[i][d2] = itemWposition+ 200;
                        }
                        else
                            ListItemGen[i][d2] = itemHposition+0;
                    }
                if (clickItem[i] == item[i]) {
                    g.setColor(Color.GREEN);
                    g.fillRect(itemWposition+ 225, itemHposition+25, 150,150);
                }
                g.drawImage(listItem[i], itemWposition+= 200, itemHposition+0, 200,200, null);
            }
        }

        if (popUp.showSelect == 2){
            g.drawImage(window, 0, 0, 1380,820, null);
            g.drawString("Order List", 450, 100);
            font = new Font("Arial", Font.PLAIN, 28);
            g.setFont(font);
            g.setColor(Color.BLACK);
            int itemHposition = 150;
            for (int i = 0; i < clickItem.length; i++){
                if (clickItem[i] != null) {
                    g.drawString(i+1+" "+clickItem[i], 300, itemHposition+=75);
                }

            }
        }
        if (popUp.showSelect == 3){
            g.drawString("hello", 100, 100);
        }
    }
}
