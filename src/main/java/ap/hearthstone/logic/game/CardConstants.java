package ap.hearthstone.logic.game;

import ap.hearthstone.UI.util.PanelConfig;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CardConstants {

    private final int maxTotalCards;
    private final int maxEachCard;
    private final Configs constants;
    private List<String> defaultDeck;


    public CardConstants() {
        constants = ConfigLoader.getInstance().getCardConstants();
        maxEachCard = constants.readInt("maxEachCard");
        maxTotalCards = constants.readInt("maxTotalCards");
    }



    public int getMaxEachCard() {
        return maxEachCard;
    }

    public int getMaxTotalCards() {
        return maxTotalCards;
    }

    public List<String> getDefaultDeck() {
        if(defaultDeck == null){
            defaultDeck = new LinkedList<>();
            constants.readList("DEFAULT_DECK");
        }
        return defaultDeck;
    }


    public enum Type {
        SPELL,
        MINION,
        WEAPON;
    }




}
