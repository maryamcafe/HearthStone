package ap.hearthstone.logic.users;

import com.google.gson.Gson;
import ap.hearthstone.model.user.PlayerStatus;
import ap.hearthstone.model.user.User;

import java.io.*;


public class PlayerFileManager {

    private static Gson gson = new Gson();
    private static PlayerStatus playerStatus;
    private static Reader playerFileReader;


    public static PlayerStatus playerInitializer(User user) {
        try {
            if (isTherePlayerFile(user.getUsername())) { //Old Player
                playerStatus = gson.fromJson(playerFileReader, PlayerStatus.class);
                playerFileReader.close();
            } else { //new Player
                playerStatus = new PlayerStatus(user);
                writePlayerToFile(playerStatus);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerStatus;
    }

    private static void writePlayerToFile(PlayerStatus playerStatus) throws IOException {
        String playerJson = gson.toJson(playerStatus);
        //we'll save the player in a file with it's name = the player's username
        Writer writer = new FileWriter(playerStatus.getUser().getUsername() + "json");
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
