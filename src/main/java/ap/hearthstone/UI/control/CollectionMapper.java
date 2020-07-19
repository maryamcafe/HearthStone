package ap.hearthstone.UI.control;

import ap.hearthstone.logic.game.CardFileManager;

import java.util.Map;

public class CollectionMapper extends ap.hearthstone.UI.api.SimpleMapper {

    CardFileManager cardFileManager;

    public CollectionMapper() {
        cardFileManager = new CardFileManager();
    }

    public Map<String, String> getCardData(){
//        CardFileManager
        return null;
    }


    @Override
    protected void executeRequests() {

    }
}
