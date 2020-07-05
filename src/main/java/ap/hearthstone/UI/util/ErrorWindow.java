package ap.hearthstone.UI.util;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow extends JFrame {
    PanelConfig config = PanelConfig.getInstance(); //this can't be lazy evaluated.

    public ErrorWindow(String errorMessage){
        super("Error!");

        int width = config.getErrorFrameWidth();
        int height = config.getErrorFrameHeight();
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
