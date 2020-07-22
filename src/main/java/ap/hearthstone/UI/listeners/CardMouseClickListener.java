package ap.hearthstone.UI.listeners;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.interfaces.RequestSender;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardMouseClickListener implements MouseListener {

    private String cardName;
    private RequestSender requestSender;

    public CardMouseClickListener(String cardName, RequestSender requestSender){
        this.cardName = cardName;
        this.requestSender = requestSender;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        requestSender.send(new Request("cardClick", cardName));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
