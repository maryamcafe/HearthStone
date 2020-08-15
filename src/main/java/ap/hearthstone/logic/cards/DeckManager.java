package ap.hearthstone.logic.cards;

import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.deck.Deck;
import ap.hearthstone.model.gameModels.exceptions.FullDeckException;
import ap.hearthstone.model.gameModels.exceptions.IllegalHeroClass;
import ap.hearthstone.model.gameModels.exceptions.MaxEachCardException;
import ap.hearthstone.model.gameModels.util.GameConstants;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.FileManager;
import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckManager extends FileManager {

    private final String publicDecksURL;
    private final String privateDecksURL;
    private final Map<String, String> deckToHeroMap;
    private final Map<String, Deck> allDecksMap;
    private Deck defaultDeck;

    public DeckManager(String username) {
        deckToHeroMap = new HashMap<>();
        allDecksMap = new HashMap<>();
        publicDecksURL = ConfigLoader.getInstance().getDecksURL();
        privateDecksURL = ConfigLoader.getInstance().getUsersURL() + username + "/";
    }

    //Getters
    public Deck getPublicDeck(String deckName) {// TODO check if this method needs to be static
        return readDeck(publicDecksURL + deckName + ".json");
    }

    public Deck getDefaultDeck(){
        if(defaultDeck == null){
            defaultDeck = getPublicDeck(GameConstants.getInstance().getDefaultDeck());
        }
        return defaultDeck;
    }

    public Deck getDeck(String deckName) {
        return readDeck(privateDecksURL + deckName + ".json");
    }

    /* Should be called after getPlayer() is called from corresponding PlayerFileManager*/
    public Map<String, String> getDeckToHeroMap() {
        if(deckToHeroMap.size() == 0){
            loadDeckToHeroMap();
        }
        return new HashMap<>(deckToHeroMap);
    }

    public Map<String, Deck> getAllDecksMap() {
        if(allDecksMap.size() == 0){
            loadAllDecksMap();
        }
        return allDecksMap;
    }

    /* Create a deck and save it. */
    public Deck createDeck(String name, String heroName, List<String> cardNames) throws FullDeckException, MaxEachCardException, IllegalHeroClass {
        logger.debug("in CREATE DECK, hero name is:{}",heroName);
        Deck deck = new Deck(name, HeroClass.valueOf(heroName), cardNames);
        update(deck);
        writeDeck(deck);
        return deck;
    }

    public void createDeck(Deck deck) throws MaxEachCardException, IllegalHeroClass, FullDeckException {
        createDeck(deck.getName(), deck.getHeroClass().name(),deck.getCards());
    }

    public void update(Deck deck){
        deckToHeroMap.put(deck.getName(), deck.getHeroClass().name());
    }

    // EDIT DECK
    public void addCardToDeck(String card, String deckName) throws MaxEachCardException, IllegalHeroClass, FullDeckException {
        Deck deck = getDeck(deckName);
        deck.add(card);
        writeDeck(deck);
    }

    public void removeCardFromDeck(String card, String deckName) throws MaxEachCardException, IllegalHeroClass, FullDeckException {
        Deck deck = getDeck(deckName);
        deck.remove(card);
        writeDeck(deck);
    }


    // Loaders
    private void loadDeckToHeroMap() {
        if(allDecksMap.size() == 0){
            loadAllDecksMap();
        }
        getAllDecksMap().forEach((deckName, deck) -> deckToHeroMap.put(deckName, deck.getHeroClass().name()));
    }

    private void loadAllDecksMap() {
        getAllFilesInDirectory(privateDecksURL).forEach(this::readDeck);
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

    public void writeDeck(Deck deck) {
//        String path = isPrivate ? privateDecksURL : publicDecksURL;
        try {
            FileWriter writer = new FileWriter(getFile(privateDecksURL + deck.getName() + ".json"));
            new Gson().toJson(deck, writer);
            writer.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
