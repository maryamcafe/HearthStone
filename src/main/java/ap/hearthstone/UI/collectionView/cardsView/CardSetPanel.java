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
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;

public class CardSetPanel extends UpdatingPanel {

    private final Set<String> allCards;
    private Map<String, Integer> cardsNumbers;
    private Map<String, Integer> cardValues;
    private final Map<String, CardView> cardViewMap;
    private Map<String, CardView> buyCardViewMap;

    private Gson gson;
    private Type numberMapType;

    Logger logger = LogManager.getLogger(this.getClass());

    public CardSetPanel(String panelName, Set<String> allCards) {
        setName(panelName);
        this.allCards = allCards;
        cardViewMap = new HashMap<>();
    }

    public CardSetPanel(String panelName, Set<String> allCards, Map<String, Integer> cardsNumbers) {
        this(panelName, allCards);
        this.cardsNumbers = cardsNumbers;
        cardValues = new HashMap<>();
        buyCardViewMap = new HashMap<>();
        gson = new Gson();
        numberMapType = new TypeToken<Map<String, Integer>>(){}.getType();
    }


    // Organize
    @Override
    protected void organize() {
        setLayout(new GridLayout(0, 3));
        if (allCards != null) {
            allCards.forEach(card -> addCard(card, cardsNumbers.get(card)));
        }
        this.setOpaque(true);
    }

    private void addCard(String cardName, int number) {
        cardViewMap.put(cardName, new CardView(cardName, number));
        add(cardViewMap.get(cardName));
        refresh();
    }

    @Override
    protected void addListeners() {
        cardViewMap.forEach((cardName, cardView) -> cardView.getLabel().
                addMouseListener(new ClickListener(requestSender,
                        cardName,
                        "collection",
                        new Request("collectionClick", cardName))));
    }

    @Override
    protected void executeResponses() {
        // All the the responses are received in the containing panel, CardSetsTabbed.
    }

    public void OKCancel(String ID, String header, String message) {
        int n = JOptionPane.showConfirmDialog(this, message,
                header, JOptionPane.OK_CANCEL_OPTION);
        if (n == JOptionPane.OK_OPTION) {
            requestSender.send(new Request("OK", ID, header));
        }
    }

    // SHOP
    public void showShop(String cardValuesJson) {
        initCardValues(cardValuesJson);
        removeAll();
        getBuyCardViewMap().forEach((card, cardView) -> add(cardView));
        refresh();
        addBuyListener();
    }


    private void initCardValues(String json) {
        if (cardValues.size() == 0) {
            cardValues.putAll(gson.fromJson(json, new TypeToken<Map<String, Integer>>() {
            }.getType()));
        }
    }

    private Map<String, CardView> getBuyCardViewMap() {
        if (buyCardViewMap.size() == 0) {
            cardViewMap.keySet().forEach(card ->
                    buyCardViewMap.put(card, new CardView(card, cardsNumbers.get(card), cardValues.get(card))));
        }
        return buyCardViewMap;
    }

    private void addBuyListener() {
        buyCardViewMap.forEach((card, cardView) -> cardView.getBuyButton().addActionListener(e ->
                requestSender.send(new Request("buyCard", card))));
        buyCardViewMap.forEach((card, cardView) -> cardView.getSellButton().addActionListener(e ->
                requestSender.send(new Request("sellCard", card))));
    }

    // Update numbers
    public void updateCardsNumbers(String json){       // card numbers: How many of each cards player has
        cardsNumbers = gson.fromJson(json, numberMapType);
        updateViews(cardViewMap);
        updateViews(buyCardViewMap);
    }

    private void updateViews(Map<String, CardView> map){
        map.forEach((card, cardView) -> {
            cardView.updateLabel(cardsNumbers.get(card));
            logger.debug("updated {} card number to {}", card, cardsNumbers.get(card));
        });
    }


    public void backToCollection() {
        this.removeAll();
        cardViewMap.forEach((cardName, cardView) -> add(cardView));
    }


}
