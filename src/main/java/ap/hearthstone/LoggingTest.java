package ap.hearthstone;



import java.io.IOException;
import java.net.URL;


public class LoggingTest {
//    static volatile Logger logger = LogManager.getLogger("ap.hearthstone");
//protected final static Logger logger = StatusLogger.getLogger();

    public static void main(String[] args) {
//        CustomConfigurationFactory.reconfigure();
        URL address = Thread.currentThread().getContextClassLoader().getResource("log4j2.xml");
//        System.out.println("Config address is: " + address);
//        assert address != null;
//        System.out.println(logger.getName());
//        assert address != null;
//        LoggerContext ctx = Configurator.initialize("fileConfig",address.toString()); //Critical to logging configuration

//        org.apache.logging.log4j.spi.LoggerContext context = LogManager.getContext(true);
//        if (context instanceof SimpleLoggerContext) {
//            System.out.println("logger is an instance of simple logger context.");
//        }

//        Level level = logger.getLevel();
//        logger.info("info info info info");
//        logger.debug("debug debug debug debug");
//        logger.warn("warn warn warn warn");
//        logger.trace("trace trace trace trace trace");
//        logger.warn("KhodaHafez!!!!!!!!!!");

    }


    static void doSth() throws IOException{
        throw new IOException("some 6-16-2020-11:05 error");
    }
}