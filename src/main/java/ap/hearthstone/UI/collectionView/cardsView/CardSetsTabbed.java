package ap.hearthstone.UI.collectionView.cardsView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.ViewPanel;
import ap.hearthstone.UI.util.ImageLoader;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public class CardSetsTabbed extends ViewPanel {

    private final List<CardSetPanel> panels;
    private final JTabbedPane tabbedPane;

    public CardSetsTabbed() {
        this(new LinkedList<>());
    }

    public CardSetsTabbed(List<CardSetPanel> panels) {
        this.panels = panels;
        tabbedPane = new JTabbedPane();
        organize();
    }

    private void organize() {
        panels.forEach(panel -> {
            String name = getPanelName(panel);
            ImageIcon icon = ImageLoader.getIcon(name);
            tabbedPane.addTab("", icon, panel, name + "cards");
            tabbedPane.setMnemonicAt(0, KeyEvent.VK_1 + panels.indexOf(panel));
        });
        add(tabbedPane);
        repaint();
        revalidate();
    }

    public void addTab(CardSetPanel panel) {
        String name = getPanelName(panel);
        ImageIcon icon = ImageLoader.getIcon(name);
        tabbedPane.addTab("",icon, panel, name);
        repaint();
        revalidate();
    }

    private String getPanelName(CardSetPanel panel) {
        if (panel.getName() != null)
            return panel.getName();
        return "default";
    }

    /* No Listeners yet!*/
    @Override
    protected void addListeners() {

    }

    /* No Request and response yet!*/
    @Override
    protected void executeResponses() {

    }

}
