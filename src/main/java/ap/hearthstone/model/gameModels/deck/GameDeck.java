package ap.hearthstone.model.gameModels.deck;

import ap.hearthstone.model.gameModels.cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameDeck {

    List<Card> cards;

    public GameDeck(Deck initialDeck){
        cards = new ArrayList<>();
        cards.addAll(initialDeck.getCards());
    }

    public Card draw(){
        Random r = new Random(System.nanoTime());
        return cards.remove(r.nextInt(cards.size()));
    }
}
