package ap.hearthstone.model.gameModels.util;

import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.cards.Card;
import ap.hearthstone.model.gameModels.cards.MinionCard;
import ap.hearthstone.model.gameModels.cards.SpellCard;

public class CardGenerator {
    public static void writeTheseCards() {
        Card polymorph = new SpellCard(HeroClass.MAGE, "Polymorph", 4,
                Card.Rarity.FREE, "transform a minion into a 1/1 sheep");
        Card friendlySmith = new SpellCard(HeroClass.MAGE, "FriendlySmith", 1,
                Card.Rarity.FREE, "Discover a weapon from any class." +
                "Add it to your deck with +2/+2"); //note that it does'nt add to your hand, but to your deck.
        Card dreadScale = new MinionCard(HeroClass.WARLOCK, "Dreadscale", (byte) 3,
                Card.Rarity.LEGENDARY, "At the end of your turn, deal 1 damage to all other minions.",
                 4,  2);

    }
}
