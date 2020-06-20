package ap.hearthstone.states.shop;

import ap.hearthstone.control.Request;
import ap.hearthstone.states.State;
import ap.hearthstone.states.collection.Collection;

public class Shop extends State {
    private static State instance;

    private Shop(){
    }

    @Override
    public void run() {
        running = true;
    }

    @Override
    public void execute(Request request) {

    }

    public static State getInstance(){
        if(instance == null){
            instance = new Collection();
        }
        return instance;
    }
}
