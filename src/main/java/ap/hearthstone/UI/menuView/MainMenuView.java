package ap.hearthstone.UI.menuView;

import ap.hearthstone.UI.api.Request;
import ap.hearthstone.UI.util.Drawer;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DragGestureEvent;

public class MainMenuView extends MenuView {

    public MainMenuView(){
        super("b:play", "b:setting", "b:status", "b:collection and shop");
    }


    @Override
    protected void addListeners() {
        buttonMap.get("play").addActionListener(e-> requestSender.send(new Request("play")));
        buttonMap.get("setting").addActionListener(e-> requestSender.send(new Request("setting")));
        buttonMap.get("status").addActionListener(e-> requestSender.send(new Request("status")));
        buttonMap.get("collection and shop").addActionListener(e-> requestSender.send(new Request("collection")));
    }

    @Override
    protected void organize() {
        ///////////Temporary!
        organizeLabel(new JLabel(""), 1, 0.8);
        organizeButton(buttonMap.get("play"), 2, 0.2);
        organizeButton(buttonMap.get("setting"), 5, 0.2);
        organizeButton(buttonMap.get("status"), 4, 0.2);
        organizeButton(buttonMap.get("collection and shop"), 3, 0.2);
        organizeLabel(new JLabel(""), 6, 0.8);
    }

    @Override
    protected void organizeButton(JButton button, int row, double weighty) {
        gc.weightx = 2;
        gc.weighty = weighty;

        gc.gridx = 2;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.CENTER;
        add(button, gc);
    }

    @Override
    protected void organizeLabel(JLabel label, int row, double weighty) {
        gc.weightx = 2;
        gc.weighty = weighty;

        gc.gridx = 2;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.CENTER;
        add(label, gc);    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Drawer.drawBackgroundImage("main", (Graphics2D) g);
    }

    @Override
    protected void executeResponses() {
        //is empty.
    }
}
