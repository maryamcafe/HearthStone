package ap.hearthstone.UI.api;

import ap.hearthstone.utils.LogInConstants;

public enum LoginRequestType implements RequestType {
    LOGIN_SUCCESSFUL(LogInConstants.loginSuccessful),
    PASSWORD_NOT_CORRECT(LogInConstants.passwordNotCorrect),
    USER_NOT_FOUND(LogInConstants.userNotFound),
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
