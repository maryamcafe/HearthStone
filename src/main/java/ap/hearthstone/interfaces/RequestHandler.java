package ap.hearthstone.interfaces;


import ap.hearthstone.UI.api.Request;

public interface RequestHandler {
    void addRequests(Request request);
    void setRequestSender(RequestSender requestSender);
}
