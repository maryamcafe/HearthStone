package ap.hearthstone.logic.cards;

import ap.hearthstone.logic.users.PlayerManager;
import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.deck.Deck;
import ap.hearthstone.model.gameModels.exceptions.FullDeckException;
import ap.hearthstone.model.gameModels.exceptions.IllegalHeroClass;
import ap.hearthstone.model.gameModels.exceptions.MaxEachCardException;
import ap.hearthstone.model.gameModels.util.GameConstants;
import ap.hearthstone.model.user.Player;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.FileManager;
import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DeckManager extends FileManager {

    private final String publicDecksURL;
    private final String privateDecksURL;
    private final Map<String, String> deckToHeroMap;
    private final List<Deck> allDecksList;
    private Deck defaultDeck;

    public DeckManager(String username) {
        deckToHeroMap = new HashMap<>();
        allDecksList = new LinkedList<>();
        publicDecksURL = ConfigLoader.getInstance().getDecksURL();
        privateDecksURL = ConfigLoader.getInstance().getUsersURL() + username + "/";
    }

    //Getters
    public Deck getPublicDeck(String deckName) throws FileNotFoundException {// TODO check if this method needs to be static
        return readDeck(publicDecksURL + deckName + ".json");
    }

    public Deck getDefaultDeck() throws FileNotFoundException {
        if (defaultDeck == null) {
            defaultDeck = getPublicDeck(GameConstants.getInstance().getDefaultDeck());
        }
        return defaultDeck;
    }

    public Deck getDeck(String deckName) throws FileNotFoundException {
        return readDeck(privateDecksURL + deckName + ".json");
    }

    /* Should be called after getPlayer() is called from corresponding PlayerFileManager*/
    public Map<String, String> getDeckToHeroMap() {
//        if (deckToHeroMap.size() == 0) {
//            loadDeckToHeroMap();
//        }
        logger.debug("deckToHeroMap is called; while it's size is:{}", deckToHeroMap.size());
        return new HashMap<>(deckToHeroMap);
    }

    public List<Deck> getAllDecksList() {
//        if (allDecksList.size() == 0) {
//            loadAllDecksList();
//        }
        return new LinkedList<>(allDecksList);
    }
//
//    // Loaders
//    private void loadDeckToHeroMap() {
//        getAllDecksList().forEach(deck -> deckToHeroMap.put(deck.getName(), deck.getHeroClass().name()));
//    }
//
//    private void loadAllDecksList() {
//        getAllFilesInDirectory(privateDecksURL).forEach(file -> {
//            try {
//                 Deck deck = readDeck(file);
//                 assert deck!=null;
//                 allDecksList.add(deck);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        });
//    }

    /* Create a deck and save it. */
    public Deck createDeck(String name, String heroName, List<String> cardNames) throws FullDeckException, MaxEachCardException, IllegalHeroClass {
        logger.debug("in CREATE DECK, hero name is:{}", heroName);
        Deck deck = new Deck(name, HeroClass.valueOf(heroName), cardNames);
        update(deck);
        writeDeck(deck);
        return deck;
    }

    public void createDeck(Deck deck) throws MaxEachCardException, IllegalHeroClass, FullDeckException {
        createDeck(deck.getName(), deck.getHeroClass().name(), deck.getCards());
    }

    public void update(Deck deck) {
        deckToHeroMap.put(deck.getName(), deck.getHeroClass().name());
        logger.debug("deck {} with size {} was added to deckToHeroMap", deck, deck.getCards().size());
        logger.debug("deckToHeroMap size is now: {}", deckToHeroMap.size());
    }

    // EDIT DECK
    public void addCardToDeck(String card, String deckName) throws MaxEachCardException, IllegalHeroClass, FullDeckException, FileNotFoundException {
        Deck deck = getDeck(deckName);
        deck.add(card);
        writeDeck(deck);
    }

    public void removeCardFromDeck(String card, String deckName) throws MaxEachCardException, IllegalHeroClass, FullDeckException, FileNotFoundException {
        Deck deck = getDeck(deckName);
        deck.remove(card);
        writeDeck(deck);
    }



    private Deck readDeck(String path) throws FileNotFoundException {
        return readDeck(getFile(path));
    }

    private Deck readDeck(File file) throws FileNotFoundException {
        FileReader reader = new FileReader(file);
        return new Gson().fromJson(reader, Deck.class);
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
