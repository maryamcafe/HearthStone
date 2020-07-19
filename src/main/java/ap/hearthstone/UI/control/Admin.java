package ap.hearthstone.UI.control;

import ap.hearthstone.UI.api.*;
import ap.hearthstone.UI.api.exceptions.NoSuchViewException;
import ap.hearthstone.UI.collectionView.CollectionView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.swing.Timer;
import java.util.*;

public class Admin extends SimpleMapper {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private static Admin instance;
    private MainFrame mainFrame;
    private String currentView, previousView;
    private Map<String, String> nextViewMap;
    private Map<String, SimpleMapper> mappers;
    private Map<String, Timer> mapperTimers, viewTimers;
    private CollectionMapper collectionMapper;


    public Admin() {
        super();
        mainFrame = new MainFrame();
        mapperTimers = new HashMap<>();
        viewTimers = new HashMap<>();
        initMappers();
        initSequences();
    }

    private void initMappers() {
        mappers = new HashMap<>();
        mappers.put("login", new LoginMapper());
        mappers.put("sign", new SignUpMapper());
        mappers.put("main", new MainMenuMapper());
        collectionMapper = new CollectionMapper();
        mappers.put("collection", collectionMapper);
        /////////////////////////////to be continued
    }

    private void initSequences() {
        nextViewMap = new HashMap<>();
        nextViewMap.put("login", "main");
        nextViewMap.put("sign", "main");
        nextViewMap.put("collection", "game");
        /////////////////////////////to be continued
    }

    public void start() {
        Timer t = new Timer(15, e -> update()); // update is defined in the parent class: simple Mapper.
        t.start();
        display("login");
        mapperTimers.get("login").start();
        viewTimers.get("login").start();
    }

    // add the panel you want to the mainFrame by it's name (String api)

    public void display(String viewName) {
        currentView = viewName;
        if (!mainFrame.getViewMap().containsKey(viewName)) { //if the panel's request sender was not set.
            logger.debug("there was no {} in viewMap, so we're initializing it.", viewName);
            mainFrame.initView(viewName);
            setTimer(viewName);
            setRequestSender(viewName);
            if("collection".equals(viewName)){
                 mainFrame.getCollectionView().receiveCardsData(collectionMapper.getCardData());
            }
        }
        try {
            mainFrame.display(viewName);
        } catch (NoSuchViewException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void setTimer(String viewName) {
        mapperTimers.put(viewName, new Timer(30, e -> mappers.get(viewName).update()));
        viewTimers.put(viewName, new Timer(30, e ->mainFrame.getViewMap().get(viewName).update()));
    }

    /*
    Sets request sender and response sender for Mapper class and Panel of this view.
     */
    private void setRequestSender(String viewName) {
        mainFrame.getViewMap().get(viewName).
                setRequestSender(mappers.get(viewName)::addRequests);
        mappers.get(viewName).
                setResponseSender(mainFrame.getViewMap().get(viewName)::addRequests);
        mappers.get(viewName).setRequestSender(this::addRequests);
        logger.debug("Request sender is set for " + viewName);
    }

    protected void executeRequests() {
        while (requestList.size() > 0) {
            Request request = requestList.remove(0);
            logger.info("Request {}: {} is being executed.", request.getTitle(),request.getRequestBody());
            switch (request.getTitle()) {
                case "next":
                    next(currentView);
                    break;
                case "switch":
                    switchView(request.getRequestBody()[0]);
                    break;
                case "back":
                    back();
                    break;
                case "exit":
                    exit();
                    break;
            }
        }
    }

    private void next(String currentView) {
        switchView(nextViewMap.get(currentView));
    }

    private void back() {
       switchView(previousView);
    }

    private void switchView(String viewName) {
        mapperTimers.get(currentView).stop();
        viewTimers.get(currentView).stop();

        previousView = currentView;
        currentView = viewName;

        display(currentView);
        mapperTimers.get(currentView).start();
        viewTimers.get(currentView).start();
    }

    private void exit() {
        //first save everything
        // We hopefully can attach an action listener to the EXIT icon on the edge of the window (the X sign).
        mainFrame.exit();
    }


}
