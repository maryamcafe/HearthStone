package ap.hearthstone.logic.users;

import ap.hearthstone.logic.cards.CardFileManager;
import ap.hearthstone.logic.cards.DeckManager;
import ap.hearthstone.model.gameModels.deck.Deck;
import ap.hearthstone.model.gameModels.exceptions.FullDeckException;
import ap.hearthstone.model.gameModels.exceptions.IllegalHeroClass;
import ap.hearthstone.model.gameModels.exceptions.MaxEachCardException;
import ap.hearthstone.model.gameModels.util.GameConstants;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.FileManager;
import com.google.gson.Gson;
import ap.hearthstone.model.user.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;


public class PlayerManager extends FileManager {

    private Player player;
    private final String username;
    private final String userDir, userFileName;
    private List<String> playerCards;  //TODO change to a proper map:)
    private Map<String, Integer> cardsNumberMap; // how many of each card player does have
    private final DeckManager deckManager;
    private final CardFileManager cardFileManager;

    Logger logger = LogManager.getLogger(this.getClass());

    public PlayerManager(String username) {
        this.username = username;
        userDir = ConfigLoader.getInstance().getUsersURL() + username + "/";
        userFileName = username + ".json";
        deckManager = new DeckManager(username);
        cardFileManager = CardFileManager.getInstance();

    }

    public Player getPlayer() {
        if (player == null) {
            if (fileExists(userDir + userFileName)) {
                readPlayerFromFile();
            } else {
                createPlayer();
            }
        }
        return player;
    }

    private void readPlayerFromFile() {
        try {
            FileReader reader = new FileReader(getFile(userDir + userFileName));
            player = new Gson().fromJson(reader, Player.class);
            player.getDecks().forEach(deckManager::update);

            reader.close();
            logger.debug("Read player: {} from file", player.toString());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void createPlayer() {
        GameConstants constants = GameConstants.getInstance();
        try {
            player = new Player(username, cardFileManager.geDefaultCards(),
                    new HashSet<>(Collections.singletonList(deckManager.getDefaultDeck())),
                    deckManager.getDefaultDeck(),
                    constants.getDefaultCoins(),
                    new HashSet<>(Collections.singletonList(constants.getDefaultHero())));
            deckManager.createDeck(player.getDefaultDeck());
            writePlayerToFile();
        } catch (MaxEachCardException | IllegalHeroClass | FullDeckException | FileNotFoundException e) {
            e.printStackTrace();
        }
        logger.debug("Created player: {} from file", player.toString());
    }

    /* Save the player in a file with name equal to the player's username*/
    private void writePlayerToFile() {
        String playerJson = new Gson().toJson(player);
        try {
            Writer writer = new FileWriter(getFile(userDir, userFileName));
            writer.write(playerJson);
            writer.close();
            logger.debug("player is written on the file successfully");
        } catch (IOException e) {
            logger.error("Could not write player to file.", e);
        }
    }

    public void deletePlayer() {
        //TODO delete the whole directory of this player.
    }

    public List<String> getPlayerCards() {
        playerCards = getPlayer().getCards();
        return new LinkedList<>(playerCards);
    }

    public Map<String, Integer> getPlayerCardsNumber() {
        cardsNumberMap = new HashMap<>();
        cardFileManager.getAllCardsMap().forEach((s, card) -> cardsNumberMap.put(s, count(s, getPlayerCards())));
        return cardsNumberMap;
    }

    private int count(String card, List<String> cards) {
        return cards.stream().filter(c -> c.equals(card)).mapToInt(c -> 1).sum();
    }

    public DeckManager getDeckManager() {
        return deckManager;
    }
}
