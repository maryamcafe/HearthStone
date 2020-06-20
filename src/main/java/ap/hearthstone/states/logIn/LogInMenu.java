package ap.hearthstone.states.logIn;

import ap.hearthstone.control.Admin;
import ap.hearthstone.control.Request;
import ap.hearthstone.mvvm.LoginData;
import ap.hearthstone.states.State;
import ap.hearthstone.users.User;
import ap.hearthstone.util.LogInConstants;
import ap.hearthstone.util.UsersFilesManager;

public class LogInMenu extends State {

    private Boolean running = false;
    private static State instance;

    private String username, password;
    private User user;
    private boolean isSuccessful;
    private LoginData.Message message;


    public static State getInstance() {
        if (instance == null) {
            instance = new LogInMenu();
        }
        return instance;
    }

    private LogInMenu() {

    }

    @Override
    public void run() {
        running = true;
    }

    @Override
    public void execute(Request request) {
        String lower = request.getName().toLowerCase();
        if (lower.contains("signup")) {
            Admin.getInstance().addRequest(new Request("SWITCH_TO_SIGN_UP", false, null));
        } else {
            parseInformation(request.getInformation());
            login();
        }
    }

    private void parseInformation(String[] information) {
        for (int i = 0; i < information.length; i++) {// better to be written with an iterator
            String info = information[i];
            if (info.equals("username")) {
                username = information[++i];
            } else if (info.equals("password")) {
                password = information[++i];
            }
        }
    }

    private void login() {
        //check if the password and username entered is right:)
        try {
            if (!UsersFilesManager.isPasswordCorrect(username.trim(), password.trim())) {
                System.out.println(LogInConstants.wrongPassword);
                sendData(new LoginData(false, LoginData.Message.INCORRECT_PASSWORD));
            } else {
                System.out.println(LogInConstants.signInSuccessful);
                sendData(new LoginData(true, LoginData.Message.SUCCESSFUL));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sendData(new LoginData(false, LoginData.Message.USER_NOT_FOUND));
            e.printStackTrace();
        }
    }

    private void sendData(LoginData loginData) {
        //send it somehow for the server and then for the panel.
        // what do we do with other data? we use it to log. (if successful) so it shouls some how be sent to
        Admin.getInstance().receiveData(loginData);
    }


}
