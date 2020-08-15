package ap.hearthstone.UI.control;

import ap.hearthstone.UI.api.*;
import ap.hearthstone.UI.api.exceptions.NoSuchViewException;
import ap.hearthstone.UI.control.mappers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.swing.Timer;
import java.util.*;

public class DisplayAdmin extends SimpleMapper {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private static DisplayAdmin instance;
    private final MainFrame mainFrame;
    private String currentView, previousView;
    private Map<String, String> nextViewMap;
    private Map<String, Mapper> mappers;
    private final Map<String, Timer> mapperTimers;
    private final Map<String, Timer> viewTimers;


    public DisplayAdmin() {
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
        currentView = "login";
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
            mainFrame.getViewMap().get(viewName).initView(); //load for the first time.
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

    /*    Sets request sender and response sender for the Mapper and the Panel of this view.
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
            logger.info("Request {}:{} is being executed.", request.getTitle(),request.getRequestBody());
            switch (request.getTitle()) {
                case "loadUser":
                    loadUser(request.getRequestBody()[0]);
                case "next":
                    next(currentView);
                    break;
                case "switch":
                    switchView(request.getRequestBody()[0]);
                    break;
                case "back":
                    goBack();
                    break;
                case "exit":
                    doExit();
                    break;
            }
        }
    }

    private void loadUser(String username) {
        mappers.put("collection", new CollectionMapper(username));
        mappers.put("status", new StatusMapper(username));
        mappers.put("game", new GameMapper(username));
        mappers.put("shop", new ShopMapper(username));
        mappers.put("setting", new SettingMapper(username));
    }

    private void next(String currentView) {
        if(nextViewMap.get(currentView) != null){
            logger.debug("next view will be: {}", nextViewMap.get(currentView));
            switchView(nextViewMap.get(currentView));
        }
    }

    private void goBack() {
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

    private void doExit() {
        //TODO save everything, also when exiting on the close window button(needs an action listener maybe).
        mainFrame.exit();
    }


}
