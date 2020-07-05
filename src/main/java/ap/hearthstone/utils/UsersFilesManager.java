package ap.hearthstone.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ap.hearthstone.users.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;


public class UsersFilesManager {
    final static String UsersListFile = "src/main/resources/ActiveUsers.json";
    static final String wrongUsernameMsg = "Username is not right";
    private static HashMap<String, User> usersList;
    private static Gson gson = new Gson();

    static {
        try {
            update();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update() throws IOException {
        Reader reader = new FileReader(new File(UsersListFile));
        Type type = new TypeToken<HashMap<String, User>>() {
        }.getType();
        usersList = gson.fromJson(reader, type);
        reader.close();

    }

    public static void addUserToFile(User user) {
        //read the file and convert it to linkedList object:
        try {
            //Add the last user:
            usersList.put(user.getUsername(), user);
            //write the list back to the file
            String usersJson = gson.toJson(usersList);
            update(usersJson);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void update(String toWrite) throws IOException {
        //this code works. but it's not the best code,
        Writer writer = new FileWriter(UsersListFile);
        writer.write(toWrite);
        writer.close();
        update();
    }

    public static boolean isUsernameTaken(String key) throws IOException {
        //if the username is taken before, return true
        return usersList.containsKey(key);
    }

    public static User findUser(String username) {
        return usersList.get(username);
    }

    public static boolean isPasswordCorrect(String username, String password) throws Exception {
        //if username doesn't exist, throw an exception:
        if (!usersList.containsKey(username)) {
            throw new Exception(wrongUsernameMsg);
        }
        return usersList.get(username).getPassword().equals(password);
    }




}
