package ap.hearthstone.UI.util;

import java.awt.*;

public class Drawer {

//    private Graphics2D g;
//
//    public Drawer(Graphics2D g){
//        this.g = g;
//    }

    public static void drawCardImage(Graphics2D g, String cardImageName) {
        g.drawImage(ImageLoader.getCardImage(cardImageName), 0,0, null);
    }

    public static void drawBackgroundImage(String viewName, Graphics2D g){
        g.drawImage(ImageLoader.getBackgroundImage(viewName), 0, 0, null);
    }

    /* Initial Location of the image not specified.
     */
    public static void drawImage(Graphics2D g, String imagePath){
        g.drawImage(ImageLoader.loadImage(imagePath), 0, 0, null);
    }

}
