package states.logIn;

import control.Request;
import states.State;

public class SignUp extends State {

    private static State instance;

    public static State getInstance() {
        if(instance == null){
            instance = new SignUp();
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
