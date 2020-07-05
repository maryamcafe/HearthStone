package ap.hearthstone.UI.control;

import ap.hearthstone.UI.api.GeneralRequestType;
import ap.hearthstone.UI.api.LoginRequestType;
import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.SimpleMapper;
import ap.hearthstone.UI.menuView.data.LoginData;
import ap.hearthstone.interfaces.RequestSender;
import ap.hearthstone.utils.LogInConstants;
import ap.hearthstone.utils.UsersFilesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginMapper extends SimpleMapper {

    Logger logger = LogManager.getLogger(this.getClass());

    public LoginMapper() {
        super();
        logger.debug("LoginMapper initialized.");
//        this.setName("LoginMapper");
    }

    @Override
    protected void executeRequests() {
        Request currentRequest = requestList.remove(0);
        switch (currentRequest.getTitle()) {
            case "login":
                login(currentRequest);
                break;
            case "sign up":
                signUp();
                break;
            case "exit":
                exitAll();
                break;
            default:
                break;
        }
    }

    private void login(Request request) {
        //check if the password and username entered is right:)
        LoginData loginData = getLoginData(request.getRequestBody());
        String username = loginData.getUsername();
        String password = loginData.getPassword();
        try {
            if (UsersFilesManager.isPasswordCorrect(username.trim(), password.trim())) {
                logger.info(LogInConstants.loginSuccessful);
                requestSender.send(new Request(LoginRequestType.LOGIN_SUCCESSFUL));
            } else {
                logger.warn(LogInConstants.passwordNotCorrect);
                requestSender.send(new Request(LoginRequestType.PASSWORD_NOT_CORRECT));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            requestSender.send(new Request(LoginRequestType.USER_NOT_FOUND));
        }
    }

    private void signUp() {
        requestSender.send(new Request(LoginRequestType.SIGN_UP));
    }

    private void exitAll() {
        requestSender.send(new Request(GeneralRequestType.EXIT_ALL));
    }

    // custom decoding from String array
    //try to use Optional!
    public LoginData getLoginData(String[] requestBody) {
        return new LoginData(requestBody[1], requestBody[2]);
    }
}
