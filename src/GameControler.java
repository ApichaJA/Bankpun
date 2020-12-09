import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.*;
import java.io.File;

public class GameControler extends GameState {
    private static int myScore = 0;
    private static double StackScore = 1;
    protected static boolean beginGame = true;

    private static Rectangle mouseRact;

    private static int myStamina = 1;

    public static int setScore(int status){
        if (status == 1) {
            for (double upScore = 0; upScore < 500 * StackScore; upScore++) {
                myScore++;
                try {
                    Thread.sleep(1);
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
            if (StackScore < 3.9)
                StackScore += .1;
        }
        else {
            if (myScore-(100*StackScore) >= 0)
                myScore-=(100*StackScore);
            StackScore = 1;
        }
        return 0;
    }

    public static int getScore(){
        return myScore;
    }

    public static double getStack(){
        return StackScore;
    }

    public static int getStamina(){
        return myStamina;
    }

    public static int setStamina(int num){
        if (beginGame) {
            myStamina+=30;
            if (myStamina > 700)
                beginGame = false;

        }
        if(!beginGame && num + myStamina > 700)
            myStamina = 700;
        else if(!beginGame)
            myStamina = num + myStamina;
        return 0;
        }


    public static void mousePressed(int x, int y) {
        mouseRact = new Rectangle(x, y, 20, 20);
        if (popUp.showSelect == 0) {
            if (mouseRact.intersects(new Rectangle(600 % (GamePanel.WIDTH), 140 % (GamePanel.HEIGHT), 350, 350))) {
                new popUp(1);
            }
        }
        if (popUp.showSelect == 1){
            InGameState.alertObject = false;
            int countOrder = 0;
            int countmylist = 0;

            int compareCount = 0;
            String[] order = new String[InGameState.countOrderCus];
            String[] mylist = new String[InGameState.countOrderCus];


            if (mouseRact.intersects(new Rectangle(424, 720, 200, 75))){
                for (int compare = 0; compare < InGameState.CustomerOrder.length; compare++) {
                    if (InGameState.CustomerOrder[compare] != null) {
                        order[countOrder] = InGameState.CustomerOrder[compare];
                        countOrder++;
                    }
                    if (InGameState.clickItem[compare] != null) {
                        mylist[countmylist] = InGameState.clickItem[compare];
                        countmylist++;
                    }
                }
                for (int comagin = 0; comagin < InGameState.countOrderCus; comagin++){
                    if (order[comagin] == mylist[comagin]){
                        compareCount++;
                    }
                }
                if (compareCount == InGameState.countOrderCus){
                    GameControler.setStamina(100);
                    playsoundShort("correct");
                }
                else{
                    GameControler.setStamina(-50);
                    playsoundShort("incorrect");
                    if (GameControler.getScore() - 50 >= 0)
                        GameControler.setScore(0);
                }
                popUp.showSelect = 2;
            }

            for (int d1 = 0; d1 < 9; d1++){
                if (mouseRact.intersects(new Rectangle(InGameState.ListItemGen[d1][1], InGameState.ListItemGen[d1][2], 200, 100))){
                    int countNull = 0;
                    if (InGameState.clickItem[d1] != InGameState.item[InGameState.ListItemGen[d1][0]]){
                        for (int j = 0; j < InGameState.clickItem.length; j++){
                            if (InGameState.clickItem[j] != null){
                                countNull++;
                            }
                        }
                        if (countNull < 4)
                            InGameState.clickItem[d1] = InGameState.item[InGameState.ListItemGen[d1][0]];
                    }
                    else if (InGameState.clickItem[d1] == InGameState.item[InGameState.ListItemGen[d1][0]])
                        InGameState.clickItem[d1] = null;
                }
            }
        }
        if (popUp.showSelect == 2){
//            System.out.println(InGameState.currect[0] +" : "+ InGameState.currect[1]);
//            System.out.println(e.getX()+" : "+e.getY());
            if (mouseRact.intersects(new Rectangle(InGameState.correct[0], InGameState.correct[1], 75, 25))) {
                popUp.showSelect = 3;
                GameControler.setStamina(100);
                animate.alertObject();
            }
            else {
                for (int wrongquestion = 0; wrongquestion < InGameState.wrongans.length; wrongquestion++) {
                    if (mouseRact.intersects(new Rectangle(InGameState.wrongans[wrongquestion][0], InGameState.wrongans[wrongquestion][1], 75, 25))) {
                        InGameState.wrongClick[0] = InGameState.wrongans[wrongquestion][0];
                        InGameState.wrongClick[1] = InGameState.wrongans[wrongquestion][1];
                        GameControler.setScore(0);
                        GameControler.setStamina(-50);
                    }
                }
            }
        }
        if (popUp.showSelect == 3){
            if (mouseRact.intersects(new Rectangle(430, 300, 80, 200))){
                GameControler.playsoundShort("Delivery");
                InGameState.alertObject = false;
                popUp.showSelect = 0;
                animate.Delivery("Go");
                animate.upScore();
                animate.notiPhone();
                animate.alertObject();
            }
        }
    }
    public static void playsound(String fil){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Resources/"+fil+".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void playsoundShort(String fil){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Resources/"+fil+".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-5.0f);
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
