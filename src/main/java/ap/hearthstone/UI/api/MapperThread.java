package ap.hearthstone.UI.api;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.utils.MyWaitNotify;

import java.util.LinkedList;
import java.util.List;

public abstract class MapperThread extends Thread {
    protected final MyWaitNotify requestNotifier;
    protected final List<Request> requestList;


    public MapperThread() {
        requestNotifier = new MyWaitNotify();
        requestList = new LinkedList<>();
    }

    @Override
    public void run() {
        while (true){
            executeRequests();
        }
    }

    protected void addRequests(Request request){
        requestNotifier.doNotify();
        requestList.add(request);
    }

    protected abstract void executeRequests();
}
