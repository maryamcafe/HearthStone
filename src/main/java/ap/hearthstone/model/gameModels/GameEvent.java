package ap.hearthstone.model.gameModels;

import ap.hearthstone.model.gameModels.cards.Card;
import ap.hearthstone.model.gameModels.notNedded.Power;
import ap.hearthstone.model.gameModels.entities.GameCharacter;

public class GameEvent {

    private Card playedCard;
    private Card summonCard;
    private int damageDealt;
    private int healthRestored;
    private Power powerUsed;
    private GameCharacter characterAffected, characterCaused;

}
