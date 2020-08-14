package ap.hearthstone.UI.menuView;

import ap.hearthstone.UI.api.Request;

import javax.swing.*;

public class SettingView extends MenuView {

    public SettingView(){
        super("b:DELETE USER");
    }

    @Override
    protected void organize() {
        organizeLabel(new JLabel(""), 0, 2);
        organizeButton(buttonMap.get("delete user"), 1, 2.5);
    }

    @Override
    protected void addListeners() {
        buttonMap.get("delete user").addActionListener(e ->
                requestSender.send(new Request("deleteUser")));
    }

    @Override
    protected void executeResponses() {
        while(requestList.size()>0){
            Request request = requestList.remove(0);
            if ("info".equals(request.getTitle())) {
                error(request.getRequestBody()[0]);
            }
        }
    }
}
