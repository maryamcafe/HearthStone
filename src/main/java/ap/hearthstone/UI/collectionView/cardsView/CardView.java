package ap.hearthstone.UI.collectionView.cardsView;

import ap.hearthstone.UI.util.Drawer;
import ap.hearthstone.model.gameModels.cards.Card;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class CardView extends JPanel {

    private String cardName;

    public CardView(String cardName) {
        this.cardName = cardName;
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        repaint();
        revalidate();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        Drawer.drawCardImage(g2d, cardName);
    }
}
