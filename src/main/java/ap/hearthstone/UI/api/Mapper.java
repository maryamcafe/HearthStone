package ap.hearthstone.UI.api;

import ap.hearthstone.interfaces.RequestHandler;
import ap.hearthstone.interfaces.RequestSender;
import ap.hearthstone.interfaces.ResponseSender;
import ap.hearthstone.interfaces.Updatable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public abstract class Mapper implements Updatable, RequestHandler {

    protected Logger logger = LogManager.getLogger(this.getClass());
    protected final List<Request> requestList;
    protected RequestSender requestSender;
    protected ResponseSender responseSender;

    public Mapper() {
        requestList = new LinkedList<>();
    }

    @Override
    public void update() {
        executeRequests();
    }

    protected void executeRequests(){
        while (requestList.size() > 0) {
            Request request = requestList.remove(0);
            switch (request.getTitle()) {
                case "back":
                    back();
                    break;
                case "exit":
                    exit();
                    break;
                default:
                    doForRequest(request);
                    break;
            }
        }
    }

    protected abstract void doForRequest(Request request);

    @Override
    public void addRequests(Request request) {
        requestList.add(request);
    }

    /*  For a mapper class Request sender sends request to the higher level of logic.*/
    @Override
    public void setRequestSender(RequestSender requestSender) {
        this.requestSender = requestSender;
    }

    /*  For  Response sender, sends messages to the view. */
    public void setResponseSender(ResponseSender responseSender) {
        this.responseSender = responseSender;
    }

    protected void exit() {
        requestSender.send(new Request("exit"));
    }

    protected void back(){
        requestSender.send(new Request("back"));
    }
}
