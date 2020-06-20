package ap.hearthstone.states;

import ap.hearthstone.control.Admin;

public class Game  {


    private Admin admin;
    //States

    public Game(){
        admin = Admin.getInstance();
    }



    public void run() {
        admin.run();
    }


}
