package ap.hearthstone.model.gameModels.heros;

import ap.hearthstone.interfaces.Playable;
import ap.hearthstone.logic.game.PlayVisitor;
import ap.hearthstone.model.gameModels.ability.AbilityAction;
import ap.hearthstone.interfaces.ManaHandler;

public class HeroPower implements ManaHandler, Playable {
    int Mana;
    AbilityAction abilityAction;


    public HeroPower(int mana) {
        Mana = mana;
    }

    @Override
    public int getMana() {
        return 0;
    }

    @Override
    public void reduceMana(int i) {

    }

    @Override
    public void increaseMana(int i) {

    }

    @Override
    public void play(PlayVisitor playVisitor) {
        playVisitor.playHeroPower(this);
    }
}
