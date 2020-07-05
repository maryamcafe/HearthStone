package ap.hearthstone.UI.util;

import ap.hearthstone.utils.ConfigLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage loadImage(String imageName){
        String path = ConfigLoader.getInstance().getImagesURL() + imageName + ".png";
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
