package util;

import java.util.Properties;

public class Configs extends Properties {
    //we want some method to parse integer from string
    public int readInt(String keyName){
        return Integer.parseInt(this.getProperty(keyName));
    }

    public boolean readBoolean(String keyName) {
        return Boolean.parseBoolean(this.getProperty(keyName));
    }


}
