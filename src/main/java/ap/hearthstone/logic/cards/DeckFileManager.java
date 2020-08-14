package ap.hearthstone.logic.cards;

import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.deck.Deck;
import ap.hearthstone.model.gameModels.exceptions.FullDeckException;
import ap.hearthstone.model.gameModels.exceptions.MaxEachCardException;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.FileManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckFileManager extends FileManager {

    private final String publicDecksURL;
    private final String privateDecksURL;
    private final Map<String, String> deckNameHeroMap;

    public DeckFileManager(String username) {
        deckNameHeroMap = new HashMap<>();
        publicDecksURL = ConfigLoader.getInstance().getDecksURL();
        privateDecksURL = ConfigLoader.getInstance().getUsersURL() + username + "/";
    }

    // TODO check if this method needs to be static
    public Deck getPublicDeck(String deckName) {
        return readDeck(publicDecksURL + deckName + "Deck.json");
    }

    public Deck getDeck(String deckName) {
        return readDeck(privateDecksURL + deckName + "Deck.json");
    }

    /* Create a deck and save it. */
    public Deck createDeck(String name, String heroName, List<String> cardNames) throws FullDeckException, MaxEachCardException {
        HeroClass heroClass = HeroClass.valueOf(heroName);
        Deck deck = new Deck(name, heroClass, cardNames);
        deckNameHeroMap.put(deck.getName(), deck.getHeroClass().name());
        updateDeckHeroMap("w");
        writeDeck(deck);
        return deck;
    }

    //TODO: add a part to edit the deck.
    /* This map is used to only show decks in collection.*/
    public Map<String, String> getDeckNameHeroMap() {
        if (deckNameHeroMap.size() == 0) {
            updateDeckHeroMap("r");
        }
        return new HashMap<>(deckNameHeroMap);
    }

    private void updateDeckHeroMap(String mode) {
        try {
            Type mapType = new TypeToken<Map<String, String>>() {
            }.getType();
            Gson gson = new Gson();
            if ("r".equals(mode)) {
                FileReader reader = new FileReader(getFile(privateDecksURL + "allDeckNames.json"));
                deckNameHeroMap.putAll(new Gson().fromJson(reader, mapType));
            } else if ("w".equals(mode)) {
                FileWriter writer = new FileWriter(getFile(privateDecksURL + "allDeckNames.json"));
                gson.toJson(deckNameHeroMap, writer);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Deck readDeck(String path) {
        try {
            FileReader reader = new FileReader(getFile(path));
            return new Gson().fromJson(reader, Deck.class);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    //TODO: test this type of writing.
    public void writeDeck(Deck deck) {
        try {
            FileWriter writer = new FileWriter(getFile(privateDecksURL + deck.getName() + "Deck.json"));
            new Gson().toJson(deck, writer);
            writer.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
