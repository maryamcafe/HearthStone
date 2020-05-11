package cards;


import util.CardConstants;

public class WeaponCard extends Card {

    protected CardConstants.Type type = CardConstants.Type.WEAPON;

    //this number shows how many times a weapon can be used
    private int durability;

    //The Action of the card in gameplay
    private Description description;

    public WeaponCard(CardConstants.Class cardClass,
                      String name,
                      int manaCost,
                      CardConstants.Rarity rarity,
                      String descriptionText,
                      int number, int durability) throws Exception {
        super(cardClass,
                name,
                manaCost,
                rarity,
                descriptionText,
                number);
        if (durability <= 0) {
            throw new Exception(CardConstants.durabilityWrongInputMsg);
        } else {
            this.durability = durability;
        }
        description = new Description(descriptionText);
    }

    @Override
    public String toString() {

        return "type: \t\t" + type +
                "\nClass: \t\t" + cardClass +
                "\nname: \t\t" + name +
                "\nmanaCost: \t" + manaCost +
                "\nrarity: \t" + rarity +
                "\nDescription: " + descriptionText +
                "\ndurability: \t\t" + durability +
                "\nNumber Of this Card:" + number;
    }
}
