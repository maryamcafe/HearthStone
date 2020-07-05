package ap.hearthstone.UI.control;

import ap.hearthstone.UI.api.ViewPanel;
import ap.hearthstone.UI.collectionView.CollectionView;
import ap.hearthstone.UI.gameView.GameView;
import ap.hearthstone.UI.menuView.LoginPanel;
import ap.hearthstone.UI.menuView.MainMenuPanel;
import ap.hearthstone.UI.menuView.SignUpPanel;
import ap.hearthstone.UI.shopView.ShopView;
import ap.hearthstone.UI.util.PanelConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame{
    private final Logger logger = LogManager.getLogger(this.getClass());

//     Map<String, JPanel> viewNameMap;
    final Map<String, ViewPanel> viewMap;

    public MainFrame(){
        super("first frame");
        configFrame();
        viewMap = new HashMap<>();
    }

    private void configFrame() {
        PanelConfig config = PanelConfig.getInstance();
        int width = config.getMainFrameWidth();
        int height = config.getMainFrameHeight();
        setSize(width, height);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        logger.debug("Main Frame initiated.");
    }

    public void display(String viewName) {
        ViewType viewType = ViewType.valueOf(viewName);
        if (!viewMap.containsKey(viewType.name())) { //lazy evaluation
            logger.debug("Map doesn't contain {} view, So we're initializing it.", viewType.toString());
            initView(viewType);
        }
        setContentPane(viewMap.get(viewType.name()));
//        logger.debug("now the main frame is showing {} view.", viewType.toString());
    }

    // The API for view names is below:
    private void initView(ViewType type) {
        switch (type) {
            case login:
//                logger.debug("switch statement to init login view.");
                viewMap.put(ViewType.login.name(), new LoginPanel());
                break;
            case sign:
                viewMap.put(ViewType.sign.name(), new SignUpPanel());
            case main:
//                logger.debug("switch statement to init main menu.");
                viewMap.put(ViewType.main.name(), new MainMenuPanel());
                break;
            case game:
//                logger.debug("switch statement to init game view.");
                viewMap.put(ViewType.game.name(), new GameView());
                break;
            case shop:
//                logger.debug("switch statement to init shop view.");
                viewMap.put(ViewType.shop.name(), new ShopView());
                break;
            case collection:
//                logger.debug("switch statement to init collection view.");
                viewMap.put(ViewType.collection.name(), new CollectionView());
                break;
        }
    }

    public Map<String, ViewPanel> getViewMap() {
        return viewMap;
    }
}
