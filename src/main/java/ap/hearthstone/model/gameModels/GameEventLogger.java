package ap.hearthstone.model.gameModels;

import ap.hearthstone.model.gameModels.cards.Card;
import ap.hearthstone.model.gameModels.notNedded.Power;
import ap.hearthstone.model.gameModels.entities.GameCharacter;

public class GameEventLogger {

    // TODO determine what we exactly want from this class.
    private String summonedCardName;
    private boolean isPlayed;
    private int damageDealt;
    private int healthRestored;
    private String abilityActed;
    private String playerName1, playerName2;
    private String characterAffected, characterCaused;

    public GameEventLogger(String playerName1, String playerName2) {
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
    }

    public String logAttack(int damageDealt, String characterAffected, String characterCaused) {
        this.damageDealt = damageDealt;
        this.characterAffected = characterAffected;
        this.characterCaused = characterCaused;
        return String.format("%s dealt %d damage(s) to %s.", characterCaused, damageDealt, characterAffected);
    }

    public GameEventLogger(String abilityActed) {
        this.abilityActed = abilityActed;
    }
}
