package ap.hearthstone.UI.control.mappers;

import ap.hearthstone.UI.api.Mapper;
import ap.hearthstone.UI.api.Request;
import ap.hearthstone.logic.cards.CardFileManager;
import ap.hearthstone.logic.cards.DeckManager;
import ap.hearthstone.logic.users.PlayerManager;
import ap.hearthstone.model.gameModels.deck.Deck;
import ap.hearthstone.model.gameModels.exceptions.FullDeckException;
import ap.hearthstone.model.gameModels.exceptions.IllegalHeroClass;
import ap.hearthstone.model.gameModels.exceptions.MaxEachCardException;
import ap.hearthstone.model.gameModels.util.GameConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionMapper extends Mapper {

    private final CardFileManager cardFileManager;
    private final DeckManager deckManager;
    private final PlayerManager playerManager;
    private final Type stringToStringListMap, stringListType;
    private final Gson gson;
    private final GameConstants constants;

    public CollectionMapper(String username) {
        cardFileManager = CardFileManager.getInstance();
        deckManager = new DeckManager(username);
        playerManager = new PlayerManager(username);
        constants = GameConstants.getInstance();
        gson = new Gson();
        stringToStringListMap = new TypeToken<Map<String, Set<String>>>() {}.getType();
        stringListType = new TypeToken<List<String>>() {}.getType();
    }


    //TODO move shared parts to parent class.
    @Override
    protected void doForRequest(Request request) {
        switch (request.getTitle()) {
            case "initCards":
                sendCardData();
                break;
            case "initDecks":
                sendDeckData();
                break;
            case "createDeck":
                createDeck(request.getRequestBody());
                break;
            case "collectionClick":
                collectionClick(request.getRequestBody()[0]);
                break;
            case "OK":
                    collectionOK(request.getRequestBody());
            default:
                break;
        }
    }

    private void sendCardData() {
        String allCards = gson.toJson(cardFileManager.getHeroToCardNameMap(), stringToStringListMap);
        String playerCards = gson.toJson(playerManager.getPlayerCards(), stringListType);
        responseSender.send(new Request("initCards", allCards, playerCards));
    }

    private void sendDeckData() {
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        String decksData = gson.toJson(deckManager.getDeckToHeroMap(), type);
        responseSender.send(new Request("initDecks", decksData));
    }

    private void collectionClick(String cardClicked) {
        String message = playerManager.getPlayer().countCard(cardClicked) >=2 ? constants.getMessage("canBuyCard") :
                constants.getMessage("canNotBuyCard");
        responseSender.send(new Request("OKCancel", cardClicked, "Buy Cards", message));
    }

    private void collectionOK(String[] requestBody) {
        String ID = requestBody[0];
        String header = requestBody[1];
        if(header.equals("buy Cards")){
            responseSender.send(new Request("switch", "shop"));
        }
    }

    private void createDeck(String[] requestBody) {
        String name = requestBody[0];
        String heroName = requestBody[1];
        String json = requestBody[2];
//        boolean isPrivate = "private".equals(requestBody[3]);
        List<String> cardNames = gson.fromJson(json, stringListType);
        try {
            Deck deck = deckManager.createDeck(name, heroName, cardNames);
            playerManager.getPlayer().addDeck(deck);
        } catch (FullDeckException | MaxEachCardException | IllegalHeroClass e) {
            logger.error(e.getMessage(), e);
            responseSender.send(new Request("error", e.getMessage()));
            responseSender.send(new Request("info", constants.getDeckInitiationGuid()));
        }
    }

    //TODO
    private void editDeck(String mode){

    }

}
