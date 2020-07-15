package ap.hearthstone.logging;

import ap.hearthstone.LogBackTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
this is a wrapper class in order to use the logger I use easily, without any extra dependency in my source code
 */
public class MyLogger {

    private final Logger logger;

    public MyLogger(String id) {
        System.setProperty("id", id);
        logger = LogManager.getLogger(LogBackTest.class);
    }

    public void debug(String message){
        logger.debug(message);
    }

    public Logger getLogger() {
        return logger;
    }
}
