package ap.hearthstone.UI.api.requestTypes;

import ap.hearthstone.interfaces.RequestType;
import ap.hearthstone.logic.users.LoginConstants;

public enum LoginRequestType implements RequestType {
    LOGIN_SUCCESSFUL(LoginConstants.getInstance().getLoginSuccessful()),
    PASSWORD_NOT_CORRECT(LoginConstants.getInstance().getPasswordNotCorrect()),
    USER_NOT_FOUND(LoginConstants.getInstance().getUserNotFound()),
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
