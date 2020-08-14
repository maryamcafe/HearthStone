package ap.hearthstone.logic.game.visitors;

import ap.hearthstone.model.gameModels.GameState;
import ap.hearthstone.model.gameModels.ability.CharacterAbility;
import ap.hearthstone.model.gameModels.ability.MinionAbility;
import ap.hearthstone.model.gameModels.entities.Minion;
import ap.hearthstone.model.gameModels.entities.Weapon;
import ap.hearthstone.model.gameModels.heros.Hero;

public class StartOfTurnVisitor implements GameVisitor{

    private MinionAbility minionAbility;
    private CharacterAbility characterAbility;

    public StartOfTurnVisitor(MinionAbility minionAbility) {
        this.minionAbility = minionAbility;
    }

    public StartOfTurnVisitor(MinionAbility minionAbility, CharacterAbility characterAbility) {
        this.minionAbility = minionAbility;
        this.characterAbility = characterAbility;
    }

    @Override
    public void doForGameState(GameState gameState) {
    }

    @Override
    public void doForMinion(Minion minion) {
        minionAbility.act(minion);
    }

    @Override
    public void doForHero(Hero hero) {

    }

    @Override
    public void doForCharacter(Character character) {

    }

    @Override
    public void doForWeapon(Weapon weapon) {

    }
}
