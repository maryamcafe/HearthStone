package ap.hearthstone.model.gameModels.util;

import ap.hearthstone.UI.util.PanelConfig;
import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

public class GameConstants {

    private Integer defaultCoins;
    private Byte heroInitialHealth;
    private final Configs constants;

    public GameConstants(){
        constants = ConfigLoader.getInstance().getGameConstants();
    }

    public int getDefaultCoins() {
        if(defaultCoins == null){
            defaultCoins = constants.readInt("defaultCoins");
        }
        return defaultCoins;
    }

    public byte getHeroInitialHealth() {
        if(heroInitialHealth == null){
            heroInitialHealth = constants.readByte("heroInitialHealth");
        }
        return heroInitialHealth;
    }

    /*
    Text used in CLI:)))
     */
    public String getMessage(String messageName) {
        return constants.getProperty(messageName);
    }

}
