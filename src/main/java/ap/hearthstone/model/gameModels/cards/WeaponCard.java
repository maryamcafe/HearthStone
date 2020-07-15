package ap.hearthstone.model.gameModels.cards;


import ap.hearthstone.logic.game.CardConstants;
import ap.hearthstone.model.gameModels.HeroClass;

public class WeaponCard extends Card {
    //this number shows how many times a weapon can be used
    private int durability;

    public WeaponCard(HeroClass heroClass, String name, int mana, Rarity rarity, String descriptionText,
                      int durability) {
        super(heroClass, name, mana, rarity, descriptionText);
        type = CardType.WEAPON;
        if (durability <= 0) {
            throw new IllegalArgumentException("Weapon's durability should be greater than zero.");
        } else {
            this.durability = durability;
        }
    }

    @Override
    public String toString() {

        return "type: \t\t" + type +
                "\nClass: \t\t" + heroClass +
                "\nname: \t\t" + name +
                "\nmanaCost: \t" + mana +
                "\nrarity: \t" + rarity +
                "\nDescription: " + descriptionText +
                "\ndurability: \t\t" + durability;
    }

    @Override
    protected void setCardType() {
        type = CardType.WEAPON;
    }
}
