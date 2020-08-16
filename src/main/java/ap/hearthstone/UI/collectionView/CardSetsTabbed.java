package ap.hearthstone.UI.collectionView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.UI.collectionView.cardsView.CardSetPanel;
import ap.hearthstone.UI.util.ImageLoader;
import ap.hearthstone.interfaces.RequestSender;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CardSetsTabbed extends UpdatingPanel {

    private final List<CardSetPanel> panels;
    private final JTabbedPane tabbedPane;
    private JButton backToCollectionButton;
    private JPanel shop;
    private JLabel walletLabel;

    private Map<String, Set<String>> tabs;

    Logger logger = LogManager.getLogger(this.getClass());

    public CardSetsTabbed(List<CardSetPanel> panels) {
        this.panels = panels;
        tabs = new HashMap<>();
        tabbedPane = new JTabbedPane();
        shop = new JPanel(new BorderLayout());
        backToCollectionButton = new JButton("Back To Collection");
        walletLabel = new JLabel();
        refresh();
    }

    public CardSetsTabbed() {
        this(new LinkedList<>());
    }

    @Override
    protected void organize() {
        Configs configs = ConfigLoader.getInstance().getPanelConfigs();
        Dimension panelDim = new Dimension(configs.readInt("cardPanelWidth"), configs.readInt("cardPanelHeight"));
        tabbedPane.setPreferredSize(panelDim);
        panels.forEach(panel -> {
            addTab(panel);
            logger.debug("Tabbed Pane organized");
        });
        add(tabbedPane);
        organizeShop();
        refresh();
    }

    public void initTabs(Map<String, Set<String>> tabs, Map<String, Integer> cardsNumbers) {
        this.tabs.putAll(tabs);
        tabs.forEach((tabName, cardSet) -> {
            CardSetPanel cardSetPanel = new CardSetPanel(tabName, cardSet, cardsNumbers);
            panels.add(cardSetPanel);
            addTab(cardSetPanel);
            cardSetPanel.initView();
        });
        logger.debug("{} tabs initiated", tabs.size());
        refresh();
    }

    public void addTab(CardSetPanel panel) {
        String name = getPanelName(panel);
        ImageIcon icon = ImageLoader.getIcon(name);
        JScrollPane scrollPane = new JScrollPane(panel);
        tabbedPane.addTab("", icon, scrollPane, name);
        panel.setRequestSender(requestSender);
        refresh();
    }

    private String getPanelName(CardSetPanel panel) {
        if (panel.getName() != null)
            return panel.getName();
        return "default";
    }

    private void organizeShop() {
        shop.add(backToCollectionButton, BorderLayout.SOUTH);
        shop.add(tabbedPane, BorderLayout.CENTER);
        walletLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        shop.add(walletLabel, BorderLayout.NORTH);
    }

    @Override
    protected void addListeners() {
        backToCollectionButton.addActionListener(e -> backToCollection());
    }



    @Override
    protected void executeResponses() {
        while (requestList.size() > 0) {
            Request request = requestList.remove(0);
            String[] requestBody = request.getRequestBody();
            logger.debug("Received response: {}.", request.getTitle());
            switch (request.getTitle()) {
                case "OKCancel":
                    OKCancel(requestBody[0], requestBody[1], requestBody[2]);
                    break;
                case "buySellUpdate":
                    buySellUpdate(requestBody[0], requestBody[1]);
                    break;
                case "error":
                    error(requestBody[0]);
                    break;
                case "shop":
                    showShop(requestBody[0], requestBody[1]);
                    break;
                case "showBuyCard":
                    showBuyCard(requestBody[0], requestBody[1], requestBody[2]);
            }
        }
    }

    public void OKCancel(String ID, String header, String message) {
        panels.forEach(panel -> {
            if (tabs.get(panel.getName()).contains(ID)) {
                panel.OKCancel(ID, header, message);
            }
        });
        refresh();
    }


    // Shop
    public void showShop(String cardValuesJson, String walletCoins) {
        removeAll();
        panels.forEach(panel -> panel.showShop(cardValuesJson));
        organizeShop();
        add(shop);
        updateWallet(Integer.parseInt(walletCoins));
        refresh();
    }

    private void buySellUpdate(String numbersJson, String walletCoins) {
        panels.forEach(panel -> panel.updateCardsNumbers(numbersJson));
        updateWallet(Integer.parseInt(walletCoins));
        refresh();
    }

    private void updateWallet(int walletCoins) {
        walletLabel.setText(String.format("You have %d coins in your wallet", walletCoins));
        shop.repaint();
        refresh();
    }

    private void backToCollection() {
        removeAll();
        panels.forEach(CardSetPanel::backToCollection);
        add(tabbedPane);
        refresh();
    }

    private void showBuyCard(String cardName, String cardValue, String walletCoins) {
    }


    @Override
    public void update() {
        super.update();
        panels.forEach(UpdatingPanel::update);
        refresh();
    }

    public List<CardSetPanel> getPanels() {
        return new LinkedList<>(panels);
    }

    public JPanel getShop() {
        return shop;
    }
}
