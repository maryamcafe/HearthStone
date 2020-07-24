package ap.hearthstone.logic.game;

import ap.hearthstone.logic.exceptions.NotEnoughManaException;
import ap.hearthstone.model.gameModels.GameState;
import ap.hearthstone.model.gameModels.Player;
import ap.hearthstone.model.gameModels.cards.Card;
import ap.hearthstone.model.gameModels.entities.GameCharacter;
import ap.hearthstone.model.gameModels.entities.Minion;
import ap.hearthstone.model.gameModels.exceptions.IllegalSummonException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameManager {

    GameState gameState;
    private final Player friend;
    private final Player enemy;
    private Player player; //current player
    private PlayVisitor playVisitor;
    private boolean isFriend;

    private String[] passives;

    int friendlyTurn, enemyTurn;

    Logger logger = LogManager.getLogger(this.getClass());

    public GameManager(GameState gameState) {
        this.gameState = gameState;
        friend = gameState.getFriend();
        enemy = gameState.getEnemy();
        friendlyTurn = 0;
        enemyTurn = 0;
        playVisitor = new PlayVisitor(this);
        passives = gameState.getPassives();
    }

    public void playTurn(boolean isFriendly) {
        if (isFriendly) {
            player = friend;
        } else {
            player = enemy;
        }
        player.playTurn();
    }


    void playCard(Card card) throws NotEnoughManaException {
        if (card.getMana() < player.getAvailableMana()) {
            throw new NotEnoughManaException(card.getMana(), player.getAvailableMana());
        }
        card.play(playVisitor);
    }


    void play(Minion minion) {
//        minion.
        try {
            player.addToPlayedMinions(minion);
            gameState.addToPlayedMinions(minion);
        } catch (IllegalSummonException e) {
            e.printStackTrace();
        }
    }

    void summon(Minion minion) {
        try {
            player.addToSummonedMinions(minion);
            gameState.addToSummonedMinions(minion);
        } catch (IllegalSummonException e) {
            e.printStackTrace();
        }
    }

    public void attack(GameCharacter attacking, GameCharacter target) {
        if (attacking.getAttacksAllowed() > 0) {
            attacking.attack(target);
        }
    }

    public String[] showInfoPassives() {
        Random random = new Random(System.nanoTime());
        List<Integer> order = Arrays.asList(0, 1, 2, 3, 4);
        String[] result = new String[3];
        for (int i = 0; i < result.length; i++) {
            result[i] = passives[order.remove(random.nextInt(order.size()))];
        }
        return result;
    }

    public void setInfoPassive() {

    }


}
