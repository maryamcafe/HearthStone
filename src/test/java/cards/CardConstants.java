package cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CardConstants {

    static final int maxTotalCardsDeck = 15;
    static final int maxEachCard = 2;
    static final String manaCostWrongInputMsg = "ManaCost should and be grater than zero.";
    static final String attackWrongInputMsg = "number of attacks and hp of minion should be grater than zero.";
    static final String durabilityWrongInputMsg = "Durability should and be grater than zero.";
    static List<String> defaultDeck = new ArrayList<>();

    static {
        //#1
        defaultDeck.add("");
        //#2
        defaultDeck.add(" ");
        //#3
        defaultDeck.add(" ");
        //#4
        defaultDeck.add(" ");
        //#5
        defaultDeck.add(" ");
        //#6
        defaultDeck.add(" ");
        //#7
        defaultDeck.add(" ");
        //#8
        defaultDeck.add(" ");
        //#9
        defaultDeck.add(" ");
    }


    enum Type {
        SPELL,
        MINION,
        WEAPON,
        QUEST
    }

    enum Rarity {
        COMMON,
        RARE,
        EPIC,
        LEGENDARY
    }

    enum Class {
        MAGE,
        ROGUE,
        WARLOCK,
        NEUTRAL
    }


}
