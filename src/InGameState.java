import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.Arrays;
import java.util.Random;



public class InGameState extends GameState{
    private BufferedImage human;
    private BufferedImage window;
    private BufferedImage submit;
    private BufferedImage car;
    private BufferedImage scoreTap;
    private BufferedImage phoneTab;


    static BufferedImage[] listItem = new BufferedImage[9];
    public static int[][] ListItemGen = new int[9][3];


    static BufferedImage[] phoneImg = new BufferedImage[5];
    public static String[] phoneImgList = new String[]{
            "Phone1380-1",
            "Phone1380-2",
            "Phone1380-3",
            "Phone1380-4",
            "Phone1380-5",
    };
    public static int spikePhone = 0;

    public static int posiX = 0;
    public static int posiY = 0;

    public static boolean alertObject = true;


    public static int countOrderCus = 0;

    public static String[] CustomerOrder = new String[4];
    static BufferedImage[] CustomerItem = new BufferedImage[5];

    public static boolean newOrder = false;

    private itemList itl = new itemList();
    public static String[] clickItem = new String[9];
    public static int[] correct = new int[2];
    public static int[][] wrongans = new int[4][6];
    public static int[] wrongClick = new int[2];
    private Background bg;
    private Background counter;
    private Background signPopup;
    private Background comPopup;

    public int random_int1, random_int2, random_int3;
    public int randomChoice;

    public static String[] item = new String[8];
    private Font font;
    private Font fontHeadPrice;

    public InGameState(GameStateManager gsm){
        this.gsm = gsm;
        randomItem();
        init();

    }

