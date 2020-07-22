package ap.hearthstone.UI.util;

import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow extends JFrame {
    Configs config = ConfigLoader.getInstance().getPanelConfigs(); //this can't be lazy evaluated.

    public ErrorWindow(String errorMessage){
        super("Error!");

        int width = config.readInt("errorFrameWidth");
        int height = config.readInt("errorFrameHeight");
        setSize(width, height);
        setLayout(new BorderLayout());

        JLabel message = new JLabel(errorMessage);
        add(message, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);


    }

}
