package ap.hearthstone.model.gameModels;

import ap.hearthstone.model.gameModels.cards.Card;
import ap.hearthstone.model.user.Player;

import java.util.List;

public class GameState {
    private Player player1;
    private Player player2;

    int[] turns = new int[2];

    List<Event> events;

    private enum Event {
        SUMMON,
        PLAY
    }
}
