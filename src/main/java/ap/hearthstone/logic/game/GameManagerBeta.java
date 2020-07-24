package ap.hearthstone.logic.game;

import ap.hearthstone.model.gameModels.GameState;
import ap.hearthstone.model.gameModels.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Deprecated
public class GameManagerBeta {
    private GameState gameState;
    private Player friend;
    private Player enemy;
    private Player player; //current player

    private boolean isFriend;

    private int[] turns;

    private Logger logger= LogManager.getLogger(this.getClass());

    public GameManagerBeta(GameState gameState) {
        this.gameState = gameState;
        friend = gameState.getFriend();
        enemy = gameState.getEnemy();
        turns = new int[]{0, 0};
    }


}
