package states;

import control.Request;

public abstract class State {


     protected volatile Boolean running = false;

     public abstract void run();

     public abstract void execute(Request request);
}
