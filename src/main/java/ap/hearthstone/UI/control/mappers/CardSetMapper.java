package ap.hearthstone.UI.control.mappers;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.logic.cards.CardShop;
import ap.hearthstone.logic.exceptions.NotEnoughMoney;
import ap.hearthstone.logic.users.PlayerManager;
import ap.hearthstone.model.gameModels.exceptions.MaxEachCardException;
import ap.hearthstone.model.gameModels.util.GameConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class CardSetMapper extends ap.hearthstone.UI.api.Mapper {
    private final PlayerManager playerManager;
    private final GameConstants constants;
    private final CardShop cardShop;
    private final Gson gson;
    private final Type numberMapType;

    public CardSetMapper(String username) {
        playerManager = new PlayerManager(username);
        constants = GameConstants.getInstance();
        cardShop = new CardShop(username);
        gson = new Gson();
        numberMapType = new TypeToken<Map<String, Integer>>(){}.getType();
    }

    // Requests
    @Override
    protected void doForRequest(Request request) {
        String[] requestBody = request.getRequestBody();
        logger.debug("{} request received.", request.getTitle());
        switch (request.getTitle()) {
            case "collectionClick":
                collectionClick(requestBody[0]);
                break;
            case "OK":
                collectionOK(requestBody[0], requestBody[1]);
                break;
            case "shop":
                shop();
                break;
            case "buyCard":
                buyCard(requestBody[0]);
                break;
            case "sellCard":
                sellCard(requestBody[0]);
                break;
            default:
                break;
        }
    }

    // Responses
    private void buyCard(String card) {
        try {
            cardShop.buy(card);
            sendNumberUpdate();
        } catch (MaxEachCardException | NotEnoughMoney e) {
            logger.error(e.getMessage(), e);
            responseSender.send(new Request("error", e.getMessage()));
        }
    }

    private void sellCard(String card) {
        cardShop.sell(card);
        sendNumberUpdate();
    }

    private void sendNumberUpdate() {
        responseSender.send(new Request("buySellUpdate", getCardNumbers(), getWalletCoins()) );
    }

    private void collectionClick(String cardClicked) {
        if (playerManager.getPlayer().countCard(cardClicked) >= 2) {
            responseSender.send(new Request("error", constants.getMessage("canNotBuyCard")));
        } else {
            responseSender.send(new Request("OKCancel", cardClicked, "Buy Cards", constants.getMessage("buyAtShop")));
        }
    }

    //TODO write a simple api for this
    private void collectionOK(String ID, String header) {
        if (header.equals("buy Cards")) { // send request directly to collection view
            requestSender.send(new Request("showBuyCard", ID, getCardValue(ID), getWalletCoins()));
        }
    }

    private void shop(){
        logger.debug("shop response was sent.");
        responseSender.send(new Request("shop", getAllCardsValues(), getWalletCoins()));
    }


    // Values from model
    private String getAllCardsValues() {
        return gson.toJson(cardShop.getCardValues(), numberMapType);
    }

    private String getCardValue(String cardName){
        return String.valueOf(cardShop.getCardValues().get(cardName));
    }

    private String getWalletCoins(){
        return String.valueOf(cardShop.getWalletCoins());
    }

    private String getCardNumbers() {
        return gson.toJson(playerManager.getPlayerCardsNumber(), numberMapType);
    }
}
