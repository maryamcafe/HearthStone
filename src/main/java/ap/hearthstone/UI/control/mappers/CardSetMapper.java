package ap.hearthstone.UI.control.mappers;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.logic.cards.CardShop;
import ap.hearthstone.logic.users.PlayerManager;
import ap.hearthstone.model.gameModels.util.GameConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class CardSetMapper extends ap.hearthstone.UI.api.Mapper {
    private final PlayerManager playerManager;
    private final GameConstants constants;
    private final CardShop cardShop;
    private Gson gson;

    public CardSetMapper(String username) {
        playerManager = new PlayerManager(username);
        constants = GameConstants.getInstance();
        cardShop = new CardShop(username);
        gson = new Gson();
    }

    @Override
    protected void doForRequest(Request request) {
        switch (request.getTitle()) {
            case "collectionClick":
                collectionClick(request.getRequestBody()[0]);
                break;
            case "OK":
                collectionOK(request.getRequestBody());
                break;
            default:
                break;
        }
    }

    private void collectionClick(String cardClicked) {
        if (playerManager.getPlayer().countCard(cardClicked) >= 2) {
            responseSender.send(new Request("error", constants.getMessage("canNotBuyCard")));
        } else {
            responseSender.send(new Request("OKCancel", cardClicked, "Buy Cards", constants.getMessage("buyAtShop")));
        }
    }

    private void collectionOK(String[] requestBody) {
        String ID = requestBody[0];
        String header = requestBody[1];
        if (header.equals("buy Cards")) {
            responseSender.send(new Request("showShop", ID, getCardValues()));
        }
    }

    private String getCardValues() {
        return gson.toJson(cardShop.getCardValues(), new TypeToken<Map<String, Integer>>(){}.getType());
    }

}
