package ap.hearthstone.states;

import ap.hearthstone.interfaces.Updatable;
import ap.hearthstone.UI.util.ImageLoader;

import java.awt.image.BufferedImage;

public class GameData implements Updatable {


    @Override
    public void update() {

    }

    public boolean isGameOver() {
        return false;
    }

    public BufferedImage getBgImage() {
        return ImageLoader.loadImage("background");
    }
}
