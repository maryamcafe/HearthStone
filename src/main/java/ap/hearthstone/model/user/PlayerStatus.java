package ap.hearthstone.model.user;

import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.cards.DeckStatus;
import ap.hearthstone.model.gameModels.deck.Deck;

//import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatus {
    /* Player's login information*/
    private final User user;
    private int coins = 50;
    private List<String> allCards;
    private List<Deck> allDecks;
    private DeckStatus deckStatus;
    private List<HeroClass> openHeroes;

    public PlayerStatus(User user) {
        this.user = user;
    }

    //setters
    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCards(List<String> cards) {
        allCards.addAll(cards);
    }

    public void addDecks(List<Deck> decks) {
        allDecks.addAll(decks);
    }

    //getters
    public User getUser() {
        return user;
    }

    public int getCoins() {
        return coins;
    }

    public List<String> getAllCards() {
        return new ArrayList<>(allCards);
    }

    public List<Deck> getDeckCards() {
        return new ArrayList<>(allDecks);
    }


}
