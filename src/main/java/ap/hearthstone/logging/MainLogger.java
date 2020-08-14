package ap.hearthstone.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/*
this is a wrapper class in order to use the logger I use easily, without any extra dependency in my source code
 */
public class MainLogger {

    private Logger logger;
    private static MainLogger instance;
    final Level click = Level.forName("Click", 350);
    final Level navigate = Level.forName("Navigate", 350);
    final Level select = Level.forName("Select", 450);


    private MainLogger(String username, String password, long id, String created, String deleted, Class c) {
        System.setProperty("user", username);
        System.setProperty("password", password);
        System.setProperty("id", String.valueOf(id));
        System.setProperty("created", created);
        if (deleted != null) {
            System.setProperty("deleted", deleted);
        }
        logger = LogManager.getLogger(c);
    }

    private MainLogger(Class c){
        logger = LogManager.getLogger(c);
    }
    public static MainLogger createLogger(String username, String password, long id, String createdAt, String Deleted_At) {
//        if (instance == null) {
//            instance =
        return new MainLogger(username, password, id, createdAt, Deleted_At, MainLogger.class);
//        }
//        return instance;
    }

    public static MainLogger createLogger(String username, String password, long id, String createdAt, String Deleted_At, Class c) {
        return new MainLogger(username, password, id, createdAt, Deleted_At, c);
    }

    public static MainLogger getLogger() {
        assert instance != null : "MainLogger not initiated through UserFactory."; // a kind of warning
        return instance;
    }

    public static MainLogger getLogger(Class c){
        return new MainLogger(c);
    }

    public void navigate(String viewName) {
        logger.log(navigate, viewName);
    }

    public void click(String buttonName) {
        logger.log(click, buttonName);
    }

    public void select(String target) {
        logger.log(select, target);
    }

    public void log(String logLevel, String message) {
        logger.log(Level.forName(logLevel, 350), message);
    }

    public void log(String logLevel, String message, Object... o) {
        logger.log(Level.forName(logLevel, 350), message, o);
    }

    public void error(String s) {
        logger.error(s);
    }

    public void error(String s, Exception e) {
        logger.error(s, e);
    }

    public void info(String s) {
        logger.info(s);
    }

    public void info(String s, Object... o) {
        logger.info(s, o);
    }

    public void debug(String s) {
        logger.debug(s);
    }

    public void debug(String s, Object... o) {
        logger.debug(s, o);
    }

}
