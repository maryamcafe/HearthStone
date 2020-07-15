package ap.hearthstone.model.gameModels.heros;

import ap.hearthstone.model.gameModels.HeroClass;
import ap.hearthstone.model.gameModels.entities.Hero;

public class HeroMage extends Hero {

    private static Hero instance;

    private HeroMage() {
        super(HeroClass.MAGE);
    }

    public static Hero getInstance(){
        if(instance == null){
            instance = new HeroMage();
        }
        return instance;
    }
}
