package ap.hearthstone.logic.users;

import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.FileManager;
import com.google.gson.Gson;
import ap.hearthstone.model.user.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;


public class PlayerFileManager extends FileManager {

    private Player player;
    private final String username;
    private final String userPath;

    Logger logger = LogManager.getLogger(this.getClass());

    public PlayerFileManager(String username) {
        this.username = username;
        userPath = ConfigLoader.getInstance().getUsersURL();
    }

    public Player getPlayer() {
        readPlayerFromFile();
        return player;
    }

    public void createPlayer() {
        player = new Player(username);
        writePlayerToFile(player);
    }

    private void readPlayerFromFile() {
        try {
            FileReader reader = new FileReader(getFile(userPath + username + ".json"));
            player = new Gson().fromJson(reader, Player.class);
            reader.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /* Save the player in a file with name equal to the player's username*/
    private void writePlayerToFile(Player player) {
        String playerJson = new Gson().toJson(player);
        try {
            Writer writer = new FileWriter(getFile(userPath + username + "json"));
            writer.write(playerJson);
            writer.close();
        } catch (IOException e) {
            logger.error("Could not write player to file.", e);
        }
    }

    public void deletePlayer() {
        //TODO delete the whole directory of this player.
    }
}
