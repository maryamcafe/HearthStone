package cards;

import cards.CardConstants.Rarity;
import cards.CardConstants.Type;

import static cards.CardConstants.Type.*;

public class Card {
    private Type type;
    private CardConstants.Class cardClass;
    private String name;
    private int manaCost;
    private Rarity rarity;
    private String descriptionText;
    //hp is the lives number of a minion
    private int hp = 0;
    // if a minion attacks, it will decrease enemy's lives this much:
    private int attack = 0;
    //this attribute is only for weapons
    private int durability = 0;
    //The Action of the card in gameplay
    private Description description;

    //a card initializer
    public Card(Type type, CardConstants.Class cardClass, String name, int manaCost, Rarity rarity,
                String descriptionText, int hp, int attack, int durability) throws Exception {
        //Checking inputs
        if (manaCost <= 0)
            throw new Exception("ManaCost should be an integer and grater than zero");
        this.type = type;
        this.cardClass = cardClass;
        this.name = name;
        this.manaCost = manaCost;
        this.rarity = rarity;
        this.descriptionText = descriptionText;
        //hp and attack is only for Minions, otherwise they will remain 0
        if (type == MINION) {
            this.attack = attack;
            this.hp = hp;
        }
        //durability is only for Weapons, otherwise it will remain 0
        else if (type == WEAPON)
            this.durability = durability;

        description = new Description(descriptionText);
    }

    //GETTERS
    public Type getType() {
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

    public Rarity getRarity() {
        return rarity;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    //Only if Type == MINION
    //otherwise we get 0
    public int getHp() {
        return hp;
    }

    //Only if Type = MINION
    //otherwise we get 0
    public int getAttack() {
        return attack;
    }

    //Only if Type = WEAPON
    //otherwise we get 0
    public int getDurability() {
        return durability;
    }

    // a method to print a Card in a pretty way
    @Override
    public String toString() {
        Type type1 = QUEST;
        String output = "type: \t\t" + type +
                "\nClass: \t\t" + cardClass +
                "\nname: \t\t" + name +
                "\nmanaCost: \t" + manaCost +
                "\nrarity: \t" + rarity +
                "\nDescription: " + descriptionText;
        switch (type) {
            case MINION:
                output += "\nhp: \t\t" + hp + "\nattack: \t" + attack;
                break;
            case WEAPON:
                output += "\ndurability: \t\t" + durability;
                break;
        }
        return output;
    }
}




