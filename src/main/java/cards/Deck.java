package cards;

import util.CardConstants;

import java.util.HashMap;
import java.util.Map;


public class Deck {

    private Map<String, Card> cards;
    private int totalNumber = 0;

    public Deck(Map<String, Card> cards) {
        this.cards = cards;
    }

    public void add(Card cardToAdd) throws Exception {
        String cardName = cardToAdd.getName();
        if (totalNumber <= CardConstants.maxTotalCardsDeck) {
            if (cards.get(cardName).getNumber() < CardConstants.maxEachCard) {
                this.cards.put(cardToAdd.getName(), cardToAdd);
                totalNumber += 1;
            } else {
                throw new Exception("you already have 2 of these cards, can't add more!");
            }
        } else {
            throw new Exception("deck is full, card can't be added");
        }
    }

}
