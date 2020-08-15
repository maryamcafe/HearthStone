package ap.hearthstone.UI.collectionView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.UpdatingPanel;
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
public class CollectionView extends UpdatingPanel {

    private final CardSetsTabbed cardSetTabs;
    private final DeckListPanel deckList;
    private final FilterPanel filters;
    private final AddDeckPanel addDeckPanel;
    private final JPanel integrating;
    private Gson gson;
    private Logger logger = LogManager.getLogger(this.getClass());

    public CollectionView() {
        super();
        cardSetTabs = new CardSetsTabbed();
        deckList = new DeckListPanel();
        filters = new FilterPanel();
        addDeckPanel = new AddDeckPanel();
        integrating = new JPanel();

        gson = new Gson();
    }

    @Override
    protected void organize() {
        setLayout(new BorderLayout());
        add(deckList, BorderLayout.EAST);

        integrating.setLayout(new BorderLayout());
        integrating.add(cardSetTabs, BorderLayout.NORTH);
        integrating.add(filters, BorderLayout.SOUTH);
        add(integrating, BorderLayout.WEST);

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
        Drawer.drawBackgroundImage("collection", (Graphics2D) g);
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
        super.initView();
        requestSender.send(new Request("initCards"));
        requestSender.send(new Request("initDecks"));
    }

    @Override
    protected void executeResponses() {
        while (requestList.size() > 0) {
            Request request = requestList.remove(0);
            logger.debug("Received response: {}.", request.getTitle());
            switch (request.getTitle()) {
                case "initCards":
                    receiveCards(request.getRequestBody()[0], request.getRequestBody()[1]);
                    break;
                case "initDecks":
                    receiveDecks(request.getRequestBody()[0]);
            }
        }
    }

    // CARDS
    private void receiveCards(String allCardsJson, String playerCardsJson) {
        Type mapType = new TypeToken<Map<String, List<String>>>() {}.getType();
        Type listType = new TypeToken<List<String>>(){}.getType();

        Map<String, List<String>> allCards = gson.fromJson(allCardsJson, mapType);
        List<String> playerCards = gson.fromJson(playerCardsJson, listType);
        logger.debug("all cards size: {} and player cards size:{}", allCards.size(), playerCards.size());
        initCardTabs(allCards, playerCards);
    }

    private void initCardTabs(Map<String, List<String>> tabs, List<String> playerCards) {
        //TODO add a tab for all cards, get players cards in heroToCardMap
        tabs.forEach((tabName, cardSet) -> {
            CardSetPanel cardSetPanel = new CardSetPanel(tabName, cardSet, playerCards);
            cardSetPanel.initView();
            cardSetTabs.addTab(cardSetPanel);
        });
        refresh();
    }

    // DECKS
    private void receiveDecks(String decksJson) {
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> deckToHeroMap = gson.fromJson(decksJson, type);
        initDecks(deckToHeroMap);
    }

    private void initDecks(Map<String, String> deckToHeroMap) {
        deckToHeroMap.forEach(deckList::addDeck);
    }

    @Override
    public void update() {
        super.update();
        cardSetTabs.update();
        filters.update();
        deckList.update();
    }
}
