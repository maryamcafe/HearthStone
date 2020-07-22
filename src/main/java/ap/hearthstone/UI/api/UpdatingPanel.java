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
public abstract class UpdatingPanel extends JPanel implements RequestHandler, Updatable {

    protected RequestSender requestSender;
    protected List<Request> requestList;

    public UpdatingPanel(){
        requestList = new LinkedList<>();
    }


    protected abstract void addListeners();

    @Override
    public void addRequests(Request request) {
        requestList.add(request);
    }

    @Override
    public void setRequestSender(RequestSender requestSender) {
        this.requestSender = requestSender;
    }

    /*
    Responses to the sent requests are received here,
     */
    protected abstract void executeResponses();

    @Override
    public void update() {
        executeResponses();
        refresh();
    }

    public void refresh(){
        repaint();
        revalidate();
    }

}
