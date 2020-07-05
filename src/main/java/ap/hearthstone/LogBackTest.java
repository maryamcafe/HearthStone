package ap.hearthstone;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.lookup.MapLookup;

public class LogBackTest {
    private static final Logger logger;

    static {
        System.setProperty("id", "FOLAN");
        logger = LogManager.getLogger(LogBackTest.class);
    }

    public static void main(String[] args) {
        ThreadContext.put("id", "folanID");
        logger.info("Example log:)))))");
        logger.getName();
    }
}
