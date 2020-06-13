package model.cards;

import java.util.HashMap;
import java.util.Map;

public class Collection {
    private Deck deck;
    private Map<String, Card> notInDeck;
    private Map<String, Card> allCards = new HashMap<>();

    //collection in each game is initiated from a file
    public Collection(Deck deck, Map<String, Card> notInDeck) {
        this.deck = deck;
        this.notInDeck = notInDeck;
    }

    public void addToDeck(Card card) throws Exception {
        if(!notInDeck.containsKey(card.getName())){
            throw new Exception("Card can't be added to deck, it's not in your collection");
        } else {
            notInDeck.remove(card.getName());
            deck.add(card);
        }
    }

    public void addToCollection(Card card){
        notInDeck.put(card.getName(),card);
    }


}
