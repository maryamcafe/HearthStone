package ap.hearthstone.UI.util;

import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

//   private Configs configs;
//
//    public ImageLoader() {
//        configs = ConfigLoader.getInstance().getImageURLs();
//    }

    public static ImageIcon getIcon(String iconName) {
        String path = ConfigLoader.getInstance().getImageURLs().getProperty("ICONS_URL") + iconName + ".png";
        return new ImageIcon(loadImage(path));
    }

    public static BufferedImage getCardImage(String cardName){
        String path = ConfigLoader.getInstance().getImageURLs().getProperty("CARDS_IMAGES_URL")
                + cardName.replace(" ", "_").replace(":", "-") + ".png";
        return loadImage(path);
    }

    public static BufferedImage getBackgroundImage(String viewName){
        String path = ConfigLoader.getInstance().getImageURLs().getProperty("BACKGROUNDS_URL") + viewName + ".jpg";
        return loadImage(path);
    }

    public static BufferedImage loadImage(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
