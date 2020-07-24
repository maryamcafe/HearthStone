package ap.hearthstone.logic.game;

import ap.hearthstone.logic.game.GameManager;
import ap.hearthstone.model.gameModels.cards.MinionCard;
import ap.hearthstone.model.gameModels.cards.QuestCard;
import ap.hearthstone.model.gameModels.cards.SpellCard;
import ap.hearthstone.model.gameModels.cards.WeaponCard;
import ap.hearthstone.model.gameModels.entities.Minion;
import ap.hearthstone.model.gameModels.entities.Weapon;
import ap.hearthstone.model.gameModels.exceptions.IllegalSummonException;
import ap.hearthstone.model.gameModels.heros.HeroPower;

public class PlayVisitor {

    private GameManager gameManager;

    public PlayVisitor(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void playSpellCard(SpellCard spellCard) {

    }

    public void playMinionCard(MinionCard minionCard) {
        //create a minion
        Minion minion = new Minion(minionCard);
        gameManager.play(minion);
    }

    public void playWeaponCard(WeaponCard weaponCard) {

    }

    public void playHeroPower(HeroPower heroPower) {

    }

    public void playQuestAndReward(QuestCard questCard) {

    }

}
