package ap.hearthstone.model.gameModels.deck;

import ap.hearthstone.model.gameModels.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class GameDeck {

    List<Card> cards;

    public GameDeck(Deck initialDeck){
        cards = new ArrayList<>();
        cards.addAll(initialDeck.getCards());
    }
}
