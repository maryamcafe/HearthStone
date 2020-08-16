package ap.hearthstone.logic.cards;

import ap.hearthstone.logic.users.PlayerManager;
import ap.hearthstone.model.gameModels.exceptions.MaxEachCardException;
import ap.hearthstone.logic.exceptions.NotEnoughMoney;
import ap.hearthstone.model.gameModels.util.GameConstants;
import ap.hearthstone.model.user.Player;

import java.util.HashMap;
import java.util.Map;

public class CardShop {

    private final Player player;
    private final Map<String, Integer> cardValues;
    private final GameConstants constants;

    public CardShop(String username){
        PlayerManager playerManager = new PlayerManager(username);
        player = playerManager.getPlayer();
        cardValues = new HashMap<>();
        constants = GameConstants.getInstance();
    }

    public void buy(String cardName) throws MaxEachCardException, NotEnoughMoney {
        if(player.getCoins() >= getCardValues().get(cardName)){
            player.addCard(cardName);
            player.addCoins(-getCardValues().get(cardName));
        } else {
            throw new NotEnoughMoney();
        }
    }

    public boolean sell(String cardName){
        return player.removeCard(cardName);
    }

    public Map<String, Integer> getCardValues(){
        if(cardValues.size() == 0){
            CardFileManager.getInstance().getAllCardsMap().forEach((cardName, card) ->
                cardValues.put(cardName, constants.getCardValue(card.getRarity().name())));
        }
        return cardValues;
    }

}
