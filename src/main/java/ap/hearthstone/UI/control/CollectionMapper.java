package ap.hearthstone.UI.control;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.logic.game.CardFileManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class CollectionMapper extends ap.hearthstone.UI.api.SimpleMapper {

    private final CardFileManager cardFileManager;
    private final Type stringToStringListMap;
    private final Gson gson;

    public CollectionMapper() {
        cardFileManager = new CardFileManager();
        gson = new Gson();
        stringToStringListMap = new TypeToken<Map<String, List<String>>>() {
        }.getType();
    }


    @Override
    protected void executeRequests() {
        if (requestList.size() > 0) {
            Request currentRequest = requestList.remove(0);
            switch (currentRequest.getTitle()) {
                case "init":
                    sendCardData();
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
}
