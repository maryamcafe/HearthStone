package ap.hearthstone.model.gameModels.heros;

import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.entities.GameCharacter;
import ap.hearthstone.model.gameModels.entities.Weapon;
import ap.hearthstone.model.gameModels.util.GameConstants;

//each hero has some cards that enters the game with it.
public abstract class Hero extends GameCharacter {

    private final HeroClass heroClass;
    private Weapon weapon;

    protected Hero(HeroClass heroClass) {
        this.heroClass = heroClass;
        initialHealth = GameConstants.getInstance().getHeroInitialHealth();
        health = initialHealth;
        attack = 0;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        attack = weapon.getAttack();
    }

    @Override
    public void attack(GameCharacter target) {
        super.attack(target);
        attack = 0;
        weapon.use();
    }

    /* If health is reduced to an amount below zero, it will be zero then. */
    @Override
    public void receiveDamage(int damage) {
        if (health - damage >= 0){
            health -= damage;
        } else {
            health = 0;
        }
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public String toString() {
        return heroClass.toString();
    }

}