    public void init(){

        try{
            for (int j = 0; j < item.length; j++){
                listItem[j] = ImageIO.read(
                        getClass().getResourceAsStream("/"+item[j]+".png"));
            }
            for (int c = 0; c < CustomerOrder.length; c++) {
                if (CustomerOrder[c] != null) {
                    CustomerItem[c] = ImageIO.read(
                            getClass().getResourceAsStream("/" + CustomerOrder[c] + ".png"));
                }
            }
            for (int p = 0; p < phoneImgList.length; p++){
                phoneImg[p] = ImageIO.read(
                        getClass().getResourceAsStream("/"+phoneImgList[p]+".png"));
            }


            window = ImageIO.read(
                    getClass().getResourceAsStream("/window.png"));
            submit = ImageIO.read(
                    getClass().getResourceAsStream("/submit.png"));

            human = ImageIO.read(
                    getClass().getResourceAsStream("/human.png"));
            car = ImageIO.read(
                    getClass().getResourceAsStream("/carrrrr.png"));
            scoreTap = ImageIO.read(
                    getClass().getResourceAsStream("/score.png"));
            phoneTab = ImageIO.read(
                    getClass().getResourceAsStream("/PhoneTab.png"));

            bg = new Background("/inGame.png");
            counter = new Background("/counter.png");
            signPopup = new Background("/signPopup.png");
            comPopup = new Background("/comPopup.png");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void update(){
        GameControler.setStamina(-1);
        if (GameControler.getStamina() < 10) {
            gsm.setState(GameStateManager.TIMEOUT);
        }
    }

    public void randomNum(){
        Random rand = new Random();
        random_int1 = (int)(Math.random() * (10 - 5 + 1) + 2);
        random_int2 = (int)(Math.random() * (40 + 20 + 1) + 20);
        random_int3 = (int)(Math.random() * (20 - 10 + 1) + 10);
        randomChoice = rand.nextInt(4);
        }

        public void randomItem(){
        String[] itemStack = new String[20];
            Random rand = new Random();
            int count = 0;
            for (int randItem = 0; randItem < 8; randItem++){
                count++;
                String thisItem = itemList.pickItem(rand.nextInt(15));
                int toOrder = rand.nextInt(2);
                if (!Arrays.stream(itemStack).anyMatch(thisItem::equals)){
                    item[randItem] = thisItem;
                }
                    if (toOrder == 0 && count < 4) {
                        CustomerOrder[count] = thisItem;
                        countOrderCus++;
                    }
                    else if (count < 2 && randItem > 5){
                        CustomerOrder[count] = thisItem;
                        countOrderCus++;
                    }
            }
        }


    public void draw(Graphics2D g){
        //Setup Background human counter
        bg.draw(g);
        g.drawImage(human, 600 % (GamePanel.WIDTH), 140 % (GamePanel.HEIGHT), 350,350, null);
        g.drawImage(car, posiX, posiY, 1380,820, null);


        //Setup Score
        counter.draw(g);
        font = new Font("Arial", Font.PLAIN, 48);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawImage(scoreTap, 0, 0, 1380,820, null);

        g.drawString(GameControler.getScore()+"", 220, 65);
        font = new Font("Arial", Font.PLAIN, 18);

        g.setFont(font);
        g.drawString(String.format("x%1.1f", GameControler.getStack()), 170, 55);

        //Setup Order menu
        int orderListX = 1200;
        g.drawImage(phoneTab, 200, 0, 1180,620, null);
                for (int cusItm = 0; cusItm < CustomerItem.length; cusItm++) {
                    if (CustomerItem[cusItm] != null) {
                        g.drawImage(CustomerItem[cusItm], orderListX -= 150, 0, 150, 150, null);
                    }
                }
        if (newOrder){
            g.drawImage(phoneImg[spikePhone],200, 0, 1180, 620, null);
        }

        //Set stamina
        g.setColor(Color.WHITE);
        g.fillRect(650, 700, 700, 50);
        g.setColor(Color.CYAN);
        if (GameControler.getStamina() < 400)
            g.setColor(Color.ORANGE);
        if (GameControler.getStamina() < 200)
            g.setColor(Color.RED);
        g.fillRect(650, 700, GameControler.getStamina(), 50);

        // Computer Noti
        g.setColor(Color.WHITE);
        if (alertObject && popUp.showSelect == 0) {
            comPopup.draw(g);
            try{
                for (int c = 0; c < CustomerOrder.length; c++) {
                    if (CustomerOrder[c] != null) {
                        CustomerItem[c] = ImageIO.read(
                                getClass().getResourceAsStream("/" + CustomerOrder[c] + ".png"));
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        if (popUp.showSelect == 1){
            alertObject = false;
            g.drawImage(window, 0, 0, 1380,820, null);
            g.drawImage(submit, 0, 0, 1380,820, null);
            int itemHposition = 0;
            int itemWposition = 120;
            for (int i = 0; i < item.length; i++){

                if (i <1){
                    itemHposition = 100;
                }
                else if (i%2==0){
                    itemHposition += 150;
                    itemWposition = 120;
                }
                else {
                    itemWposition = 370;
                }
                ListItemGen[i][0] = i;
                    for (int d2 = 1; d2 < 3; d2++){
                        if (d2 == 1){
                            ListItemGen[i][d2] = itemWposition;
                        }
                        else
                            ListItemGen[i][d2] = itemHposition+60;
                    }
                if (clickItem[i] == item[i] && clickItem[i] != null && item[i] != null) {
                    g.setColor(Color.ORANGE);
                    g.fillOval(itemWposition+45, itemHposition+35, 120,120);
                }
                g.drawImage(listItem[i], itemWposition, itemHposition, 200,200, null);
            }
            randomNum();
        }

        if (popUp.showSelect == 2){
            font = new Font("Arial", Font.PLAIN, 48);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawImage(window, 0, 0, 1380,820, null);
            g.drawString("Order List", 230, 160);
            int itemHposition = 150;
            int countItem = 0;
            int value = 0;
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;

            for (int i = 0; i < clickItem.length; i++){
                if (clickItem[i] != null) {
                    countItem++;
                    font = new Font("Arial", Font.PLAIN, 28);
                    g.setFont(font);
                    g.drawString(countItem+") "+ clickItem[i], 180, itemHposition+=75);
                    g.drawString( itl.itemList(clickItem[i]) +" $", 420, itemHposition);
                    value += Integer.parseInt(itl.itemList(clickItem[i]));
                    value1 = value;
                    value2 = value;
                    value3 = value;
                }
            }

            fontHeadPrice = new Font("Arial", Font.PLAIN, 18);
            g.setFont(fontHeadPrice);
            g.drawString("Choose The Correct Price", 150, itemHposition+80);

            int[]price = new int[]{
                    value,
                    value1+random_int1,
                    value2+random_int2,
                    value3+random_int3,
            };
            int sumWposition = 0;
            int sumHposition = itemHposition+150;

            for (int ran = 0; ran < 4; ran++) {

                if (ran == 2){
                    sumWposition = 0;
                    sumHposition = itemHposition += 260;
                }
                if (ran == 0){
                    correct[0] = sumWposition+240;
                    correct[1] = sumHposition-15;
                }
                if (ran != 0){
                    wrongans[ran][0] = sumWposition+240;
                    wrongans[ran][1] = sumHposition-15;
                }
                g.setFont(font);
                if (wrongClick[0] != 0) {
                    GameControler.playsoundShort("incorrect");
                    wrongClick[0]=0;
                    wrongClick[1]=0;
                }

                g.setColor(Color.ORANGE);
                g.fillOval(sumWposition+195, sumHposition-60, 90, 90);
                g.setColor(Color.WHITE);
                g.drawString(price[ran] + "$", sumWposition+=210, sumHposition);
}
            }
        if (popUp.showSelect == 3){
            listItem = new BufferedImage[9];
            CustomerItem = new BufferedImage[5];
            item = new String[8];
            CustomerOrder = new String[4];
            clickItem = new String[9];
            countOrderCus = 0;
            randomItem();
            try {
                for (int j = 0; j < item.length; j++) {
                    listItem[j] = ImageIO.read(
                            getClass().getResourceAsStream("/" + item[j] + ".png"));
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            if (alertObject && popUp.showSelect == 3) {
                signPopup.draw(g);

            }
        }
    }
}
