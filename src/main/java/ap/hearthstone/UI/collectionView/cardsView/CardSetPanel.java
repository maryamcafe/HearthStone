package ap.hearthstone.UI.collectionView.cardsView;

import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.UI.listeners.CardMouseClickListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CardSetPanel extends UpdatingPanel {

    private final List<String> cardsNames;
    private final Map<String, CardView> cardsMap;
    Logger logger = LogManager.getLogger(this.getClass());

    public CardSetPanel(String panelName, List<String> cardsNames) {
        this.cardsNames = cardsNames;
        cardsMap = new HashMap<>();
        setName(panelName);
        organize();
    }

    // TODO : make cards it clickable? by making each a panel. we should also use gridLayoutManager (or GridBagLayoutManager)

    private void organize() {
        setLayout(new GridLayout(0, 3));
        if (cardsNames != null) {
            cardsNames.forEach(this::addCard);
        }
    }

    public void addCardSet(Set<String> cards) {
        cards.forEach(this::addCard);
    }

    private void addCard(String cardName) {
        cardsMap.put(cardName, new CardView(cardName));
        add(cardsMap.get(cardName));
        refresh();
        addListeners();
//        logger.debug("Card {} was added", cardName);
    }

    @Override
    protected void addListeners() {
        cardsMap.forEach((cardName, label) -> label.addMouseListener(new CardMouseClickListener(cardName, requestSender)));
    }

    @Override
    protected void executeResponses() {

    }


}
