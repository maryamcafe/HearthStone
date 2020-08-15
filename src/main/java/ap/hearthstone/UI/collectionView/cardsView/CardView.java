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

public class CardView extends JLabel {

    private Logger logger = LogManager.getLogger(this.getClass());

    public CardView(String cardName, int number) {
        super(new ImageIcon((ImageLoader.getCardImage(cardName, number==0))));
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        setText(number + "X");
    }

}
