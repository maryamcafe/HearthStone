package ap.hearthstone.logic.game;

import ap.hearthstone.logic.exceptions.NotEnoughManaException;
import ap.hearthstone.logic.game.visitors.StartOfTurnVisitor;
import ap.hearthstone.model.gameModels.GameState;
import ap.hearthstone.model.gameModels.GamePlayer;
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
    private final GamePlayer friend;
    private final GamePlayer enemy;
    private GamePlayer gamePlayer; //current player
    private PlayVisitor playVisitor;
    private boolean isFriend;

    private String[] passives;
    private StartOfTurnVisitor startOfTurnVisitor;

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
            gamePlayer = friend;
        } else {
            gamePlayer = enemy;
        }
        gamePlayer.playTurn();
        startOfTurnVisitor.doForGameState(gameState);
    }


    void playCard(Card card) throws NotEnoughManaException {
        if (card.getMana() < gamePlayer.getAvailableMana()) {
            throw new NotEnoughManaException(card.getMana(), gamePlayer.getAvailableMana());
        }
        card.play(playVisitor);
    }


    void play(Minion minion) {
        try {
            gamePlayer.addToPlayedMinions(minion);
            gameState.addToPlayedMinions(minion);
        } catch (IllegalSummonException e) {
            e.printStackTrace();
        }
    }

    void summon(Minion minion) {
        try {
            gamePlayer.addToSummonedMinions(minion);
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
