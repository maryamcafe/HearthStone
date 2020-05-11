package util;

import com.google.gson.Gson;
import users.Player;
import users.User;

import java.io.*;


public class PlayerFileManager {

    private static Gson gson = new Gson();
    private static Reader reader;
    private static Player player;


    public static Player playerInitializer(User user) {
        try {
            if (isTherePlayerFile(user.getUsername())) {
                //Old Player
                Reader reader = new FileReader(new File(user.getUsername() + ".json"));
                player = gson.fromJson(reader, Player.class);
                reader.close();

            } else {
                //new Player
                player = new Player(user);
                writePlayerToFile(player);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return player;
    }

    private static void writePlayerToFile(Player player) throws IOException {
        String playerJson = gson.toJson(player);
        //we'll save the player in a file with it's name = the player's username
        Writer writer = new FileWriter(player.getUser().getUsername() + "json");
        writer.write(playerJson);
        writer.close();
    }


    private static boolean isTherePlayerFile(String username) {
        try {
            Reader reader = new FileReader(new File(username + ".json"));
            reader.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
