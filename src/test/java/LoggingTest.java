import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class LoggingTest {
    private static final Logger logger = LogManager.getLogger(LoggingTest.class);

    public static void main(String[] args) {
        logger.info("main has compiled");
        logger.error("error: main has compiled");


        String a = "11";
        int b = Integer.parseInt(a);
        System.out.println(b);
    }

}

