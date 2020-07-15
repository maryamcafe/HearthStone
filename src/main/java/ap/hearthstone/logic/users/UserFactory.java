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
        return new User(username, password, id);
    }

    //setting a unique ID for every individual user

    private  long initId() {
        long id = filesManager.getLastUserId() + 1;
        filesManager.writeLastId(id);
        return id;
    }

    public void deleteUser(User user) {
        System.setProperty("deleted", LocalDateTime.now().toString());
        filesManager.removeUserFromFile(user.getUsername());
        logger = MainLogger.createLogger(user.getUsername(), user.getPassword(), user.getId(), "first", LocalDateTime.now().toString());
        logger.info("User {}: {} (username: id) was deleted.", user.getUsername(), user.getId());
    }
}
