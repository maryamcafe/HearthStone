package ap.hearthstone.UI.api;

import ap.hearthstone.interfaces.RequestHandler;
import ap.hearthstone.interfaces.RequestSender;
import ap.hearthstone.interfaces.Updatable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/* This class is created to use the common methods in this project's panels,
such as updating and handling requests.
*/
public abstract class UpdatingPanel extends JPanel implements RequestHandler, Updatable {

    protected RequestSender requestSender;
    protected List<Request> requestList;
    protected Logger logger;

    public UpdatingPanel(){
        requestList = new LinkedList<>();
        logger = LogManager.getLogger(this.getClass());
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

    /*
The program waits in info and error dialogues for the user's click on "OK" button,
 and then moves to the next view.
 Also the logging happens here, not in the request sending object.
 */
    protected void info(String message) {
        logger.info(message);
        JOptionPane.showMessageDialog(this, message);
    }

    protected void error(String message) {
        logger.error(message);
        JOptionPane.showMessageDialog(this,
                message,
                "Error in Login",
                JOptionPane.ERROR_MESSAGE);
    }

}
