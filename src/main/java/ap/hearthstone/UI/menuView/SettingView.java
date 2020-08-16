package ap.hearthstone.UI.menuView;

import ap.hearthstone.UI.api.Request;

import javax.swing.*;
import java.awt.*;

public class SettingView extends MenuView {

    public SettingView(){
        super("b:DELETE USER", "b:back");
    }

    @Override
    protected void organize() {
        organizeLabel(new JLabel(""), 0, 2);
        organizeButton(buttonMap.get("delete user"), 1, 1);
        organizeButton(buttonMap.get("back"), 2, 1);
        organizeLabel(new JLabel(""), 3, 2);

    }


    @Override
    protected void addListeners() {
        buttonMap.get("delete user").addActionListener(e ->
                requestSender.send(new Request("deleteUser")));
        buttonMap.get("back").addActionListener(e ->
                requestSender.send(new Request("back")));
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

    @Override
    protected void organizeButton(JButton button, int row, double weighty) {
        gc.weightx = 1;
        gc.weighty = weighty;

        gc.gridx = 2;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.CENTER;
        add(button, gc);
    }

    @Override
    protected void organizeLabel(JLabel label, int row, double weighty) {
        gc.weightx = 1;
        gc.weighty = weighty;

        gc.gridx = 2;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.CENTER;
        add(label, gc);
    }
}
