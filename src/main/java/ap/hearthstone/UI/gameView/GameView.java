package ap.hearthstone.UI.gameView;


import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.UI.util.Drawer;

import javax.swing.*;
import java.awt.*;

//panels should get data from states and draw them

public class GameView extends UpdatingPanel {

    Graphics2D g2d;

    public GameView() {
        g2d = (Graphics2D) this.getGraphics();
    }

    @Override
    protected void organize() {

    }

    @Override
    protected void addListeners() {
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        requestFocus();
        drawBgImage();
        drawGameState();
    }


    private void drawBgImage() {
        //TODO omit Drawer
        Drawer.drawBackgroundImage("game", g2d);
    }

    private void drawGameState() {

    }


    @Override
    protected void executeResponses() {

    }

    // TODO: choose passives.
    @Override
    public void initView() {
        //show passives() when clicked on one just send a request and showThreeCards(). if chose a card send request.
        requestSender.send(new Request("passive"));
        showPassives();
    }

    private void showPassives() {
//        add(new DiscoverPanel())
    }
}
