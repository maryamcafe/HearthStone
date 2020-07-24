package ap.hearthstone.model.gameModels.ability;

import ap.hearthstone.model.gameModels.cards.Card;

import java.util.List;

public interface CardAbilityAction extends AbilityAction{
    void act(List<Card> target);
}
