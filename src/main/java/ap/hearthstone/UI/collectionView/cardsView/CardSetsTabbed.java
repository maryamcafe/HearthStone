package ap.hearthstone.UI.collectionView.cardsView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.UI.util.ImageLoader;
import ap.hearthstone.interfaces.RequestSender;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class CardSetsTabbed extends UpdatingPanel {

    private final List<CardSetPanel> panels;
    private final JTabbedPane tabbedPane;
//    private JScrollPane scrollPane;

    Logger logger = LogManager.getLogger(this.getClass());

    public CardSetsTabbed() {
        this(new LinkedList<>());
    }

    public CardSetsTabbed(List<CardSetPanel> panels) {
        this.panels = panels;
        tabbedPane = new JTabbedPane();
//        tabbedPane.setTabPlacement(JTabbedPane.SCROLL_TAB_LAYOUT);
//        scrollPane = new JScrollPane(tabbedPane);
        organize();
    }

    private void organize() {
        Configs configs = ConfigLoader.getInstance().getPanelConfigs();
        Dimension panelDim = new Dimension(configs.readInt("cardPanelWidth"), configs.readInt("cardPanelHeight"));
        tabbedPane.setPreferredSize(panelDim);
        panels.forEach(panel -> {
            addTab(panel);
            logger.debug("Tabbed Pane organized");
        });
        add(tabbedPane);
        refresh();
    }

    public void addTab(CardSetPanel panel) {
        String name = getPanelName(panel);
        ImageIcon icon = ImageLoader.getIcon(name);
        JScrollPane scrollPane = new JScrollPane(panel);
        tabbedPane.addTab("",icon, scrollPane, name);
        refresh();
    }


    private String getPanelName(CardSetPanel panel) {
        if (panel.getName() != null)
            return panel.getName();
        return "default";
    }


    @Override
    protected void addListeners() {

    }

    @Override
    protected void executeResponses() {

    }

    @Override
    public void setRequestSender(RequestSender requestSender) {
        super.setRequestSender(requestSender);
        panels.forEach(panel -> panel.setRequestSender(requestSender));
    }

    @Override
    public void update(){
        super.update();
        panels.forEach(UpdatingPanel::update);
    }

}
