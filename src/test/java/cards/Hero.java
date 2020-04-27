package cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static cards.CardConstants.defaultDeck;

public class Hero {
    private Deck deck;
    private int lives;
    private int attack;
    private HeroName heroName;
    private boolean isOpen;


    public Hero(HeroName heroName) {
        initDeck(heroName);
    }

    private void initDeck(HeroName heroName) {
        deck = stringToCard(defaultDeck);
        switch heroName
    }

    public enum HeroName{
        MAGE,
        ROGUE,
        WARLOCK
    }

    public String toString(){
        String output = "";
    }

}
