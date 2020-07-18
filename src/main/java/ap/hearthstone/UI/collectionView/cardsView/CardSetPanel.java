package ap.hearthstone.UI.collectionView.cardsView;

import ap.hearthstone.UI.api.ViewPanel;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

import java.awt.*;
import java.util.*;

public class CardSetPanel extends ViewPanel {

    private Set<String> cardsNames;
    private Map<String, CardView> cardsViewMap;


    public CardSetPanel(String panelName, Set<String> cardsNames) {
        this.cardsNames = cardsNames;
        cardsViewMap = new HashMap<>();
        setName(panelName);
        organize();
    }

    // TODO : draw cards images on the panel. how to make it clickable? by making each a panel. we should also use gridLayoutManager (or GridBagLayoutManager)

    private void organize() {
        Configs configs = ConfigLoader.getInstance().getPanelConfigs();
        Dimension panelDim = new Dimension(configs.readInt("cardPanelWidth"), configs.readInt("cardPanelHeight"));
        setSize(panelDim);
        setLayout(new GridLayout(0, 3));
        cardsNames.forEach(this::addCard);
        repaint();
        revalidate();
    }

    public void addCards(Map<String, String> cards){
        cards.keySet().forEach(this::addCard);
        repaint();
        revalidate();
    }

    private void addCard(String cardName) {
        cardsViewMap.put(cardName, new CardView(cardName));
        add(cardsViewMap.get(cardName));
    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void executeResponses() {

    }
}
