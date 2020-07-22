package ap.hearthstone.UI.util;

import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

//   private Configs configs;
//
//    public ImageLoader() {
//        configs = ConfigLoader.getInstance().getImageURLs();
//    }

    private static Logger logger = LogManager.getLogger(ImageLoader.class);

    public static ImageIcon getIcon(String iconName) {
        String path = ConfigLoader.getInstance().getImageURLs().getProperty("ICONS_URL") + iconName + ".png";
        return new ImageIcon(resize(loadImage(path), 30, 30));
    }

    public static BufferedImage getCardImage(String cardName){
        String path = ConfigLoader.getInstance().getImageURLs().getProperty("CARDS_IMAGES_URL")
                + cardName.replace(" ", "_").replace(":", "-").replace("’", "'") + ".png";
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
            logger.error(e.getMessage(), e);
        }
        return image;
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}
