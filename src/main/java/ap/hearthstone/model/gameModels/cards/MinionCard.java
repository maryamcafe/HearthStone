package ap.hearthstone.model.gameModels.cards;

import ap.hearthstone.model.gameModels.entities.GameCharacter;
import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.entities.Minion;

public class MinionCard extends Card {

    private int attack, initialHealth;
    private Minion minion;

    public MinionCard(HeroClass heroClass, String name, int mana, Rarity rarity, String descriptionText
            , int attack, int initialHealth){
        super(heroClass, name, mana, rarity, descriptionText);
        type = CardType.MINION;
        if (attack <= 0 || initialHealth <= 0) {
            throw new IllegalArgumentException("Health and and attack can not be zero or less.");
        } else {
            minion = new Minion(attack, initialHealth);
        }
    }


    @Override
    public String toString() {
        String output = "\nHero Class: \t\t" + heroClass +
                "\nName: \t\t" + name +
                "\nMana: \t" + mana +
                "\nRarity: \t" + rarity +
                "\nDescription: " + descriptionText +
                "\nHealth: \t\t" + initialHealth +
                "\nAttack:\t\t" + attack;
        return output;
    }



    @Override
    protected void setCardType() {
       type = CardType.MINION;
    }
}
