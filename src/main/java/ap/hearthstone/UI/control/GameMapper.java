package ap.hearthstone.UI.control;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.SimpleMapper;

public class GameMapper extends SimpleMapper {


    @Override
    protected void executeRequests() {
        while (requestList.size() > 0) {
            Request currentRequest = requestList.remove(0);
            switch (currentRequest.getTitle()) {
                case "passive":
                    sendPassives();
                case "exit": //this is meaningless in Login Window
                    exit();
                    break;
                default:
                    break;
            }
        }
    }

    private void sendPassives() {

    }
}
