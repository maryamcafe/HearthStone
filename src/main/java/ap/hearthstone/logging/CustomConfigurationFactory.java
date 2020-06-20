//package ap.hearthstone.logging;
//
////import org.apache.logging.log4j.Level;
////import org.apache.logging.log4j.core.Filter;
////import org.apache.logging.log4j.core.LoggerContext;
////import org.apache.logging.log4j.core.appender.ConsoleAppender;
////import org.apache.logging.log4j.core.config.*;
////import org.apache.logging.log4j.core.config.builder.api.*;
////import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
////import org.apache.logging.log4j.core.config.plugins.Plugin;
//
//import java.net.URI;
//
////@Plugin(name = "CustomConfigurationFactory", category = ConfigurationFactory.CATEGORY)
////@Order(5)
//public class CustomConfigurationFactory extends ConfigurationFactory {
//
//    public void reconfigure() {
//        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
//        builder.setStatusLevel(Level.WARN);
//        builder.setConfigurationName("RollingBuilder");
//
//// create a console appender
//        AppenderComponentBuilder appenderBuilder = builder.newAppender("Stdout", "CONSOLE").
//                addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
//
//        appenderBuilder.add(builder.newLayout("PatternLayout")
//                .addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable")
//                .addAttribute("header", "Hello! %n"));
//
//        builder.add(appenderBuilder);
//
////   ---------------------          create a rolling file appender
//        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout")
//                .addAttribute("pattern", "%d [%t] %-5level: %msg%n").addAttribute("header", "Saaalaaam%n");
//
//        ComponentBuilder triggeringPolicy = builder.newComponent("Policies")
//                .addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "100M"));
//
//        AppenderComponentBuilder appenderBuilder2 = builder.newAppender("rolling", "RollingFile")
//                .addAttribute("fileName", "logs/rolling.log")
//                .addAttribute("filePattern", "logs/rolling-%d{MM-dd-yy}.log.gz")
//                .add(layoutBuilder)
//                .addComponent(triggeringPolicy);
//        builder.add(appenderBuilder2);
//
////   ------------------  create the new logger
//        builder.add(builder.newLogger("LoggingTest", Level.DEBUG)
//                .add(builder.newAppenderRef("rolling"))
//                .addAttribute("additivity", false));
//
//        builder.add(builder.newRootLogger(Level.TRACE)
//                .add(builder.newAppenderRef("Stdout"))
//                .addAttribute("additivity", false));
//
//        LoggerContext ctx = Configurator.initialize(builder.build());
//    }
//
//    public static Configuration createConfiguration(final String name, ConfigurationBuilder<BuiltConfiguration> builder) {
//
//        builder.setConfigurationName(name);
//        builder.add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL)
//                .addAttribute("level", Level.DEBUG));
//        AppenderComponentBuilder appenderBuilder = builder.newAppender("Stdout", "CONSOLE")
//                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
//        appenderBuilder.add(builder.newLayout("PatternLayout")
//                .addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"));
////        appenderBuilder.add(builder.newFilter("MarkerFilter", Filter.Result.DENY,
////                Filter.Result.NEUTRAL).addAttribute("marker", "FLOW"));
//        builder.add(appenderBuilder);
////        builder.add(builder.newLogger("org.apache.logging.log4j", Level.DEBUG).
////                add(builder.newAppenderRef("Stdout")).
////                addAttribute("additivity", false));
//        builder.add(builder.newRootLogger(Level.ERROR).add(builder.newAppenderRef("Stdout")));
//        return builder.build();
//    }
//
//    @Override
//    public Configuration getConfiguration(final LoggerContext loggerContext, final ConfigurationSource source) {
//        return getConfiguration(loggerContext, source.toString(), null);
//    }
//
//    @Override
//    public Configuration getConfiguration(final LoggerContext loggerContext, final String name, final URI configLocation) {
//        ConfigurationBuilder<BuiltConfiguration> builder = newConfigurationBuilder();
//        return createConfiguration(name, builder);
//    }
//
//    @Override
//    protected String[] getSupportedTypes() {
//        return new String[]{"*"};
//    }
//}