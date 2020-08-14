package ap.hearthstone.model.user;

import ap.hearthstone.logic.cards.DeckFileManager;
import ap.hearthstone.model.gameModels.deck.Deck;
import ap.hearthstone.model.gameModels.util.GameConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {
    /* Player's login information*/
    private final String username;
    private int coins;
    private final List<String> cards;
    private final List<Deck> decks;
    private Deck defaultDeck;
    private List<String> openHeroes;

    public Player(String username) {
        this.username = username;
        cards = new ArrayList<>();
        decks = new ArrayList<>();
        openHeroes = new ArrayList<>();
        GameConstants constants = GameConstants.getInstance();
        coins = constants.getDefaultCoins();

        DeckFileManager deckFileManager = new DeckFileManager(username);
        defaultDeck = deckFileManager.getPublicDeck(constants.getDefaultDeck());
        initDeck();
    }

    private void initDeck() {
        addDeck(defaultDeck);
        for(String card: defaultDeck.getCards()){
            addCard(card);
        }
    }



    //setters
    public void addCoins(int coins) {
        if (this.coins + coins > 0) {
            this.coins += coins;
        } else {
            this.coins = 0;
        }
    }

    public void addCard(String card) {
        this.cards.add(card);
    }

    public void addDeck(Deck deck) {
        this.decks.add(deck);
    }

    public void setDefaultDeck(Deck defaultDeck) {
        this.defaultDeck = defaultDeck;
    }


    //getters
    public String getUsername() {
        return username;
    }

    public int getCoins() {
        return coins;
    }

    public List<String> getCards() {
        return new ArrayList<>(cards);
    }

    public List<Deck> getDecks() {
        return new ArrayList<>(decks);
    }



}
