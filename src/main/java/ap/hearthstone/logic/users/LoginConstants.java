package ap.hearthstone.logic.users;

import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

public class LoginConstants {

    private Configs configs;
    //FILE Names:
    private String activeUsersFile, idUsernameFile, lastIdFile;
    //sign up constant:
    private Long initialID;
    //Messages to print in consul
    private String usernameTakenBeforeMsg, tryAgainMsg;
    private String wrongInput, passwordNotCorrect, userNotFound;
    private String signUpSuccessful, loginSuccessful;
    private String LoggedInMenu;


    public LoginConstants() {
        configs = ConfigLoader.getInstance().getLoginConstants();
    }

    public String getActiveUsersFile() {
        if (activeUsersFile == null) {
            activeUsersFile = configs.getProperty("ACTIVE_USERS_FILE");
        }
        return activeUsersFile;
    }

    public String getIdUsernameFile() {
        if (idUsernameFile == null) {
           idUsernameFile = configs.getProperty("ID_USERNAME_FILE");
        }
        return idUsernameFile;
    }

    public String getLastIdFile() {
        if (lastIdFile == null) {
           lastIdFile = configs.getProperty("LAST_ID_FILE");
        }
        return lastIdFile;
    }

    public long getInitialID() {
        if (initialID == null) {
            initialID = configs.readLong("initialID");
        }
        return initialID;
    }

    public String getUsernameTakenBeforeMsg() {
        if (usernameTakenBeforeMsg == null) {
            usernameTakenBeforeMsg = configs.getProperty("usernameTakenBeforeMsg");
        }
        return usernameTakenBeforeMsg;
    }

    public String getTryAgainMsg() {
        if (tryAgainMsg == null) {
            tryAgainMsg = configs.getProperty("tryAgainMsg");
        }
        return tryAgainMsg;
    }

    public String getWrongInput() {
        return configs.getProperty("wrongInput");
    }

    public String getPasswordNotCorrect() {
        return configs.getProperty("passwordNotCorrect");
    }

    public String getUserNotFound() {
        return configs.getProperty("userNotFound");
    }

    public String getSignUpSuccessful() {
        return configs.getProperty("signUpSuccessful");
    }

    public String getLoginSuccessful() {
        return configs.getProperty("loginSuccessful");
    }

    public String getLoggedInMenu() {
        return configs.getProperty("LoggedInMenu");
    }
}
