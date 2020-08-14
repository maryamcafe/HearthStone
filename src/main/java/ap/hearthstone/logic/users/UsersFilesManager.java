package ap.hearthstone.logic.users;

import ap.hearthstone.logic.exceptions.NoUserFoundException;
import ap.hearthstone.model.user.User;
import ap.hearthstone.utils.FileManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UsersFilesManager extends FileManager {
    private final String activeUsersFile;
    private final String lastIdFile;
    private final String idUsernameFile;
    private final long initialID;
    private Map<String, User> userMap;
    private Map<Long, String> idUsernameMap;

    public UsersFilesManager() {
        LoginConstants constants = LoginConstants.getInstance();
        activeUsersFile = constants.getActiveUsersFile();
        lastIdFile = constants.getLastIdFile();
        idUsernameFile = constants.getIdUsernameFile();
        initialID = constants.getInitialID();
        userMap = new HashMap<>();
        //lazy evaluation is better.
        getFile(lastIdFile);
    }

    private Map<String, User> getUserMap() {
        if (userMap.size() == 0) {
            updateUsers("r");
        }
        return userMap;
    }

    /*    Check correct login */
    public boolean isUsernameTaken(String key) {
        return getUserMap().containsKey(key);
    }

    public boolean isPasswordCorrect(String username, String password) throws NoUserFoundException {
        if (!getUserMap().containsKey(username)) {  //if username doesn't exist, throw an exception:
            throw new NoUserFoundException(username);
        }
        return getUserMap().get(username).getPassword().equals(password);
    }


    public void addUserToFile(User user) {
        getUserMap().put(user.getUsername(), user);
        updateUsers("w");
    }

    public void removeUserFromFile(String userName) {
        getUserMap().remove(userName);
        updateUsers("w");
    }

    /* getting last ID assigned to a user.
     */
    public long getLastUserId() {
        try {
            RandomAccessFile file = new RandomAccessFile(getFile(lastIdFile), "r");
            long lastId;
            if (file.length() == 0) {
                lastId = initialID;
            } else {
                lastId = file.readLong();
            }
            file.close();
            return lastId;
        } catch (IOException e) {
            e.printStackTrace();
            return initialID;
        }
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
    public Long getID(String username) throws Exception {
        Optional<Long> id = idUsernameMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(username))
                .map(Map.Entry::getKey).findAny();
        if(id.isPresent()){
            return id.get();
        }
        else throw new Exception("can not find id for this username");
    }

    /* this method should be package private     */
    String getPassword(String username){
        return getUserMap().get(username).getPassword();
    }

    private void updateUsers(String mode) {
        try {
            Gson gson = new Gson();
            if ("r".equals(mode)) {
                Reader reader = new FileReader(getFile(activeUsersFile));
                Type type = new TypeToken<HashMap<String, User>>() {
                }.getType();
                userMap = gson.fromJson(reader, type);
                reader.close();
            } else if ("w".equals(mode)) {
                Writer writer = new FileWriter(getFile(activeUsersFile));
                writer.write(gson.toJson(userMap));
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
