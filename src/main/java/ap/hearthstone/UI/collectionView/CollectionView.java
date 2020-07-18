package ap.hearthstone.UI.collectionView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.ViewPanel;
import ap.hearthstone.UI.collectionView.cardsView.CardSetPanel;
import ap.hearthstone.UI.collectionView.cardsView.CardSetsTabbed;
import ap.hearthstone.UI.collectionView.cardsView.FilterPanel;
import ap.hearthstone.UI.collectionView.decksView.AddDeckPanel;
import ap.hearthstone.UI.collectionView.decksView.DeckListPanel;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.Map;

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
    private final Configs configs = ConfigLoader.getInstance().getPanelConfigs();
    private final Type dataType;
    private final Gson gson;


    public CollectionView() {
        super();
        cardSetsTabbed = new CardSetsTabbed();
        requestSender.send(new Request("cardsData"));

        deckList = new DeckListPanel();
        requestSender.send(new Request("decksData"));

        filters = new FilterPanel();
        addDeckPanel = new AddDeckPanel();
        integrating = new JPanel();
        organize();

        gson = new Gson();
        dataType = new TypeToken<Map<String, String>>(){}.getType();
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

    public void display(JPanel panel){
        add(panel, BorderLayout.CENTER);
    }

    public void notDisplay(JPanel panel){
        remove(panel);
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void executeResponses() {
        while(requestList.size()>0){
            Request request = requestList.remove(0);
            switch (request.getTitle()){
                case "cardsData":
                    receiveCardsData(request);
            }
        }
    }

    private void receiveCardsData(Request request) {
        Map<String, String> cardsData = gson.fromJson(request.getRequestBody()[0], dataType);
        cardSetsTabbed.addTab(new CardSetPanel("all", cardsData.keySet()));
        Map<String, LinkedList<String>> tabs;
//        cardsData.forEach((cardName, heroName)-> {
//            cardsData.
//        });
    }

}
