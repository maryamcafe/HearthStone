package ap.hearthstone.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class ConfigLoader {
    private static ConfigLoader configLoader;
    private static String defaultURL = "src/main/resources/MainConfigFile.properties";
    private Configs addresses, loginConstants, gameConstants, panelConfigs,
            cardConstants, imageURLs;
    private String cardsURL, decksURL, usersURL;
    private Logger logger = LogManager.getLogger(ConfigLoader.class);

    private ConfigLoader(String mainURL) {
        init(mainURL);
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null)
            configLoader = new ConfigLoader(defaultURL);
        return configLoader;
    }

    public static ConfigLoader getInstance(String address) {
        if (configLoader == null) {
            configLoader = new ConfigLoader(address); // supposing that the first
        }
        return configLoader;
    }

    private void init(String mainURL) {
        try {
            Reader reader = new FileReader(mainURL);
            addresses = new Configs();
            addresses.load(reader);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("MainConfigFile not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Configs getLoginConstants() {
        if (loginConstants == null) {
            loginConstants = new Configs();
            load(loginConstants, addresses.getProperty("LOGIN_CONSTANTS"));
        }
        return loginConstants;
    }

    public Configs getPanelConfigs() {
        if (panelConfigs == null) {
            panelConfigs = new Configs();
            load(panelConfigs, addresses.getProperty("PANEL_CONFIGS"));
        }
        return panelConfigs;
    }

    public Configs getGameConstants() {
        if (gameConstants == null) {
            gameConstants = new Configs();
            load(gameConstants, addresses.getProperty("GAME_CONSTANTS"));
        }
        return gameConstants;
    }

    public Configs getCardConstants() {
        if (cardConstants == null) {
            cardConstants = new Configs();
            load(cardConstants, addresses.getProperty("CARD_CONSTANTS"));
        }
        return cardConstants;
    }


    public Configs getImageURLs() {
        if (imageURLs == null) {
            imageURLs = new Configs();
            load(imageURLs, addresses.getProperty("IMAGE_URLS"));
        }
        return imageURLs;
    }

    public String getCardsURL() {
        if (cardsURL == null) {
            cardsURL = addresses.getProperty("CARDS_URL");
        }
        return cardsURL;
    }

    public String getDecksURL() {
        if(decksURL == null){
            decksURL = addresses.getProperty("DECKS_URL");
        }
        return decksURL;
    }

    public String getUsersURL() {
        if(usersURL == null){
            usersURL = addresses.getProperty("USERS_URL");
        }
        return usersURL;
    }

    private void load(Configs toLoad, String address) {
        try {
            FileReader fileReader = new FileReader(new File(address));
            toLoad.load(fileReader);
            fileReader.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
