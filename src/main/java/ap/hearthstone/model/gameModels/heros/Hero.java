package ap.hearthstone.model.gameModels.heros;

import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.entities.GameCharacter;
import ap.hearthstone.model.gameModels.util.GameConstants;

//each hero has some cards that enters the game with it.
public abstract class Hero extends GameCharacter {

    private final HeroClass heroClass;

    protected Hero(HeroClass heroClass) {
        this.heroClass = heroClass;
        initialHealth = new GameConstants().getHeroInitialHealth();
        health = initialHealth;
    }

    /* If health is reduced to an amount below zero, it will be zero then. */
    @Override
    public void addHealth(int i) {
        if (health + i <= 0){
            health = 0;
        } else {
            health += i;
        }
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public String toString() {
        return heroClass.toString();
    }
}
