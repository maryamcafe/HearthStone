package ap.hearthstone.UI.collectionView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.ViewPanel;
import ap.hearthstone.UI.collectionView.cardsView.CardSetPanel;
import ap.hearthstone.UI.collectionView.cardsView.CardSetsTabbed;
import ap.hearthstone.UI.collectionView.cardsView.FilterPanel;
import ap.hearthstone.UI.collectionView.decksView.AddDeckPanel;
import ap.hearthstone.UI.collectionView.decksView.DeckListPanel;
import ap.hearthstone.UI.util.Drawer;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/*
The integrator and controller class for the collection view.
SubPanels are directly connected to the data.
 */
public class CollectionView extends ViewPanel {

    private final CardSetsTabbed cardSetsTabbed;
    private final DeckListPanel deckList;
    private final FilterPanel filters;
    private final AddDeckPanel addDeckPanel;
    private final JPanel integrating;
    private int timesLoaded;


    public CollectionView() {
        super();
        cardSetsTabbed = new CardSetsTabbed();
        requestSender.send(new Request("cardsData"));

        deckList = new DeckListPanel();
        requestSender.send(new Request("decksData"));

        filters = new FilterPanel();
        addDeckPanel = new AddDeckPanel();
        integrating = new JPanel();

        timesLoaded = 0;
        organize();
    }

    private void organize() {

        setLayout(new BorderLayout());
        add(deckList, BorderLayout.EAST);

        integrating.setLayout(new BorderLayout());
        integrating.add(cardSetsTabbed, BorderLayout.NORTH);
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
        super.paintComponent(g);
        if (timesLoaded == 0) {
            Drawer.drawBackgroundImage("collection", (Graphics2D) g);
        }
        timesLoaded ++;
    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void executeResponses() {
        while (requestList.size() > 0) {
            Request request = requestList.remove(0);
            switch (request.getTitle()) {
                case "":

            }
        }
    }

    public void receiveCardsData(Map<String, String> cardsData) {
        cardSetsTabbed.addTab(new CardSetPanel("all", cardsData.keySet()));

        Map<String, Set<String>> tabs = new HashMap<>();
        cardsData.forEach((cardName, heroName) -> {
            if (!tabs.containsKey(heroName)) {
                tabs.put(heroName, new HashSet<>());
            }
            tabs.get(heroName).add(cardName);
        });
        tabs.forEach((tabName, cardSet) ->
                cardSetsTabbed.addTab(new CardSetPanel(tabName, cardSet)));
    }

}
