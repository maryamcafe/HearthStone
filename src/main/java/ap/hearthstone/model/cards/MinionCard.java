package ap.hearthstone.model.cards;

import ap.hearthstone.util.CardConstants;

public class MinionCard extends Card {

    protected CardConstants.Type type = CardConstants.Type.MINION;
    //hp is the lives number of a minion
    private int hp;
    // if a minion attacks, it will decrease enemy's lives this much:
    private int attack;

    //The Action of the card in gameplay
    private Description description;


    public MinionCard(CardConstants.Class cardClass,
                      String name,
                      int manaCost,
                      CardConstants.Rarity rarity,
                      String descriptionText,
                      int number, int hp, int attack) throws Exception {
        super(cardClass,
                name,
                manaCost,
                rarity,
                descriptionText,
                number);
        if (attack <= 0 || hp <= 0) {
            throw new Exception(CardConstants.attackWrongInputMsg);
        } else {
            this.attack = attack;
            this.hp = hp;
        }
        description = new Description(descriptionText);
    }

    public MinionCard(CardConstants.Class cardClass, String name, int manaConst, CardConstants.Rarity rarity, String description, int number) {
        super(cardClass, name, manaConst, rarity, description, number);
    }


    @Override
    public String toString() {
        String output = "type: \t\t" + type +
                "\nClass: \t\t" + cardClass +
                "\nname: \t\t" + name +
                "\nmanaCost: \t" + manaCost +
                "\nrarity: \t" + rarity +
                "\nDescription: " + descriptionText +
                "\nhp: \t\t" + hp +
                "\nattack:\t\t" + attack +
                "\nNumber Of this Card:" + number;
        return output;
    }

}
