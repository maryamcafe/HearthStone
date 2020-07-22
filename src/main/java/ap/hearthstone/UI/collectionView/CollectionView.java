package ap.hearthstone.UI.collectionView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.ViewPanel;
import ap.hearthstone.UI.collectionView.cardsView.CardSetPanel;
import ap.hearthstone.UI.collectionView.cardsView.CardSetsTabbed;
import ap.hearthstone.UI.collectionView.cardsView.FilterPanel;
import ap.hearthstone.UI.collectionView.decksView.AddDeckPanel;
import ap.hearthstone.UI.collectionView.decksView.DeckListPanel;
import ap.hearthstone.UI.util.Drawer;
import ap.hearthstone.interfaces.RequestSender;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;

/*
The integrator and controller class for the collection view.
SubPanels are directly connected to the data.
 */
public class CollectionView extends ViewPanel {

    private final CardSetsTabbed cardSetTabs;
    private final DeckListPanel deckList;
    private final FilterPanel filters;
    private final AddDeckPanel addDeckPanel;
    private final JPanel integrating;
    private int timesLoaded;
    private Logger logger = LogManager.getLogger(this.getClass());

    public CollectionView() {
        super();
        cardSetTabs = new CardSetsTabbed();
        deckList = new DeckListPanel();
        filters = new FilterPanel();
        addDeckPanel = new AddDeckPanel();
        integrating = new JPanel();

        timesLoaded = 0;
        organize();

        addListeners();
    }

    private void organize() {

        setLayout(new BorderLayout());
        add(deckList, BorderLayout.EAST);

        integrating.setLayout(new BorderLayout());
        integrating.add(cardSetTabs, BorderLayout.NORTH);
        integrating.add(filters, BorderLayout.SOUTH);
        add(integrating, BorderLayout.WEST);

//        add(cardSetsTabbed, BorderLayout.NORTH);
//        add(filters, BorderLayout.SOUTH);
//        add(deckList, BorderLayout.EAST);
        repaint();
        revalidate();
    }

    public void display(JPanel panel) {
        add(panel, BorderLayout.CENTER);
    }

    public void notDisplay(JPanel panel) {
        remove(panel);
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        if (timesLoaded == 0) {
        Drawer.drawBackgroundImage("collection", (Graphics2D) g);
//        }
//        timesLoaded++;
    }

    @Override
    public void setRequestSender(RequestSender requestSender) {
        super.setRequestSender(requestSender);
        deckList.setRequestSender(requestSender);
        filters.setRequestSender(requestSender);
        cardSetTabs.setRequestSender(requestSender);
    }

    @Override
    protected void addListeners() {

    }

    @Override
    public void initView() {
        requestSender.send(new Request("init"));
    }

    @Override
    protected void executeResponses() {
        while (requestList.size() > 0) {
            Request request = requestList.remove(0);
            logger.debug("Received response: {}.", request.getTitle());
            switch (request.getTitle()) {
                case "init":
                    receiveCardsData(request.getRequestBody()[0]);
            }
        }
    }

    private void receiveCardsData(String json) {
        Type mapType = new TypeToken<Map<String, List<String>>>() {
        }.getType();
        Map<String, List<String>> cardsMap = new Gson().fromJson(json, mapType);
        logger.debug("cardsMap size: {}", cardsMap.size());
        initCardTabs(cardsMap);
    }

    private void initCardTabs(Map<String, List<String>> tabs) {
//        cardSetsTabbed.addTab(new CardSetPanel("all", cardsData.keySet()));
//        Map<String, Set<String>> tabs = new HashMap<>();
//        cardsData.forEach((cardName, heroName) -> {
//            if (!tabs.containsKey(heroName)) {
//                tabs.put(heroName, new HashSet<>());
//            }
//            tabs.get(heroName).add(cardName);
//        });
        tabs.forEach((tabName, cardSet) -> {
            CardSetPanel cardSetPanel = new CardSetPanel(tabName, cardSet);
            cardSetTabs.addTab(cardSetPanel);
        });
        refresh();
    }

    @Override
    public void update() {
        super.update();
        cardSetTabs.update();
        filters.update();
        deckList.update();
    }
}
