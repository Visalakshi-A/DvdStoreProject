package com.ideas2it.dvdstore.logging;

/**
 * Contains methods that invokes the corresponding log4j methods.
 *
 * @version 1
 * @author Visalakshi
 *
 */
public class Logger {

    private static org.apache.logging.log4j.Logger logger
                              = org.apache.logging.log4j.LogManager.getRootLogger();

    public static void trace(String message) {
        logger.trace(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable cause) {
        logger.error(message, cause);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }
}
