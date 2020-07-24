package ap.hearthstone.model.gameModels.heros;

import ap.hearthstone.model.gameModels.HeroClass;

public class HeroMage extends Hero {

    private static HeroMage instance;

    private HeroMage() {
        super(HeroClass.MAGE);
    }

    public static HeroMage getInstance(){
        if(instance == null){
            instance = new HeroMage();
        }
        return instance;
    }
}
