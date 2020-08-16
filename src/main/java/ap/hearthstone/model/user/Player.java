package ap.hearthstone.model.user;

import ap.hearthstone.model.gameModels.deck.Deck;
import ap.hearthstone.model.gameModels.exceptions.MaxEachCardException;
import ap.hearthstone.model.gameModels.util.GameConstants;

import java.util.*;

public class Player {
    private final String username;
    private final List<String> cards;
    private final Set<Deck> decks;
    private Deck defaultDeck;
    private int coins;
    private final Set<String> openHeroes;

    private transient final int maxEachCard;

    public Player(String username, List<String> cards, Set<Deck> decks, Deck defaultDeck, int coins, Set<String> openHeroes) {
        this.username = username;
        this.cards = cards;
        this.decks = decks;
        this.defaultDeck = defaultDeck;
        this.coins = coins;
        this.openHeroes = openHeroes;

        maxEachCard = GameConstants.getInstance().getMaxDeckCards();
    }

    // Setters
    public void addCoins(int coins) {
        if (this.coins + coins > 0) {
            this.coins += coins;
        } else {
            this.coins = 0;
        }
    }

    public void addCard(String card) throws MaxEachCardException {
        if (countCard(card) >= maxEachCard+3) {
            throw new MaxEachCardException();
        } else {
            this.cards.add(card);
        }
    }

    public boolean removeCard(String cardName) {
        return cards.remove(cardName);
    }

    public int countCard(String card) {
        return cards.stream().filter(c -> c.equals(card)).mapToInt(c -> 1).sum();
    }

    public void addDeck(Deck deck) {
        this.decks.add(deck);
    }

    public void setDefaultDeck(Deck defaultDeck) {
        this.defaultDeck = defaultDeck;
        if(!decks.contains(defaultDeck)){
            addDeck(defaultDeck);
        }
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public int getCoins() {
        return coins;
    }

    public List<String> getCards() {
        return new LinkedList<>(cards);
    }

    public List<Deck> getDecks() {
        return new ArrayList<>(decks);
    }

    public Deck getDefaultDeck() {
        return defaultDeck;
    }

    public Set<String> getOpenHeroes() {
        return new HashSet<>(openHeroes);
    }

    @Override
    public String toString(){
        return String.format("%s: username: %s, coins: %s, OpenHeroes: %s, default deck: %s",
                super.toString(), username, coins, openHeroes, defaultDeck.getName());
    }

}
