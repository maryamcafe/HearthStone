package ap.hearthstone.control;



import ap.hearthstone.interfaces.MyExecutable;

import java.util.Iterator;
import java.util.LinkedList;

public class GameAction {

    private static GameAction instance = new GameAction();

    private LinkedList<Request> requests;


    private GameAction(){
        requests = new LinkedList<>();
    }


    public static GameAction getInstance() {
        return instance;
    }

    public void addRequest(Request request) {
        if(request != null)
            requests.add(request);
    }

    public void executeRequests() {
        for (Iterator<Request> requestIterator = requests.iterator(); requestIterator.hasNext();){
            Request request = requestIterator.next();
            request.execute();
            requestIterator.remove();
        }
    }

    private static void moveRight() {

    }

    private static void moveLeft(){

    }

    private static void tobe(){
    }


    private enum Request implements MyExecutable {

        BLOCK_MOVE_RIGHT{
            @Override
            public void execute(){
                ;
            }
        }, BLOCK_MOVE_LEFT {
            @Override
            public void execute(){
                ;
            }
        }, TOBEH {
            @Override
            public void execute(){
                ;
            }
        }
    }
}
