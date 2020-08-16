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

    // TODO change static methods here to non-static
    // TODO create a panel config class.
   private static final Configs configs = ConfigLoader.getInstance().getPanelConfigs();
   private static final Configs imageURLs = ConfigLoader.getInstance().getImageURLs();
//    public ImageLoader() {
//        configs = ConfigLoader.getInstance().getImageURLs();
//    }

    private static final Logger logger = LogManager.getLogger(ImageLoader.class);

    public static ImageIcon getIcon(String iconName) {
        String path = imageURLs.getProperty("ICONS_URL") + iconName + ".png";
        return new ImageIcon(resize(loadImage(path), 30, 30));
    }

    public static ImageIcon getDeckIcon(String heroName){
        String path = imageURLs.getProperty("DECKS_IMAGES_URL") + heroName + "Deck.png";
        return new ImageIcon(loadImage(path));
    }

    public static BufferedImage getCardImage(String cardName){
        String path = imageURLs.getProperty("CARDS_IMAGES_URL")
                + cardName.replace(" ", "_").replace(":", "-").replace("’", "'") + ".png";
        return loadImage(path);
    }

    public static BufferedImage getCardImage(String cardName, boolean isZero){
        String gray = "";
        if(isZero) gray = "-gray";
        String path = imageURLs.getProperty("CARDS_IMAGES_URL")
                + cardName.replace(" ", "_").replace(":", "-").replace("’", "'") + gray + ".png";
        return loadImage(path);
    }

    public static BufferedImage getBackgroundImage(String viewName){
        String path = imageURLs.getProperty("BACKGROUNDS_URL") + viewName + ".jpg";
        return resize(loadImage(path), configs.readInt("mainFrameHeight"), configs.readInt("mainFrameWidth"));
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
