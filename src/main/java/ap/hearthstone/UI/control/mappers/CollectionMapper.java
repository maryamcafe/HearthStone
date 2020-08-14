package ap.hearthstone.UI.control.mappers;

import ap.hearthstone.UI.api.Mapper;
import ap.hearthstone.UI.api.Request;
import ap.hearthstone.logic.cards.CardConstants;
import ap.hearthstone.logic.cards.CardFileManager;
import ap.hearthstone.logic.cards.DeckFileManager;
import ap.hearthstone.logic.users.PlayerFileManager;
import ap.hearthstone.model.gameModels.deck.Deck;
import ap.hearthstone.model.gameModels.exceptions.FullDeckException;
import ap.hearthstone.model.gameModels.exceptions.MaxEachCardException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class CollectionMapper extends Mapper {

    private final CardFileManager cardFileManager;
    private final DeckFileManager deckFileManager;
    private final PlayerFileManager playerFileManager;
    private final Type stringToStringListMap, stringListType;
    private final Gson gson;
    private CardConstants cardConstants;

    public CollectionMapper(String username) {
        cardFileManager = CardFileManager.getInstance();
        deckFileManager = new DeckFileManager(username);
        playerFileManager = new PlayerFileManager(username);
        cardConstants = new CardConstants();
        gson = new Gson();
        stringToStringListMap = new TypeToken<Map<String, List<String>>>() {
        }.getType();
        stringListType = new TypeToken<List<String>>() {
        }.getType();
    }


    //TODO move shared parts to parent class.
    @Override
    protected void doForRequest(Request request) {
        switch (request.getTitle()) {
            case "init":
                sendCardData();
                break;
            case "createDeck":
                createDeck(request.getRequestBody());
                break;
            default:
                break;

        }
    }

    private void sendCardData() {
        String requestBody = gson.toJson(getCardData(), stringToStringListMap);
//        logger.debug(requestBody);
        responseSender.send(new Request("init", requestBody));
    }

    private Map<String, List<String>> getCardData() {
        return cardFileManager.getHeroToCardNameMap();
    }

    private void createDeck(String[] requestBody) {
        String name = requestBody[0];
        String heroName = requestBody[1];
        String json = requestBody[2];
        List<String> cardNames = gson.fromJson(json, stringListType);
        try {
            Deck deck = deckFileManager.createDeck(name, heroName, cardNames);
            playerFileManager.getPlayer().addDeck(deck);
        } catch (FullDeckException | MaxEachCardException e) {
            logger.error(e.getMessage(), e);
            responseSender.send(new Request("error", e.getMessage()));
            responseSender.send(new Request("info", cardConstants.getDeckInitiationGuid()));
        }
    }

}
