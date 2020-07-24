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

    private final String decksURL;
    private final Map<String, String> deckNameHeroMap;

    public DeckFileManager() {
        deckNameHeroMap = new HashMap<>();
        decksURL = ConfigLoader.getInstance().getDecksURL();
    }

    public Deck getDeck(String name) {
        try {
            FileReader reader = new FileReader(getFile(decksURL + name + ".json"));
            return new Gson().fromJson(reader, Deck.class);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
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
                FileReader reader = new FileReader(getFile(decksURL + "allDeckNames.json"));
                deckNameHeroMap.putAll(new Gson().fromJson(reader, mapType));
            } else if ("w".equals(mode)) {
                FileWriter writer = new FileWriter(getFile(decksURL + "allDeckNames.json"));
                gson.toJson(deckNameHeroMap, writer);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Create a deck and save it.
     */
    public void createDeck(String name, String heroName, List<String> cardNames) throws FullDeckException, MaxEachCardException {
        HeroClass heroClass = HeroClass.valueOf(heroName);
        Deck deck = new Deck(name, heroClass, cardNames);
        deckNameHeroMap.put(deck.getName(), deck.getHeroClass().name());
        updateDeckHeroMap("w");
        writeDeck(deck);
    }

    //TODO: test this type of writing.
    public void writeDeck(Deck deck) {
        try {
            FileWriter writer = new FileWriter(getFile(decksURL + deck.getName() + ".json"));
            new Gson().toJson(deck, writer);
            writer.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
