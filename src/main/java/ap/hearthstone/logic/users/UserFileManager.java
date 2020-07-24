package ap.hearthstone.logic.users;

import ap.hearthstone.logic.exceptions.NoUserFoundException;
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

public class UserFileManager {

    private final static String usersListFile = "src/main/resources/users/ActiveUsers.json";
    private final static String lastIdFile = "src/main/resources/users/lastId.dat";
    private final String idUserNameFile = "src/main/resources/users/idUserName.json";
    private Reader activeUsersReader;
    private final int initialID = 1_000_000;
    private Map<String, User> userMap;
    private Map<Long, String> idUsernameMap;
    private final Gson gson = new Gson();
    private Type userMapType, idUserMap ;

    Logger logger = LogManager.getLogger(this.getClass());

    public UserFileManager() {
    }

    private void initFiles() { // this method should be called if needed.
        Path usersListPath = Paths.get(usersListFile);
        Path lastIdPath = Paths.get(lastIdFile);
        try {
            Files.createFile(usersListPath);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        try {
            Files.createFile(lastIdPath);
            writeLastId(initialID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readUsersListFromFile() {
        try {
            if(userMap == null){
                userMap = new HashMap<>();
            }
            userMap = gson.fromJson(getActiveUsersReader(), getUserMapType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Type getUserMapType() {
        if(userMapType == null){
            userMapType = new TypeToken<HashMap<String, User>>() {
            }.getType();
        }
        return userMapType;
    }

    private Reader getActiveUsersReader() throws FileNotFoundException {
        if(activeUsersReader == null){
            initFiles();
            activeUsersReader = new FileReader(new File(usersListFile));
        }
        return activeUsersReader;
    }


    public boolean isUsernameTaken(String key) {
        return getUserMap().containsKey(key);
    }

    public User findUser(String username) {
        return getUserMap().get(username);
    }

    public boolean isPasswordCorrect(String username, String password) throws NoUserFoundException {
        //if username doesn't exist, throw an exception:
        if (!getUserMap().containsKey(username)) {
            throw new NoUserFoundException(username);
        }
        return userMap.get(username).getPassword().equals(password);
    }

    public static long readLastUserId() throws IOException {
        RandomAccessFile file = new RandomAccessFile(lastIdFile, "r");
        long lastId = file.readLong();
        file.close();
        return lastId;
    }

    public static void writeLastId(long lastId){
        try {
            RandomAccessFile file = new RandomAccessFile(lastIdFile, "rw");
            file.writeLong(lastId);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToUsersFile(String toWrite) throws IOException {
        //this code works. but it's not the best code,
        Writer writer = new FileWriter(usersListFile);
        writer.write(toWrite);
        writer.close();
        readUsersListFromFile();
    }

    private Map<String, User> getUserMap() {
        if (userMap == null){
            readUsersListFromFile();
        }
        return userMap;
    }

    public void addUserToFile(User user) {
        //read the file and convert it to linkedList object:
        try {
            //Add the last user:
            userMap.put(user.getUsername(), user);
            //write the list back to the file
            String usersJson = gson.toJson(userMap);
            writeToUsersFile(usersJson);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserFromFile(String userName){
        userMap.remove(userName);

    }

    public User searchById(long id){
        return userMap.get(idUsernameMap.get(id));
    }
}
