package ap.hearthstone.model.gameModels.entities;

import ap.hearthstone.model.gameModels.ability.Ability;
import ap.hearthstone.model.gameModels.cards.MinionCard;

import java.util.List;

public class Minion extends GameCharacter {

    private boolean overkill = false;
    private List<Ability> abilities;

    public Minion(MinionCard minionCard) {
        this.attack = minionCard.getAttack();
        this.initialHealth = minionCard.getInitialHealth();
        health = initialHealth;
        this.abilities = minionCard.getAbilities();
    }

    @Override
    public void receiveDamage(int damage) {
        if (health - damage >= 0) {
            health -= damage;
        } else {
            health = 0;
            overkill = true;
        }
    }

    public boolean isOverkill() {
        return overkill;
    }

}
