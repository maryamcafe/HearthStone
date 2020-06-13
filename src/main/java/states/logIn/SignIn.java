package states.logIn;

import control.Request;
import states.State;

public class SignIn extends State {

    private static State instance;

    public static State getInstance() {
        if(instance == null){
            instance = new SignIn();
        }
        return instance;
    }
    @Override
    public void run() {

    }

    @Override
    public void execute(Request request) {

    }
}
