package ap.hearthstone.logic.game.visitors;

import ap.hearthstone.model.gameModels.GameState;
import ap.hearthstone.model.gameModels.entities.Minion;
import ap.hearthstone.model.gameModels.entities.Weapon;
import ap.hearthstone.model.gameModels.heros.Hero;

public interface GameVisitor  {

    void doForGameState (GameState gameState);

    void doForMinion(Minion minion);

    void doForHero(Hero hero);

    void doForCharacter(Character character);

    void doForWeapon(Weapon weapon);

}
