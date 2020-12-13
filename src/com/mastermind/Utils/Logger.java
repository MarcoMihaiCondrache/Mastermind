package com.mastermind.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that provides functions for logging
 */
public class Logger {
    /**
     * Method to print debug message
     *
     * @param message Message to print
     */
    public static void debug(String message) {
        Logger.o(message, "DEBUG");
    }

    /**
     * Method to print warning message
     *
     * @param message Message to print
     */
    public static void warning(String message) {
        Logger.o(message, "WARNING");
    }

    /**
     * Method to print error message
     *
     * @param message Message to print
     */
    public static void error(String message) {
        Logger.o(message, "ERROR");
    }

    private static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }

    private static void o(String message, String c) {
        String led = "[" + Logger.getDate() + " " + c + "]";
        System.out.println(led + " " + message);
    }
}
