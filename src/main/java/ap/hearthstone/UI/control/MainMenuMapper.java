package ap.hearthstone.UI.control;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.SimpleMapper;

public class MainMenuMapper extends SimpleMapper {

    @Override
    protected void executeRequests() {
        while (requestList.size()>0){
            Request request = requestList.remove(0);
            System.out.println(request.getTitle());
            requestSender.send(new Request("switch",request.getTitle()));
        }
    }



}
