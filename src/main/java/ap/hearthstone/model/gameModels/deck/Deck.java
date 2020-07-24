package ap.hearthstone.model.gameModels.deck;

import ap.hearthstone.logic.cards.CardConstants;
import ap.hearthstone.logic.cards.CardFileManager;
import ap.hearthstone.model.gameModels.exceptions.FullDeckException;
import ap.hearthstone.model.gameModels.exceptions.MaxEachCardException;
import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.cards.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/*
This model is used in the game, and also in collection to create and save decks.
 */
public class Deck {

    private final String name;
    private final HeroClass heroClass;
    private final List<String> cardsNames;

    private transient int totalNumberOfCards;
    private transient final CardConstants constants;
    private transient final CardFileManager cardFileManager;
    private transient Logger logger = LogManager.getLogger(this);

    public Deck(String name, HeroClass heroClass){
        this.name = name;
        this.heroClass = heroClass;
        constants = new CardConstants();
        cardsNames = new ArrayList<>();
        cardFileManager = new CardFileManager();
    }

    public Deck(String name, HeroClass heroClass, List<String> cards) throws FullDeckException, MaxEachCardException {
        this(name, heroClass);
        for (String card : cards) {
            add(card);
        }
    }

    public void add(String cardName) throws MaxEachCardException, FullDeckException {
        if (totalNumberOfCards <= constants.getMaxDeckCards()) {
            if (numberOfCards(cardName) < constants.getMaxEachCard()) {
                this.cardsNames.add(cardName);
                totalNumberOfCards += 1;
            } else {
                throw new MaxEachCardException();
            }
        } else {
            throw new FullDeckException();
        }
    }

    public Card draw(){
        Random r = new Random(System.nanoTime());
        String cardName = cardsNames.remove(r.nextInt(cardsNames.size()));
        return cardFileManager.getCard(cardName);
    }

    private int numberOfCards(String cardName) {
         return cardsNames.stream().filter(c-> c.equals(cardName)).mapToInt(c->1).sum();
    }

    // GETTERS
    public String getName() {
        return name;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public List<String> getCards() {
        return new LinkedList<>(cardsNames);
    }
}
