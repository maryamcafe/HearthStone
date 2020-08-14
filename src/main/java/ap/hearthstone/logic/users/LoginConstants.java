package ap.hearthstone.logic.users;

import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

public class LoginConstants {

    private Configs configs;
    /* file paths */
    private String activeUsersFile, idUsernameFile, lastIdFile;
    /* sign up constant */
    private Long initialID;
    /* Messages to print in consul */
    private String usernameTakenBeforeMsg, tryAgainMsg;
    /* singleton object */
    private static LoginConstants instance;

    private LoginConstants() {
        configs = ConfigLoader.getInstance().getLoginConstants();
    }

    public static LoginConstants getInstance() {
        if(instance == null){
            instance = new LoginConstants();
        }
        return instance;
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
        return configs.getProperty("usernameTakenBeforeMsg");
    }

    public String getTryAgainMsg() {
        return configs.getProperty("tryAgainMsg");
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
