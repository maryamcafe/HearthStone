package ap.hearthstone.UI.collectionView.cardsView;

import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.UI.util.Drawer;
import ap.hearthstone.UI.util.ImageLoader;
import ap.hearthstone.model.gameModels.cards.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class CardView extends UpdatingPanel {

    private String cardName;
    private Logger logger = LogManager.getLogger(this.getClass());

    public CardView(String cardName) {
        this.cardName = cardName;
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        JLabel cardImage = new JLabel(new ImageIcon(ImageLoader.getCardImage(cardName)));
        add(cardImage);
        repaint();
        revalidate();
//        logger.debug("CardView {}", cardName);
    }

    @Override
    public void paintComponents(Graphics g) {
//        super.paintComponents(g);
//        Graphics2D g2d = (Graphics2D) g;
//        Drawer.drawCardImage(g2d, cardName);
    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void executeResponses() {

    }
}
