package ap.hearthstone.logic.users;

import ap.hearthstone.logging.MainLogger;
import ap.hearthstone.model.user.User;

import java.time.LocalDateTime;

public class UserFactory {

    private UsersFilesManager filesManager;
    private MainLogger logger;
    public UserFactory(){
        filesManager = new UsersFilesManager();
    }

    public User createUser(String username, String password) {
        long id = initId();
        logger = MainLogger.createLogger(username, password, id, LocalDateTime.now().toString(), null);
        logger.info("start!");
        User user = new User(username, password, id);
        filesManager.addUserToFile(user);
        return user;
    }

    //setting a unique ID for every individual user

    private  long initId() {
        long id = filesManager.getLastUserId() + 1;
        filesManager.writeLastId(id);
        return id;
    }

    public void deleteUser(String username) {
        System.setProperty("deleted", LocalDateTime.now().toString());
        filesManager.removeUserFromFile(username);
        try {
            logger = MainLogger.createLogger(username, filesManager.getPassword(username), filesManager.getID(username), "first", LocalDateTime.now().toString());
            logger.info("User {}: {} (username: id) was deleted.",username, filesManager.getID(username));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
