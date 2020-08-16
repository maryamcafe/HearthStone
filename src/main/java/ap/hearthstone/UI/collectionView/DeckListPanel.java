package ap.hearthstone.UI.collectionView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.UI.util.ImageLoader;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DeckListPanel extends UpdatingPanel {

    private JScrollPane scrollPane;
    private JPanel listPanel;
    private final Map<String, String> deckMap;
    private final Map<String, JLabel> deckViewMap;
    private JButton addDeckButton;

    public DeckListPanel(Map<String, String> deckMap) {
        this.deckMap = deckMap;
        deckViewMap = new HashMap<>();
        organize();
    }

    public DeckListPanel() {
        this(new HashMap<>());
    }

    protected void organize() {
        this.setOpaque(true);
        Configs configs = ConfigLoader.getInstance().getPanelConfigs();
        Dimension dim = new Dimension(configs.readInt("DeckListWidth"), configs.readInt("DeckListHeight"));
        setPreferredSize(dim);

        listPanel = new JPanel(new GridLayout(0,1));
        addDeckButton = new JButton("ADD NEW DECK");
        listPanel.add(addDeckButton);

        scrollPane = new JScrollPane(listPanel);
        add(scrollPane);
        deckMap.forEach(this::addDeck);
        refresh();
    }

    public void getData(){

    }

    public void addDeck(String deckName, String heroName){
        JLabel label = new JLabel(deckName, ImageLoader.getDeckIcon(heroName), SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        deckViewMap.put(deckName, label);
        listPanel.add(deckViewMap.get(deckName));
        listPanel.repaint();
        scrollPane.repaint();
        refresh();
    }

    @Override
    protected void addListeners() {
        addDeckButton.addActionListener(e-> requestSender.send(new Request("addDeck")));
    }

    @Override
    protected void executeResponses() {
        while (requestList.size() > 0) {
            Request request = requestList.remove(0);
            logger.debug("Received response: {}.", request.getTitle());
            switch (request.getTitle()) {
                case "addDeck":
                    showDeckInitiating(request.getRequestBody()[0], request.getRequestBody()[1]);
                    break;
            }
        }

    }

    private void showDeckInitiating(String s, String s1) {

    }


}
