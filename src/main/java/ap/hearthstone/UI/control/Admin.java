package ap.hearthstone.UI.control;

import ap.hearthstone.UI.Listener;
import ap.hearthstone.UI.api.MapperThread;
import ap.hearthstone.UI.api.RequestType;
import ap.hearthstone.UI.api.SimpleMapper;
import ap.hearthstone.interfaces.Updatable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class Admin extends SimpleMapper implements Updatable {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private static Admin instance;
    private MainFrame mainFrame;
    private LoginMapper loginMapper;
    private Map<String, SimpleMapper> mappers;

    public Admin() {
        super();
        mainFrame = new MainFrame();
        initMappers();
//        loginMapper.start();
        display("login");
    }

    public void start() {
        Timer t = new Timer(15, e -> update());
        Timer t2 = new Timer(15, e -> loginMapper.update());
        t.start();
        t2.start();
    }

    private void initMappers() {
        mappers = new HashMap<>();
        loginMapper = new LoginMapper();
        mappers.put("login", loginMapper);
    }

    // add the panel you want to the mainFrame by it's name (String api)
    public void display(String viewName) {
        mainFrame.display(viewName);
        if (mainFrame.getViewMap().get(viewName).getRequestSender() == null) { //if the panel's request sender was not set.
            setRequestSender(viewName);
            logger.debug("{} is set", mappers.get(viewName));
        }
//        logger.debug("{} is been displayed.", viewName);
    }

    private void setRequestSender(String viewName) {
        mainFrame.getViewMap().get(viewName).setRequestSender(mappers.get(viewName)::addRequests);
        mappers.get(viewName).setRequestSender(this::addRequests);
    }

    protected void executeRequests() {
        while (requestList.size() > 0) {
            RequestType currentRequest = requestList.remove(0).getType();
            System.out.println(currentRequest.toString());
        }
    }

}
