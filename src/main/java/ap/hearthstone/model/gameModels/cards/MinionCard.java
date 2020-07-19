package ap.hearthstone.model.gameModels.cards;

import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.ability.Ability;
import ap.hearthstone.model.gameModels.entities.Minion;

import java.util.Arrays;

public class MinionCard extends Card {

    private final int attack, initialHealth;

    public MinionCard(HeroClass heroClass, String name, int mana, Rarity rarity, String descriptionText,
                      int attack, int initialHealth){
        super(CardType.MINION, heroClass, name, mana, rarity, descriptionText);
        if (attack < 0) {
            throw new IllegalArgumentException("Attack can not be less than zero.");
        } else if(initialHealth <= 0) {
            throw new IllegalArgumentException("Health can not be zero or less.");
        } else {
            this.initialHealth = initialHealth;
            this.attack = attack;
        }
    }

    public MinionCard(HeroClass heroClass, String name, int mana, Rarity rarity, String descriptionText,
                      int attack, int initialHealth, Ability... abilities){
        this(heroClass, name, mana, rarity, descriptionText, attack, initialHealth);
        this.abilities = Arrays.asList(abilities);
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

    public int getAttack() {
        return attack;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

}
