package ap.hearthstone.UI.control.mappers;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.Mapper;

public class GameMapper extends Mapper {


    public GameMapper(String username){

    }

    @Override
    protected void doForRequest(Request request) {
            switch (request.getTitle()) {
                case "passive":
                    sendPassives();
                default:
                    break;
        }
    }

    private void sendPassives() {

    }
}
