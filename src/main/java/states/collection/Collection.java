package states.collection;

import control.Request;
import states.State;

public class Collection extends State {


    private static State instance;

    public static State getInstance(){
        if(instance == null){
            instance = new Collection();
        }
        return instance;
    }

    @Override
    public void run() {
        running = true;
    }

    @Override
    public void execute(Request request) {

    }
}
