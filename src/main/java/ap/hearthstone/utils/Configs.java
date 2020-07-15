package ap.hearthstone.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Configs extends Properties {
    //we want some method to parse integer from string
    public Integer readInt(String keyName){
        return Integer.parseInt(this.getProperty(keyName));
    }

    public Long readLong(String keyName){
        return Long.parseLong(this.getProperty(keyName));
    }

    public boolean readBoolean(String keyName) {
        return Boolean.parseBoolean(this.getProperty(keyName));
    }

    public List<String> readList(String keyName){
        return Arrays.asList(this.getProperty(keyName).split(","));
    }

}
