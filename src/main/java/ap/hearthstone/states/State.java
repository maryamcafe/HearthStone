package ap.hearthstone.states;


import ap.hearthstone.UI.api.Request;

public abstract class State {


     protected volatile Boolean running = false;

     public abstract void run();

     public abstract void execute(Request request);
}
