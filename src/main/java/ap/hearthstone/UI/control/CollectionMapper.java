package ap.hearthstone.UI.control;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.logic.cards.CardConstants;
import ap.hearthstone.logic.cards.CardFileManager;
import ap.hearthstone.logic.cards.DeckFileManager;
import ap.hearthstone.model.gameModels.exceptions.FullDeckException;
import ap.hearthstone.model.gameModels.exceptions.MaxEachCardException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class CollectionMapper extends ap.hearthstone.UI.api.SimpleMapper {

    private final CardFileManager cardFileManager;
    private final DeckFileManager deckFileManager;
    private final Type stringToStringListMap, stringListType;
    private final Gson gson;
    private CardConstants cardConstants;

    public CollectionMapper() {
        cardFileManager = new CardFileManager();
        deckFileManager = new DeckFileManager();
        cardConstants = new CardConstants();
        gson = new Gson();
        stringToStringListMap = new TypeToken<Map<String, List<String>>>() {
        }.getType();
        stringListType = new TypeToken<List<String>>(){}.getType();
    }


    @Override
    protected void executeRequests() {
        if (requestList.size() > 0) {
            Request request = requestList.remove(0);
            switch (request.getTitle()) {
                case "init":
                    sendCardData();
                    break;
                case "createDeck":
                    createDeck(request.getRequestBody());
                case "exit": //this is meaningless in Login Window
                    exit();
                    break;
                default:
                    break;
            }
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

    private void createDeck(String[] requestBody){
        String name = requestBody[0];
        String heroName = requestBody[1];
        String json = requestBody[2];
        List<String> cardNames = gson.fromJson(json, stringListType);
        try {
            deckFileManager.createDeck(name, heroName, cardNames);
        } catch (FullDeckException e) {
            logger.error(e.getMessage(), e);
            responseSender.send(new Request("error", e.getMessage()));
            responseSender.send(new Request("info", cardConstants.getDeckInitiationGuid()));
        } catch (MaxEachCardException e) {
            logger.error(e.getMessage(), e);
            responseSender.send(new Request("error", e.getMessage()));
        }
    }
}
