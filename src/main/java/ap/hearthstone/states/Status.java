package ap.hearthstone.states;

import ap.hearthstone.control.Request;
import ap.hearthstone.states.collection.Collection;

public class Status extends State {

    private static State instance;

    private Status(){
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
