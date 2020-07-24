package ap.hearthstone.model.gameModels.ability;

import ap.hearthstone.model.gameModels.entities.Minion;

public interface MinionAbility extends AbilityAction {
    void act(Minion target);
}
