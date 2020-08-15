package ap.hearthstone.UI.control.mappers;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.Mapper;

public class MainMenuMapper extends Mapper {

    @Override
    protected void doForRequest(Request request) {
        if (request.getTitle().equals("play")) {
            requestSender.send(new Request("switch", "game"));
        } else {
            requestSender.send(new Request("switch", request.getTitle()));
        }
    }

}
