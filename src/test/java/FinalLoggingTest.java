import java.lang.reflect.Method;

public class FinalLoggingTest {
//    private static final Logger logger = LogManager.getLogger(LoggingTest.class);

    public static void main(String[] args) {
//        logger.info("this is my Info Logger in main");
//        logger.error("this is my Error Logger in main is gone now");
//

        Caller();
    }

    private static void Caller() {
        System.out.println("Caller");
        called();
    }

    private static void called() {
        System.out.println("Called");
//        Method method = this.getClass().getMethod(methodName, param1.class, param2.class, ..);

    }

    public static String logTheStack() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        //this shoes the first caller function of every function:
        StackTraceElement e = stacktrace[2];
        //all the way up (could be continued)
        System.out.println(  stacktrace[0].getMethodName() + " + "
                + stacktrace[1].getMethodName() + " + "
                + stacktrace[2].getMethodName() + " + "
                + stacktrace[3].getMethodName());
        return stacktrace[2].getMethodName();
    }

}
