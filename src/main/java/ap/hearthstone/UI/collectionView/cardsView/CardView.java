package ap.hearthstone.UI.collectionView.cardsView;

import ap.hearthstone.UI.util.ImageLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class CardView extends JPanel {

    private Logger logger = LogManager.getLogger(this.getClass());
    private final JLabel label;
    private JButton buyButton;
    private JButton sellButton;

    public CardView(String cardName, int number) {
        super(new BorderLayout());
        label = new JLabel(number + "X",
                new ImageIcon((ImageLoader.getCardImage(cardName, number==0))),
                SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        add(label, BorderLayout.CENTER);
    }

    public CardView(String cardName, int number, int value){
        this(cardName, number);
        buyButton = new JButton(String.format("BUY: %d coins", value));
        sellButton = new JButton(String.format("BUY: %d coins", value));
        add(buyButton, BorderLayout.PAGE_END);
        add(sellButton, BorderLayout.LINE_START);
        setOpaque(true);
    }

    public JLabel getLabel() {
        return label;
    }

    public void updateLabel(int number){
        label.setText(number+"X");
    }

    public JButton getBuyButton() {
        return buyButton;
    }
}
