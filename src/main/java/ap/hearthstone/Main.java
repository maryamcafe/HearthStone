package ap.hearthstone;

import ap.hearthstone.UI.control.DisplayAdmin;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;


public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> {
            logger.log(Level.forName("start", 320), "the start.");
            DisplayAdmin displayAdmin = new DisplayAdmin();
            displayAdmin.start();
        });
    }
}
