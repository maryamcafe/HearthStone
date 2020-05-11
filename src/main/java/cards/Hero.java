package cards;

import util.CardConstants;

import java.util.List;


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
        deck = stringToCard(CardConstants.defaultDeck);
        switch (heroName){}
    }

    private Deck stringToCard(List<String> defaultDeck) {
        return null;
    }

    public enum HeroName{
        MAGE,
        ROGUE,
        WARLOCK
    }

    public String toString(){
        return "";
    }

}
