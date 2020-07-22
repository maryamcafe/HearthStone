package ap.hearthstone.UI.collectionView.decksView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.UI.api.ViewPanel;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class DeckListPanel extends UpdatingPanel {

    private Map<String, String> deckMap;
    private JButton addDeck;

    public DeckListPanel() {
        addDeck = new JButton("ADD NEW DECK");
        organize();
    }

    private void organize() {
        Configs configs = ConfigLoader.getInstance().getPanelConfigs();
        Dimension dim = new Dimension(configs.readInt("DeckListWidth"), configs.readInt("DeckListHeight"));
        setSize(dim);
        setLayout(new GridLayout(0,1));
    }

    public void getData(){

    }

    public void addDeck(String DeckName, String HeroName){

    }

    @Override
    protected void addListeners() {
        addDeck.addActionListener(e-> requestSender.send(new Request("addDeck")));
    }

    @Override
    protected void executeResponses() {

    }



}
