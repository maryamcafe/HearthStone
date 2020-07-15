package ap.hearthstone.UI.api;

import ap.hearthstone.interfaces.RequestHandler;
import ap.hearthstone.interfaces.RequestSender;
import ap.hearthstone.interfaces.Updatable;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/* This class is created to use the common methods in this project's panels,
such as updating and handling requests.
*/
public abstract class ViewPanel extends JPanel implements RequestHandler, Updatable {

    protected RequestSender requestSender;
    protected List<Request> requestList;

    public ViewPanel(){
        requestList = new LinkedList<>();
    }

    @Override
    public void addRequests(Request request) {
        requestList.add(request);
    }

    @Override
    public void setRequestSender(RequestSender requestSender) {
        this.requestSender = requestSender;
    }

    @Override
    public void update() {
        executeResponses();
        repaint();
        revalidate();
    }

    protected abstract void executeResponses();
}
