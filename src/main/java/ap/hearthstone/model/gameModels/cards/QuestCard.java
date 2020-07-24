package ap.hearthstone.model.gameModels.cards;

import ap.hearthstone.model.gameModels.HeroClass;

public class QuestCard extends SpellCard {
    public QuestCard(HeroClass heroClass, String name, int mana, Rarity rarity, String descriptionText) {
        super(heroClass, name, mana, rarity, descriptionText);
    }

}
