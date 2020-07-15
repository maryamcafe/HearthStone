package ap.hearthstone.UI.control;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.requestTypes.SignUpRequestType;
import ap.hearthstone.UI.api.SimpleMapper;
import ap.hearthstone.model.user.User;
import ap.hearthstone.logic.users.UserFactory;
import ap.hearthstone.logic.users.UsersFilesManager;


public class SignUpMapper extends SimpleMapper {

    private UsersFilesManager filesManager = new UsersFilesManager();
    private UserFactory userFactory = new UserFactory();

    //SignUp
    @Override
    protected void executeRequests() {
        while (requestList.size() > 0) {
            Request request = requestList.remove(0);
            if (request.getTitle().equals("sign")) {
                signUp(request);
            } else if (request.getTitle().equals("back")) {
                back();
            }
        }
    }


    private void signUp(Request request) {
        String username = request.getRequestBody()[0];
        String password = request.getRequestBody()[1];
        if (filesManager.isUsernameTaken(username)) {
            responseSender.send(new Request("error", SignUpRequestType.USERNAME_TAKEN.getMessage()));
        } else {
            responseSender.send(new Request("successful", SignUpRequestType.SIGN_UP_SUCCESSFUL.getMessage()));
            newUser(username, password);
            requestSender.send(new Request("next"));
        }
    }

    //    To do: add the user id to logger header.
    //    logging configuration should update frequently to capture this.

    private void newUser(String username, String password) {
        User user = userFactory.createUser(username, password);
        filesManager.addUserToFile(user);
        logger.info("A new User just created.");
    }

    private void back() {
        requestSender.send(new Request("back"));
        responseSender.send(new Request("again"));
    }
}
