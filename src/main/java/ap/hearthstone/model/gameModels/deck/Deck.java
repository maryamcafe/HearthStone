package ap.hearthstone.model.gameModels.deck;

import ap.hearthstone.logic.game.CardConstants;
import ap.hearthstone.model.gameModels.exceptions.FullDeckException;
import ap.hearthstone.model.gameModels.exceptions.MaxEachCardException;
import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.cards.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class Deck {

    private List<Card> cards;
    private int totalNumberOfCards;
    private final CardConstants constants;
    private final HeroClass heroClass;
    private Logger logger = LogManager.getLogger(this.getClass());

    public Deck(HeroClass heroClass){
        this.heroClass = heroClass;
        constants = new CardConstants();
        cards = new ArrayList<>();
    }

    public Deck(HeroClass heroClass, List<Card> cards) throws FullDeckException {
        this(heroClass);
        if(cards.size() <= constants.getMaxTotalCards()){
            this.cards.addAll(cards);
        }else {
            throw new FullDeckException();
        }
    }

    public void add(Card cardToAdd) throws MaxEachCardException, FullDeckException {
        if (totalNumberOfCards <= constants.getMaxTotalCards()) {
            if (numberOfCards(cardToAdd) < constants.getMaxEachCard()) {
                this.cards.add(cardToAdd);
                totalNumberOfCards += 1;
            } else {
                throw new MaxEachCardException();
            }
        } else {
            throw new FullDeckException();
        }
    }

    private byte numberOfCards(Card cardToCount) {
         return (byte) cards.stream().filter(c-> c.equals(cardToCount)).mapToInt(c->1).sum();
    }

    public List<Card> getCards() {
        return cards;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }
}
