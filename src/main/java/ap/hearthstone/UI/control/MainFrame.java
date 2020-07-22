package ap.hearthstone.UI.control;

import ap.hearthstone.UI.api.ViewPanel;
import ap.hearthstone.UI.api.exceptions.NoSuchViewException;
import ap.hearthstone.UI.collectionView.CollectionView;
import ap.hearthstone.UI.gameView.GameView;
import ap.hearthstone.UI.menuView.LoginView;
import ap.hearthstone.UI.menuView.MainMenuView;
import ap.hearthstone.UI.menuView.SignUpView;
import ap.hearthstone.UI.shopView.ShopView;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final Map<String, ViewPanel> viewMap;
    private CollectionView collectionView;

    public MainFrame() {
        super("first frame");
        configFrame();
        viewMap = new HashMap<>();
    }

    private void configFrame() {
        Configs config = ConfigLoader.getInstance().getPanelConfigs();
        int width = config.readInt("mainFrameWidth");
        int height = config.readInt("mainFrameHeight");
        setSize(width, height);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        logger.debug("Main Frame configured.");
    }

    ///////////////////////TEMPORARY PUBLIC!
     void display(String viewName) throws NoSuchViewException {
        if (!viewMap.containsKey(viewName)) {
            throw new NoSuchViewException(viewName);
        }
        setContentPane(viewMap.get(viewName));
        revalidate();
        repaint();
        logger.debug("now the main frame is showing {} view.", viewName);
    }

    // The API for view names is below:
    void initView(String viewName) {
        switch (viewName) {
            case "login":
//                logger.debug("switch statement to init login view.");
                viewMap.put("login", new LoginView());
                break;
            case "sign":
                viewMap.put("sign", new SignUpView());
                break;
            case "main":
//                logger.debug("switch statement to init main menu.");
                viewMap.put("main", new MainMenuView());
                break;
            case "game":
//                logger.debug("switch statement to init game view.");
                viewMap.put("game", new GameView());
                break;
            case "shop":
//                logger.debug("switch statement to init shop view.");
                viewMap.put("shop", new ShopView());
                break;
            case "collection":
//                logger.debug("switch statement to init collection view.");
                collectionView = new CollectionView();
                viewMap.put("collection", collectionView);
                break;
        }
    }

    public Map<String, ViewPanel> getViewMap() {
        return viewMap;
    }

    public CollectionView getCollectionView() {
        return collectionView;
    }

    public void showDialogue(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void exit() {
        final JOptionPane optionPane = new JOptionPane(
                "The only way to close this dialog is by\n"
                        + "pressing one of the following buttons.\n"
                        + "Do you understand?",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION);
        int doExit = JOptionPane.showOptionDialog(this,
                "Exit The Game?", "Close The Game",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, null, null);
        if (doExit == JOptionPane.OK_OPTION) {
            doExit();
        }
    }

    private void doExit() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
