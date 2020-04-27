package users;

import cards.Card;
import cards.Hero;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Set;

public class Player {
    //Player's login information
    @Expose
    private User user;

    //Coins are used to buy cards from shop, which is initially equall to 50
    @Expose
    private int coins = 50;
    //all of player's cards:
    @Expose
    private Set<String> allCardsString;

    private List<Card> allCards;
    //Cards in player's deck, maximum number of cards is 15 here
    private List<Card> deckCards;

    private Hero hero;

    //Initializing a player for the first time
    Player (User user) {
        this.user = user;
    }

    //setters
    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setAllCards(List<Card> allCards) {
        this.allCards = allCards;
    }

    public void setDeckCards(List<Card> deckCards) {
        this.deckCards = deckCards;
    }

    //getters
    public User getUser() {
        return user;
    }

    public int getCoins() {
        return coins;
    }

    public List<Card> getAllCards() {
        return allCards;
    }

    public List<Card> getDeckCards() {
        return deckCards;
    }

    public static void delete() {
//        user.delete();
        //add DELETED AT... to headerfile
    }

}
