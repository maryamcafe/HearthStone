package ap.hearthstone.model.gameModels.util;

import ap.hearthstone.utils.ConfigLoader;
import ap.hearthstone.utils.Configs;

public class GameConstants {

    private Integer defaultCoins;
    private Byte heroInitialHealth;
    private String defaultDeck;
    private String defaultHero;
    private Integer maxDeckCards;
    private Integer maxEachCard;

    private final Configs constants;
    private static GameConstants instance;

    private GameConstants(){
        constants = ConfigLoader.getInstance().getGameConstants();
    }

    public static GameConstants getInstance(){
        if(instance == null){
            instance = new GameConstants();
        }
        return instance;
    }

    public int getDefaultCoins() {
        if(defaultCoins == null){
            defaultCoins = constants.readInt("defaultCoins");
        }
        return defaultCoins;
    }

    public int getCardValue(String rarity) {
        return constants.readInt(rarity);
    }

    public byte getHeroInitialHealth() {
        if(heroInitialHealth == null){
            heroInitialHealth = constants.readByte("heroInitialHealth");
        }
        return heroInitialHealth;
    }

    public String getDefaultDeck() {
        if(defaultDeck == null){
            defaultDeck = constants.getProperty("defaultDeck");
        }
        return defaultDeck;
    }

    public int getMaxDeckCards() {
        if(maxDeckCards == null){
            maxDeckCards = constants.readInt("maxTotalCards");
        }
        return maxDeckCards;
    }

    public int getMaxEachCard() {
        if(maxEachCard == null){
            maxEachCard = constants.readInt("maxEachCard");
        }
        return maxEachCard;
    }

    public String getDefaultHero() {
        if(defaultHero == null){
            defaultHero = constants.getProperty("defaultHero");
        }
        return defaultHero;
    }

    public String getDeckInitiationGuid(){
        return constants.getProperty("deckInitiationGuid");
    }

    /* Text used in CLI:)))  */
    public String getMessage(String messageName) {
        return constants.getProperty(messageName);
    }


}
