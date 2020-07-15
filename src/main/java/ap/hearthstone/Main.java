package ap.hearthstone;

import ap.hearthstone.UI.control.Admin;
import ap.hearthstone.UI.control.MainFrame;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.IOException;


public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> {
            logger.log(Level.forName("start", 320), "the start.");
            Admin admin = new Admin();
            admin.start();
        });
    }
}
