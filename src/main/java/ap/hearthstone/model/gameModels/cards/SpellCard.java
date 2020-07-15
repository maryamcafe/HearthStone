package ap.hearthstone.model.gameModels.cards;


import ap.hearthstone.logic.game.CardConstants;
import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.ability.Ability;

import java.util.Map;


public class SpellCard extends Card {

    public SpellCard(HeroClass heroClass, String name, int mana, Rarity rarity, String descriptionText) {
        super(heroClass, name, mana, rarity, descriptionText);
    }

    public SpellCard(HeroClass heroClass, String name, int mana, Rarity rarity, String descriptionText, Ability... abilities) {
        super(heroClass, name, mana, rarity, descriptionText, abilities);
    }

    @Override
    protected void setCardType() {
        type = CardType.SPELL;
    }

    @Override
    public String toString() {
        String output = "type: \t\t" + type +
                "\nClass: \t\t" + heroClass +
                "\nname: \t\t" + name +
                "\nmanaCost: \t" + mana +
                "\nrarity: \t" + rarity +
                "\nDescription: " + descriptionText;

        return output;
    }
}
