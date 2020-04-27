package cards;

import cards.CardConstants.Class;
import cards.CardConstants.*;

import static cards.CardConstants.Type.*;
import static cards.CardConstants.*;

public class Card {
    protected Type type;
    protected Class cardClass;
    protected String name;
    protected int manaCost;
    protected Rarity rarity;
    protected String descriptionText;

    //this shows HOW MANY of that specific card we have:
    protected int number;

    //a card initializer

    public Card(Class cardClass,
                String name,

                int manaCost,
                Rarity rarity,
                String descriptionText,
                int number) {
        this.cardClass = cardClass;
        this.name = name;
        this.manaCost = manaCost;
        this.rarity = rarity;
        this.descriptionText = descriptionText;
        this.number = number;
    }

    //GETTERS
    public Type getType() {
        return type;
    }

    public Class getCardClass() {
        return cardClass;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public int getNumber() {
        return number;
    }


}




