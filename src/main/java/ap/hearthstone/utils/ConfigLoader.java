package ap.hearthstone.utils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ConfigLoader {
    private static ConfigLoader configLoader;
    private Configs addresses, gameConstants, panelConstants;
    private String defaultURL = "src/main/resources/MainConfigFile.properties";
    private String cardsURL, imagesURL;

    private ConfigLoader(){
        init();
    }

    public static ConfigLoader getInstance(){
        if (configLoader == null)
            configLoader = new ConfigLoader();
        return configLoader;
    }

    private void init() {
        try {
            Reader reader = new FileReader(defaultURL);
            addresses.load(reader);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("MainConfigFile not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadConfigurations();
    }

    private void loadConfigurations() {

        for (Map.Entry<Object, Object> entry: addresses.entrySet()){
            System.out.println(entry.getKey() + " = " + entry.getValue());

            String key = ((String)entry.getKey()).toLowerCase();
            String address =  (String) entry.getValue();

            Configs configs = new Configs();

            if (key.contains("config")){
                try {//load config from fime
                    Path path = Paths.get(address);
                    FileReader reader = new FileReader(path.toFile());
                    configs.load(reader);
                    reader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println(key + " file not found");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(key + " load failed");
                }
                if(key.contains("game")){
                    gameConstants = configs;
                } else if(key.contains("graphic")){
                    panelConstants = configs;
                }
            } else if(key.contains("image")){//for images we just need to copy the relative path
                //maybe this changes in the future to a config
                imagesURL = address;
            } else if (key.contains("card")) {
                cardsURL = address;
            }
        }
    }

    public Configs getGameConstants() {
        return gameConstants;
    }

    public Configs getPanelConstants() {
        return panelConstants;
    }

    public String getImagesURL() {
        return imagesURL;
    }

    public String getCardsURL() {
        return cardsURL;
    }

    public String getAddress(String addressName) {
        return addresses.getProperty(addressName);
    }
}
