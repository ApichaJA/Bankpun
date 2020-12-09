import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GameStateManager{

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int INGAME = 1;
    public static final int TIMEOUT = 2;
    public static final int SCORE = 3;

    public GameStateManager(){

        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new InGameState(this));
        gameStates.add(new GameTimeout(this));

    }

    public void setState(int state){
        System.out.println(state);
            currentState = state;
            gameStates.get(currentState).init();
    }

    public void update(){
        gameStates.get(currentState).update();
    }

    public void draw(java.awt.Graphics2D g){
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k){
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k){
        gameStates.get(currentState).keyReleased(k);
    }

    public void mousePressed(int x, int y){
        if (currentState == 1)
        GameControler.mousePressed(x, y);
        if (currentState == 2)
        GameTimeout.mousePressed(x, y);
    }
}
