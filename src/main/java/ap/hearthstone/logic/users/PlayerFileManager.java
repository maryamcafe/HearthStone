package ap.hearthstone.logic.users;

import com.google.gson.Gson;
import ap.hearthstone.model.user.Player;
import ap.hearthstone.model.user.User;

import java.io.*;


public class PlayerFileManager {

    private static Gson gson = new Gson();
    private static Player player;
    private static Reader playerFileReader;


    public static Player playerInitializer(User user) {
        try {
            if (isTherePlayerFile(user.getUsername())) { //Old Player
                player = gson.fromJson(playerFileReader, Player.class);
                playerFileReader.close();
            } else { //new Player
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
            playerFileReader = new FileReader(new File(username + ".json"));
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

}
