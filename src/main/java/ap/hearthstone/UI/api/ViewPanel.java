package ap.hearthstone.UI.api;

import ap.hearthstone.interfaces.RequestSender;

import javax.swing.*;

//this class is created to use the common methods in this project's panels
public class ViewPanel extends JPanel {

    protected RequestSender requestSender;

    public void setRequestSender(RequestSender requestSender) {
        this.requestSender = requestSender;
    }

    public RequestSender getRequestSender() {
        return requestSender;
    }
}
