package ap.hearthstone.UI.control.mappers;

import ap.hearthstone.UI.api.requestTypes.LoginRequestType;
import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.Mapper;
import ap.hearthstone.logic.exceptions.NoUserFoundException;
import ap.hearthstone.logic.users.UsersFilesManager;

public class LoginMapper extends Mapper {

    public LoginMapper() {
        super();
//        logger.debug("LoginMapper initialized.");
    }

    @Override
    protected void doForRequest(Request request) {
        switch (request.getTitle()) {
            case "login":
                login(request);
                break;
            case "sign up":
                signUp();
                break;
            default:
                break;
        }
    }

    private void login(Request request) {
        //check if the password and username entered is right:)
        String username = request.getRequestBody()[0];
        String password = request.getRequestBody()[1];
        UsersFilesManager filesManager = new UsersFilesManager();
        try {
            if (filesManager.isPasswordCorrect(username.trim(), password.trim())) {
                responseSender.send(new Request("successful", LoginRequestType.LOGIN_SUCCESSFUL.getMessage()));
                requestSender.send(new Request("loadUser", username));
                requestSender.send(new Request("next"));
            } else {
                responseSender.send(new Request("error", LoginRequestType.PASSWORD_NOT_CORRECT.getMessage()));
            }
        } catch (NoUserFoundException e) {
            responseSender.send(new Request("error", LoginRequestType.USER_NOT_FOUND.getMessage()));
            logger.error(e.getMessage());
        }
    }

    private void signUp() {
        requestSender.send(new Request("switch", "sign"));
    }


}
