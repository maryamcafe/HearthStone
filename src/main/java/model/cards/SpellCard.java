package model.cards;


import util.CardConstants;


public class SpellCard extends Card {

    protected CardConstants.Type type = CardConstants.Type.SPELL;

    //The Action of the card in gamePlay
    private Description description;


    public SpellCard(CardConstants.Class cardClass,
                     String name,
                     int manaCost,
                     CardConstants.Rarity rarity,
                     String descriptionText,
                     int number) {
        super(cardClass,
                name,
                manaCost,
                rarity,
                descriptionText,
                number);
        description = new Description(descriptionText);
    }


    @Override
    public String toString() {
        String output = "type: \t\t" + type +
                "\nClass: \t\t" + cardClass +
                "\nname: \t\t" + name +
                "\nmanaCost: \t" + manaCost +
                "\nrarity: \t" + rarity +
                "\nDescription: " + descriptionText +
                "\nNumber Of this Card:" + number;

        return output;
    }
}
