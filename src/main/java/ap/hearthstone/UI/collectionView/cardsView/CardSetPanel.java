package ap.hearthstone.UI.collectionView.cardsView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.UI.listeners.ClickListener;
import ap.hearthstone.interfaces.RequestSender;
import ap.hearthstone.logging.MainLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class CardSetPanel extends UpdatingPanel {

    private final List<String> allCards;
    private List<String> playerCards;
    private Map<String, Integer> cardsNumber;
    private final Map<String, CardView> cardViewMap;
    Logger logger = LogManager.getLogger(this.getClass());

    public CardSetPanel(String panelName, List<String> allCards) {
        setName(panelName);
        this.allCards = allCards;
        cardViewMap = new HashMap<>();
    }

    public CardSetPanel(String panelName, List<String> allCards, List<String> playerCards) {
        this(panelName, allCards);
        this.playerCards = playerCards;
        cardsNumber = new HashMap<>();
        initCardsNumber();
    }

    public void initCardsNumber() {
        allCards.forEach(card -> cardsNumber.put(card, count(card, playerCards)));
    }

    private int count(String card, List<String> cards) {
        return cards.stream().filter(c -> c.equals(card)).mapToInt(c -> 1).sum();
    }

    // TODO : make cards clickable:
    @Override
    protected void organize() {
        setLayout(new GridLayout(0, 3));
        if (cardsNumber != null) {
            cardsNumber.forEach(this::addCard);
        }
    }

    private void addCard(String cardName, int number) {
        cardViewMap.put(cardName, new CardView(cardName, number));
        add(cardViewMap.get(cardName));
        refresh();
    }

    @Override
    protected void addListeners() {
        cardViewMap.forEach((cardName, label) -> label.addMouseListener(new ClickListener(requestSender,
                cardName, "collection", new Request("collectionClick", cardName))));
    }

    @Override
    protected void executeResponses() {
        while (requestList.size() > 0) {
            Request request = requestList.remove(0);
            logger.debug("Received response: {}.", request.getTitle());
            switch (request.getTitle()) {
                case "OKCancel":
                    OKCancel(request.getRequestBody());
                    break;

            }
        }
    }

    private void OKCancel(String[] requestBody) {
        String ID = requestBody[0];
        String header = requestBody[1];
        String message = requestBody[2];
        int n = JOptionPane.showConfirmDialog(this, message,
                header, JOptionPane.OK_CANCEL_OPTION);
        if (n == JOptionPane.OK_OPTION) {
            requestSender.send(new Request("OK", ID, header));
        }
    }




}
