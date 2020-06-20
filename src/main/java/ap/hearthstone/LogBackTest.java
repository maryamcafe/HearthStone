package ap.hearthstone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackTest {

    private static volatile  Logger logger  = LoggerFactory.getLogger("LogBackTest");

    public static void main(String[] args) {
        logger.info("Example log:)))))");
    }
}
