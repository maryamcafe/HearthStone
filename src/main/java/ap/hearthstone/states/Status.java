package ap.hearthstone.states;

import ap.hearthstone.UI.api.Request;

public class Status extends State {

    private static State instance;

    public Status(){
    }

    @Override
    public void run() {
        running = true;
    }

    @Override
    public void execute(Request request) {

    }
    
}
