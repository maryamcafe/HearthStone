package ap.hearthstone.logic.cards;

import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

import java.util.LinkedList;
import java.util.List;

public class CardConstants {

    private final int maxDeckCards;
    private final int maxEachCard;
    private final Configs constants;
    private List<String> defaultDeck;
    private String deckInitiationGuid;


    public CardConstants() {
        constants = ConfigLoader.getInstance().getCardConstants();
        maxEachCard = constants.readInt("maxEachCard");
        maxDeckCards = constants.readInt("maxTotalCards");
    }


    public int getMaxEachCard() {
        return maxEachCard;
    }

    public int getMaxDeckCards() {
        return maxDeckCards;
    }

    public List<String> getDefaultDeck() {
        if (defaultDeck == null) {
            defaultDeck = new LinkedList<>();
            constants.readList("DEFAULT_DECK");
        }
        return defaultDeck;
    }

    public String getDeckInitiationGuid() {
        if (deckInitiationGuid == null) {
            deckInitiationGuid = String.format("Please select %d cards", maxDeckCards);
        }
        return deckInitiationGuid;
    }

    public enum Type {
        SPELL,
        MINION,
        WEAPON;
    }


}
