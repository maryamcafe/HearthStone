package ap.hearthstone.model.gameModels.deck;

import ap.hearthstone.model.gameModels.cards.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {

    private List<Card> cards;

    public Hand(Card[] initialCards){
        cards = new ArrayList<>();
        cards.addAll(Arrays.asList(initialCards));
    }
}
