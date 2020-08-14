package ap.hearthstone.UI.menuView;

import ap.hearthstone.UI.api.Request;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends MenuView {

    public MainMenuView(){
        super("b:play", "b:shop", "b:status", "b:collection");
    }


    @Override
    protected void addListeners() {
        buttonMap.get("play").addActionListener(e-> requestSender.send(new Request("play")));
        buttonMap.get("shop").addActionListener(e-> requestSender.send(new Request("shop")));
        buttonMap.get("status").addActionListener(e-> requestSender.send(new Request("status")));
        buttonMap.get("collection").addActionListener(e-> requestSender.send(new Request("collection")));
    }

    @Override
    protected void organize() {
        ///////////Temporary!
        organizeLabel(new JLabel(""), 1, 0.8);
        organizeButton(buttonMap.get("play"), 2, 0.2);
        organizeButton(buttonMap.get("shop"), 3, 0.2);
        organizeButton(buttonMap.get("status"), 4, 0.2);
        organizeButton(buttonMap.get("collection"), 5, 0.8);
    }

//    @Override
//    protected void organizeButton(JButton button, int row, double weighty) {
//        Insets inset = new Insets(0, 600, 0, 0);
//        gc.weightx = 1;
//        gc.weighty = weighty;
//
//        gc.gridx = 2;
//        gc.gridy = row;
//        gc.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;
//        gc.insets = inset;
//        add(button, gc);
//    }
    @Override
    protected void executeResponses() {
        //Maybe some "Loading" window???
    }
}
