package ap.hearthstone.UI.api;

import ap.hearthstone.interfaces.RequestSender;
import ap.hearthstone.utils.MyWaitNotify;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class SimpleMapper {
    protected final List<Request> requestList;

    protected RequestSender requestSender;

    public SimpleMapper() {
        requestList = new ArrayList<>();
    }

     public void addRequests(Request request){
        requestList.add(request);
    }

    public void update(){
        while (requestList.size()>0){
            executeRequests();
        }
    }

    protected abstract void executeRequests();

    public void setRequestSender(RequestSender requestSender) {
        this.requestSender = requestSender;
    }
}
