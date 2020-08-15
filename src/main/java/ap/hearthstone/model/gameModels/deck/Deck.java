package ap.hearthstone.model.gameModels.deck;

import ap.hearthstone.logic.cards.CardFileManager;
import ap.hearthstone.model.gameModels.exceptions.FullDeckException;
import ap.hearthstone.model.gameModels.exceptions.IllegalHeroClass;
import ap.hearthstone.model.gameModels.exceptions.MaxEachCardException;
import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.cards.Card;
import ap.hearthstone.model.gameModels.util.GameConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/*
This model is used in the game, and also in collection to create and save decks.
 */
public class Deck {

    private final String name;
    private final HeroClass heroClass;
    private final List<String> cardsNames;

    private transient int totalNumberOfCards;
    private transient final GameConstants constants;
    private transient final CardFileManager cardFileManager;
    private transient final Map<String, String> cardToHeroMap;
    private transient final Logger logger = LogManager.getLogger(this);

    public Deck(String name, HeroClass heroClass){
        this.name = name;
        this.heroClass = heroClass;
        constants = GameConstants.getInstance();
        cardsNames = new ArrayList<>();
        cardFileManager = CardFileManager.getInstance();
        cardToHeroMap = cardFileManager.getCardNameToHeroMap();
    }

    public Deck(String name, HeroClass heroClass, List<String> cards) throws FullDeckException, MaxEachCardException, IllegalHeroClass {
        this(name, heroClass);
        for (String card : cards) {
            add(card);
        }
    }

    public void add(String card) throws MaxEachCardException, FullDeckException, IllegalHeroClass {
        if(!heroClass.name().equals(cardToHeroMap.get(card))){
            logger.debug(heroClass.name() + "=?" + cardToHeroMap.get(card));
            throw new IllegalHeroClass();
        }
        if (totalNumberOfCards <= constants.getMaxDeckCards()) {
            if (numberOfCards(card) < constants.getMaxEachCard()) {
                this.cardsNames.add(card);
                totalNumberOfCards += 1;
            } else {
                throw new MaxEachCardException();
            }
        } else {
            throw new FullDeckException();
        }
    }

    public void remove(String cardName){
        cardsNames.remove(cardName);
    }

    public Card draw(){
        Random r = new Random(System.nanoTime());
        String cardName = cardsNames.remove(r.nextInt(cardsNames.size()));
        return cardFileManager.getCard(cardName);
    }

    public int numberOfCards(String cardName) {
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
