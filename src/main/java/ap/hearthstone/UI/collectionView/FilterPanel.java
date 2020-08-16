package ap.hearthstone.UI.collectionView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.logging.MainLogger;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

import javax.swing.*;
import java.awt.*;

public class FilterPanel extends UpdatingPanel {

    private JPanel manaFilterPanel;
    private final JButton shop;
    private final JButton back;
    private final JTextField searchField;
    MainLogger mainLogger = MainLogger.getLogger(this.getClass());

    public FilterPanel() {
        super();
        manaFilterPanel = new JPanel(new GridLayout(1,0));
        shop = new JButton("SHOP");
        back = new JButton("BACK");
        searchField = new JTextField("Search Card Name:", 10);
        organize();
    }

    protected void organize() {
        Configs configs = ConfigLoader.getInstance().getPanelConfigs();
        Dimension filterPanelDim = new Dimension(configs.readInt("filterPanelWidth"), configs.readInt("filterPanelHeight"));
        setSize(filterPanelDim);
        int panelMargin = configs.readInt("filterPanelMargin");
        setBorder(BorderFactory.createEmptyBorder(panelMargin, panelMargin, panelMargin, panelMargin));

        Dimension manaFilterDim = new Dimension(configs.readInt("manaFilterWidth"), configs.readInt("manaFilterHeight"));
        manaFilterPanel.setSize(manaFilterDim);
        int margin = configs.readInt("filterButtonMargin");
        manaFilterPanel.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));

//        back.setBorder(BorderFactory.createEtchedBorder());
//        shop.setBorder(BorderFactory.createEtchedBorder());

        JPanel integrating = new JPanel(new BorderLayout());
        add(manaFilterPanel, BorderLayout.WEST);
        add(shop, BorderLayout.CENTER);
        add(back, BorderLayout.EAST);

        refresh();
    }

    @Override
    protected void addListeners() {
        back.addActionListener(e -> {
            requestSender.send(new Request("back"));
            mainLogger.click("back", "collection");
        });
        shop.addActionListener(e -> {
            requestSender.send(new Request("shop"));
            mainLogger.click("shop", "collection");
        });
        searchField.addActionListener(e ->
                requestSender.send(new Request("searchCards", searchField.getText())));
    }

    @Override
    protected void executeResponses() {

    }
}
