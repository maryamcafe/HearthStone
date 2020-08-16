package ap.hearthstone.UI.collectionView.cardsView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.UI.listeners.ClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CardSetPanel extends UpdatingPanel {

    private final List<String> allCards;
    private List<String> playerCards;
    private Map<String, Integer> cardsNumber;
    private Map<String, Integer> cardValues;
    private final Map<String, CardView> cardViewMap;
    private Map<String, CardView> buyCardViewMap;
    private JLabel walletLabel;
    private Gson gson;
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
        cardValues = new HashMap<>();
        buyCardViewMap = new HashMap<>();
        walletLabel = new JLabel();
        gson = new Gson();
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
        this.setOpaque(true);
        walletLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    }

    private void addCard(String cardName, int number) {
        cardViewMap.put(cardName, new CardView(cardName, number));
        add(cardViewMap.get(cardName));
        refresh();
    }

    @Override
    protected void addListeners() {
        cardViewMap.forEach((cardName, cardView) -> cardView.getLabel().addMouseListener(new ClickListener(requestSender,
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
                case "buySellUpdate":

                case "error":
                    error(request.getRequestBody()[0]);
                    break;
            }
        }
    }

    public void showShop(String cardValuesJson, int walletCoins) {
        initCardValues(cardValuesJson);
        cardViewMap.forEach((cardName, cardView) -> remove(cardView));
        add(walletLabel);
        updateWallet(walletCoins);
        getBuyCardViewMap().forEach((card, cardView) -> add(cardView));
        addBuyListener();
    }

    private void updateWallet(int walletCoins) {
        walletLabel.setText(String.format("You have %d coins in your wallet", walletCoins));
    }

    private Map<String, CardView> getBuyCardViewMap() {
        if(buyCardViewMap.size()==0){
            cardViewMap.keySet().forEach(card ->
                    buyCardViewMap.put(card, new CardView(card, cardsNumber.get(card), cardValues.get(card))));
        }
        return buyCardViewMap;
    }

    private void addBuyListener() {
        buyCardViewMap.forEach((card, cardView) -> cardView.getBuyButton().addActionListener(e ->
                requestSender.send(new Request("buyCard", card))));
    }

    private void initCardValues(String json) {
        if (cardValues.size() == 0) {
            cardValues.putAll(gson.fromJson(json, new TypeToken<Map<String, Integer>>() {
            }.getType()));
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
