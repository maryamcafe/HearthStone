package ap.hearthstone.model.gameModels;


import ap.hearthstone.model.gameModels.entities.Minion;
import ap.hearthstone.model.gameModels.exceptions.IllegalSummonException;

import java.util.List;

public class GameState {

    private Player friend;
    private Player enemy;
    private int friendlyTurn;
    private int enemyTurn;
    private String[] passives;

    private List<Minion> summonedMinions;
    private List<Minion> playedMinions;
    private List<Minion> deadMinions;


    public GameState(Player friend, Player enemy){
        this.friend = friend;
        this.enemy = enemy;
        friendlyTurn = 0;
        enemyTurn = 0;
    }

    int[] turns = new int[2];

    public void playFriendlyTurn() {
        friendlyTurn++;
    }

    public void playEnemyTurn() {
        enemyTurn++;
    }

    int getAvailableMana(boolean isFriend) {
        if (isFriend) {
            return friendlyTurn;
        } else {
            return enemyTurn;
        }
    }

    public String[] getPassives() {
        if(passives == null){
            passives = new String[]{"Twice Draw", "Off Cards", "Warriors", "Free Power", "Mana Jump"};
        }
        return passives;
    }

    public Player getFriend() {
        return friend;
    }

    public Player getEnemy() {
        return enemy;
    }
    public void addToDeadMinions(Minion minion) {
        deadMinions.add(minion);
    }

    public void addToSummonedMinions(Minion minion) throws IllegalSummonException {
        summonedMinions.add(minion);
    }

    public void addToPlayedMinions(Minion minion) throws IllegalSummonException {
        addToSummonedMinions(minion);
        playedMinions.add(minion);
    }


}
