package ap.hearthstone.UI.control.mappers;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.requestTypes.SignUpRequestType;
import ap.hearthstone.UI.api.Mapper;
import ap.hearthstone.logic.users.PlayerFileManager;
import ap.hearthstone.logic.users.UserFactory;
import ap.hearthstone.logic.users.UsersFilesManager;

/* sign up */
public class SignUpMapper extends Mapper {

    @Override
    protected void doForRequest(Request request) {
            if (request.getTitle().equals("sign")) {
                signUp(request);
            }
    }

    private void signUp(Request request) {
        UsersFilesManager filesManager = new UsersFilesManager();
        String username = request.getRequestBody()[0];
        String password = request.getRequestBody()[1];
        if (filesManager.isUsernameTaken(username)) {
            responseSender.send(new Request("error", SignUpRequestType.USERNAME_TAKEN.getMessage()));
        } else {
            responseSender.send(new Request("successful", SignUpRequestType.SIGN_UP_SUCCESSFUL.getMessage()));
            newUser(username, password);
            requestSender.send(new Request("loadUser", username));
            requestSender.send(new Request("next"));
        }
    }

    //TODO: check logs' header.
    private void newUser(String username, String password) {
        new UserFactory().createUser(username, password);
        PlayerFileManager playerFileManager = new PlayerFileManager(username);
        playerFileManager.createPlayer();
        logger.info("A new User just created.");
    }

    protected void back() {
        super.back();
        responseSender.send(new Request("again"));
    }
}
