package ap.hearthstone.UI.collectionView.cardsView;

import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.UI.api.ViewPanel;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

import javax.swing.*;
import java.awt.*;

public class FilterPanel extends UpdatingPanel {

    private JPanel manaFilterPanel;
    private JButton shop;
    private JButton back;

    public FilterPanel() {
        super();
        manaFilterPanel = new JPanel(new GridLayout(1,0));
        shop = new JButton("SHOP");
        back = new JButton("BACK");
        organize();
    }

    private void organize() {
        Configs configs = ConfigLoader.getInstance().getPanelConfigs();

        Dimension filterPanelDim = new Dimension(configs.readInt("filterPanelWidth"), configs.readInt("filterPanelHeight"));
        setSize(filterPanelDim);
        int panelMargin = configs.readInt("filterPanelMargin");
        setBorder(BorderFactory.createEmptyBorder(panelMargin, panelMargin, panelMargin, panelMargin));

        Dimension manaFilterDim = new Dimension(configs.readInt("manaFilterWidth"), configs.readInt("manaFilterHeight"));
        manaFilterPanel.setSize(manaFilterDim);
        int margin = configs.readInt("filterButtonMargin");
        manaFilterPanel.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));

        back.setBorder(BorderFactory.createEtchedBorder());
        shop.setBorder(BorderFactory.createEtchedBorder());

        add(manaFilterPanel, BorderLayout.LINE_END);
        add(shop, BorderLayout.CENTER);
        add(back, BorderLayout.LINE_START);

    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void executeResponses() {

    }
}
