package model.cards;

import util.CardConstants;


public class Card {
    protected CardConstants.Type type;
    protected CardConstants.Class cardClass;
    protected String name;
    protected int manaCost;
    protected CardConstants.Rarity rarity;
    protected String descriptionText;

    //this shows HOW MANY of that specific card we have:
    protected int number;

    //a card initializer

    public Card(CardConstants.Class cardClass,
                String name,
                int manaCost,
                CardConstants.Rarity rarity,
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
    public CardConstants.Type getType() {
        return type;
    }

    public CardConstants.Class getCardClass() {
        return cardClass;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public CardConstants.Rarity getRarity() {
        return rarity;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public int getNumber() {
        return number;
    }


}




