package game;

import users.LogIn;

import java.util.ArrayList;
import java.util.List;

public class Game  {

    private List<Boolean> running = new ArrayList<>();
    private List<State> states = new ArrayList<State>();

    //States
    private State gameState;
    private State logInState;
    private State collectionState;

    private void init(){
        logInState = new LogIn();
        gameState = new GameState();
        running.add(true);
        states.add(logInState);
        states.add(gameState);


    }


    public void run() {
        //get things ready
        init();
        int i = 0;
        //update everything and render everything
        while(running.get(0)){
            states.get(i++).run(running);
            if (!running.get(1)){
                i = 0; //returns to the first state: LogIn
            }
        }
    }



}
