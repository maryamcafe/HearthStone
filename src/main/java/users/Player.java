package users;

import cards.Card;

public class Player {

    //players coins to buy cards from shop:
    private int coins;
    //all of player's cards:
    private Card[] allCards;
    //cards in player's hand:
    private Card[] currentCards;

    //setters
    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setAllCards(Card[] allCards) {
        this.allCards = allCards;
    }

    public void setCurrentCards(Card[] currentCards) {
        this.currentCards = currentCards;
    }

    //getters
    public int getCoins() {
        return coins;
    }

    public Card[] getAllCards() {
        return allCards;
    }

    public Card[] getCurrentCards() {
        return currentCards;
    }

}
