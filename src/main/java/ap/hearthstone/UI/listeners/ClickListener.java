package ap.hearthstone.UI.listeners;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.interfaces.RequestSender;
import ap.hearthstone.logging.MainLogger;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickListener implements MouseListener {

    private final RequestSender requestSender;
    private final Request request;
    private final String itemClicked;
    private final String context;
    MainLogger mainLogger = MainLogger.getLogger();

    public ClickListener(RequestSender requestSender, String itemClicked, String context, Request request){
        this.requestSender = requestSender;
        this.itemClicked = itemClicked;
        this.context = context;
        this.request = request;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        requestSender.send(request);
        mainLogger.click(itemClicked, context);
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
