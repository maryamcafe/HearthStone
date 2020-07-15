package ap.hearthstone.logic.users;

import ap.hearthstone.model.user.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UsersFilesManager {
    private final String activeUsersFile;
    private final String lastIdFile;
    private final String idUsernameFile;
    private final long initialID;
    private Map<String, User> userMap;
    private Map<Long, String> idUsernameMap;
    private final Gson gson = new Gson();

    Logger logger = LogManager.getLogger(this.getClass());

    public UsersFilesManager() {
        LoginConstants constants = new LoginConstants();
        activeUsersFile = constants.getActiveUsersFile();
        lastIdFile = constants.getLastIdFile();
        idUsernameFile = constants.getIdUsernameFile();
        initialID = constants.getInitialID();
        //lazy evaluation is better.
        getFile(lastIdFile);
    }

    private Map<String, User> getUserMap() {
        if (userMap == null) {
            readUsersListFromFile();
        }
        return userMap;
    }

    /*
    Check correct login
     */
    public boolean isUsernameTaken(String key) {
        return getUserMap().containsKey(key);
    }

    public boolean isPasswordCorrect(String username, String password) throws NoUserFoundException {
        //if username doesn't exist, throw an exception:
        if (!getUserMap().containsKey(username)) {
            throw new NoUserFoundException(username);
        }
        return getUserMap().get(username).getPassword().equals(password);
    }


    public void addUserToFile(User user) {
        getUserMap().put(user.getUsername(), user);
        String usersJson = gson.toJson(getUserMap());
        writeToUsersFile(usersJson);
    }

    public void removeUserFromFile(String userName) {
        getUserMap().remove(userName);
    }

    /* getting last ID assigned to a user.
     */
    public long getLastUserId() {
        try {
            return readLastId();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return initialID;
        }
    }

    private long readLastId() throws IOException {
        RandomAccessFile file = new RandomAccessFile(getFile(lastIdFile), "r");
        long lastId;
        if (file.length() == 0) {
            lastId = initialID;
        } else {
            lastId = file.readLong();
        }
        file.close();
        return lastId;
    }

    public void writeLastId(long lastId) {
        try {
            RandomAccessFile file = new RandomAccessFile(getFile(lastIdFile), "rw");
            file.writeLong(lastId);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Searching tool*/
    public String usernameById(long id) {
        return idUsernameMap.get(id);
    }

    /*Searching tool*/
    private Optional<Long> getID(String username) {
        return idUsernameMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(username))
                .map(Map.Entry::getKey).findAny();
    }

    private void readUsersListFromFile() {
        try {
            Reader reader = new FileReader(getFile(activeUsersFile));
            Type type = new TypeToken<HashMap<String, User>>() {
            }.getType();
            if (userMap == null) {
                userMap = new HashMap<>();
            }
            userMap = gson.fromJson(reader, type);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToUsersFile(String toWrite) {
        try {
            Writer writer = new FileWriter(getFile(activeUsersFile));
            writer.write(toWrite);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        readUsersListFromFile();
    }

    private File getFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            Path path = Paths.get(filePath);
            try {
                Files.createFile(path);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return file;
    }
}
