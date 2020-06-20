package ap.hearthstone.control;

import ap.hearthstone.mvvm.StateData;
import ap.hearthstone.states.*;
import ap.hearthstone.states.collection.Collection;
import ap.hearthstone.states.logIn.LogInMenu;
import ap.hearthstone.states.logIn.SignUp;
import ap.hearthstone.states.shop.Shop;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Admin implements Runnable {

    private static Admin instance;
    //this class is responsible for running the game flow:)))
    // TA: add the panel you want to your mainFrame (or tv)
    //

    private Map<String,State> stateMap = new HashMap<>();
    private State currentState;
    private boolean gameRunning = false;
    private List<Request> requests;

    private Admin(){
        // get all the states single object. maybe singleton:)
        stateMap.put("login", LogInMenu.getInstance());
        stateMap.put("signup", SignUp.getInstance());

        stateMap.put("game", PlayState.getInstance());
        stateMap.put("collection", Collection.getInstance());
        stateMap.put("shop", Shop.getInstance());
        stateMap.put("status", Status.getInstance());
        init();
    }

    public static Admin getInstance() {
        if(instance == null){
            instance = new Admin();
        }
        return instance;
    }

    private void init(){
        currentState  = stateMap.get("logIn");
        gameRunning = true;
    }


    public void addRequest(Request  request) {
        if(request != null)
            requests.add(request);
    }

    @Override
    public void run() {
        while(gameRunning){
            currentState.run();
            for (Iterator<Request> requestIterator = requests.iterator(); requestIterator.hasNext();){
                Request request = requestIterator.next();
                if(request.isInternal()){
                    currentState.execute(request);
                }else{
                    execute(request);
                }
                requestIterator.remove();
            }
        }
    }

    public void execute (Request request) {
        String lower = request.getName().toLowerCase();

        if (lower.startsWith("switch")){
            currentState = stateMap.get(lower.substring(10));
        } else if(lower.startsWith("exit_all")){
            gameRunning = false;
        }
    }

    public void receiveData(StateData data){
        //now what? we'll give it to our panel
//        stateMap.
    }
}
