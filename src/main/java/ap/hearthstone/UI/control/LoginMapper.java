package ap.hearthstone.UI.control;

import ap.hearthstone.UI.api.requestTypes.LoginRequestType;
import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.SimpleMapper;
import ap.hearthstone.logic.users.NoUserFoundException;
import ap.hearthstone.logic.users.UsersFilesManager;

public class LoginMapper extends SimpleMapper {

    public LoginMapper() {
        super();
//        logger.debug("LoginMapper initialized.");
    }

    @Override
    protected void executeRequests() {
        while (requestList.size() > 0) {
            Request currentRequest = requestList.remove(0);
            switch (currentRequest.getTitle()) {
                case "login":
                    login(currentRequest);
                    break;
                case "sign up":
                    signUp();
                    break;
                case "exit": //this is meaningless in Login Window
                    exit();
                    break;
                default:
                    break;
            }
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

    private void exit() {
        requestSender.send(new Request("exit"));
    }

}
