package ap.hearthstone.UI.api;

import ap.hearthstone.interfaces.Updatable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public abstract class SimpleMapper implements Updatable {

    protected Logger logger = LogManager.getLogger(this.getClass());
    protected final List<Request> requestList;

    public SimpleMapper(){
        requestList = new LinkedList<>();
    }

    @Override
    public void update() {
        executeRequests();
    }

    protected abstract void executeRequests();

    public void addRequests(Request request) {
        requestList.add(request);
    }

}
