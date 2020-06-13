package states;

import interfaces.Updatable;
import swing.ImageLoader;

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
