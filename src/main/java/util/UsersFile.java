package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import users.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class UsersFile {

    private static Map<String, User> usersList;
    private static Gson gson = new Gson();
    final static String UsersListFile = "ActiveUsers.json";
    static final String wrongUsernameMsg = "Username is not right";


    //Reads users' information json file and updates the usersList map
    public UsersFile() throws IOException {
        update();
    }

    public void update() throws IOException {
        Reader reader = new FileReader(new File(UsersListFile));
        Type type = new TypeToken<HashMap<String, User>>() {
        }.getType();
        usersList = gson.fromJson(reader, type);
        reader.close();
    }

    public void addUserToFile(User user) {
        //read the file and convert it to linkedList object:
        try {
            //Add the last user:
            usersList.put(user.getUsername(),user);
            //write the list back to the file
            String users = gson.toJson(usersList);
            writeUpdate(users);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeUpdate(String toWrite) throws IOException {
        //this code works. but it's not the best code,
        Writer writer = new FileWriter(UsersListFile);
        writer.write(toWrite);
        writer.close();
        update();
    }


    public boolean isUsernameTaken(String key) {
        //if the username is taken before, return true
        return usersList.containsKey(key);
    }

    public User findUser(String username) {
        return usersList.get(username);
    }

    public boolean isPasswordCorrect(String username, String password) throws Exception {
        //if username doesn't exist, throw an exception:
        if (!usersList.containsKey(username)) {
            throw new Exception(wrongUsernameMsg);
        }
        return usersList.get(username).getPassword().equals(password);
    }


}
