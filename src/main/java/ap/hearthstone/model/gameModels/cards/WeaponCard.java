package ap.hearthstone.model.gameModels.cards;

import ap.hearthstone.logic.game.PlayVisitor;
import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.ability.Ability;

import java.util.Arrays;

public class WeaponCard extends Card {

    private final int attack, durability;

    public WeaponCard(HeroClass heroClass, String name, int mana, Rarity rarity, String descriptionText,
                      int attack, int durability) {
        super(CardType.WEAPON, heroClass, name, mana, rarity, descriptionText);
        if (durability <= 0) {
            throw new IllegalArgumentException("Weapon's durability should be greater than zero.");
        } else {
            this.attack = attack;
            this.durability = durability;
        }
    }

    public WeaponCard(HeroClass heroClass, String name, int mana, Rarity rarity, String descriptionText,
                      int attack, int durability, Ability... abilities){
        this(heroClass, name, mana, rarity, descriptionText, attack, durability);
        this.abilities = Arrays.asList(abilities);
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

    public int getAttack() {
        return attack;
    }

    /* How many times this weapon can be used. */
    public int getDurability() {
        return durability;
    }

    @Override
    public void play(PlayVisitor playVisitor) {
        playVisitor.playWeaponCard(this);
    }
}
