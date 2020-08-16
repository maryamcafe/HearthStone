package ap.hearthstone.UI.gameView;


import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.api.UpdatingPanel;
import ap.hearthstone.UI.util.Drawer;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

import javax.swing.*;
import java.awt.*;

//panels should get data from states and draw them

public class GameView extends UpdatingPanel {

    Graphics2D g2d;
    JButton endTurn;
    JButton back;
    DiscoverPanel discoverPanel;

    public GameView() {
        endTurn = new JButton("End Turn");
        back = new JButton("Back");
        discoverPanel = new DiscoverPanel();
        g2d = (Graphics2D) this.getGraphics();
    }

    @Override
    protected void organize() {
        setLayout(null);
        Configs config = ConfigLoader.getInstance().getPanelConfigs();
        endTurn.setBounds(config.readInt("endTurnX") , config.readInt("endTurnY"),
                config.readInt("endTurnWidth"), config.readInt("endTurnHeight"));
        back.setBounds(config.readInt("backX"), config.readInt("backY"),
                config.readInt("backWidth"), config.readInt("backHeight"));
        add(endTurn);
        add(back);
        refresh();
    }


    protected void addListeners() {
        endTurn.addActionListener(e -> requestSender.send(new Request("endTurn")));
        back.addActionListener(e -> requestSender.send(new Request("back")));
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
        super.initView();
        requestSender.send(new Request("passive"));
        showPassives();
    }

    private void showPassives() {
//        add(new DiscoverPanel())
    }
}
