package ap.hearthstone.model.gameModels.entities;

import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.deck.Deck;
import ap.hearthstone.model.gameModels.util.GameConstants;

import java.util.List;

//each hero has some cards that enters the game with it.
public abstract class Hero extends GameCharacter {

    private final HeroClass heroClass;
    private String image;

    protected Hero(HeroClass heroClass) {
        this.heroClass = heroClass;
        image = heroClass.name()+".png";
        health = new GameConstants().getHeroInitialHealth();
    }

    public String getImage() {
        return image;
    }

    @Override
    protected void receiveHealth(byte health) {
        this.health += health;
    }

    @Override
    protected void receiveDamage(byte damage) {

    }

    public String toString() {
        return heroClass.toString();
    }

}
