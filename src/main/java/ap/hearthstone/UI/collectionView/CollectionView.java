package ap.hearthstone.UI.collectionView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.UI.collectionView.cardsView.BuyCardView;
import ap.hearthstone.UI.collectionView.decksView.AddDeckPanel;
import ap.hearthstone.interfaces.RequestSender;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Type;
import java.util.*;

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
    private BuyCardView buyCardView;

    public CollectionView() {
        super();
        cardSetTabs = new CardSetsTabbed();
        cardSetTabs.initView();
        deckList = new DeckListPanel();
        deckList.initView();
        filters = new FilterPanel();
        filters.initView();
        addDeckPanel = new AddDeckPanel();
        buyCardView = new BuyCardView();
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


    @Override
    public void initView() {
        super.initView();
        requestSender.send(new Request("initCards"));
        requestSender.send(new Request("initDecks"));
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        Drawer.drawBackgroundImage("collection", (Graphics2D) g);
//    }

    // Request Handling
    @Override
    public void setRequestSender(RequestSender requestSender) {
        super.setRequestSender(requestSender);
        deckList.setRequestSender(requestSender);
    }

    @Override
    protected void addListeners() {
        //Nothing needed here, all clickable items are in sub-panels.
    }

    @Override
    protected void executeResponses() {
        while (requestList.size() > 0) {
            Request request = requestList.remove(0);
            String[] requestBody = request.getRequestBody();
            logger.debug("Received response: {}.", request.getTitle());
            switch (request.getTitle()) {
                case "initCards":
                    receiveCards(requestBody[0], requestBody[1]);
                    break;
                case "initDecks":
                    receiveDecks(requestBody[0]);
                    break;
                case "back":
                    requestSender.send(new Request("back"));
                    break;
            }
        }
    }


    // CARDS
    private void receiveCards(String allCardsJson, String playerCardsJson) {
        Type nameListMapType = new TypeToken<Map<String, Set<String>>>() {}.getType();
        Type numberMapType = new TypeToken<Map<String, Integer>>(){}.getType();

        Map<String, Set<String>> allCards = gson.fromJson(allCardsJson, nameListMapType);
        Map<String, Integer> cardsNumbers = gson.fromJson(playerCardsJson, numberMapType);
        logger.debug("all cards size: {} and player cards size:{}", allCards.size(), cardsNumbers.size());
        cardSetTabs.initTabs(allCards, cardsNumbers);
        refresh();
    }

    // DECKS
    private void receiveDecks(String decksJson) {
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> deckToHeroMap = gson.fromJson(decksJson, type);
        logger.debug("Deck to Hero map size is: {}", deckToHeroMap.size());
        deckToHeroMap.forEach(deckList::addDeck);
        refresh();
    }


    @Override
    public void update() {
        super.update();
        cardSetTabs.update();
        filters.update();
        deckList.update();
    }

    // Getters
    public CardSetsTabbed getCardSetTabs() {
        return cardSetTabs;
    }

    public DeckListPanel getDeckList() {
        return deckList;
    }

    public FilterPanel getFilters() {
        return filters;
    }
}
