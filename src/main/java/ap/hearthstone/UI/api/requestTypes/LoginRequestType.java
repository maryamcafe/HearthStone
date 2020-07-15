package ap.hearthstone.UI.api.requestTypes;

import ap.hearthstone.interfaces.RequestType;
import ap.hearthstone.logic.users.LoginConstants;

public enum LoginRequestType implements RequestType {
    LOGIN_SUCCESSFUL(new LoginConstants().getLoginSuccessful()),
    PASSWORD_NOT_CORRECT(new LoginConstants().getPasswordNotCorrect()),
    USER_NOT_FOUND(new LoginConstants().getUserNotFound()),
    SIGN_UP;

    private String message;

    LoginRequestType(String message) {
        this.message = message;
    }
    LoginRequestType(){ }

    public String getMessage() {
        return message;
    }
}
