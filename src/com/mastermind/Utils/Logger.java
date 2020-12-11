package com.mastermind.Utils;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Logger {
    public static void info(String message) {
        String led = "[" + Logger.getDate() + "]";
        System.out.println(led + " " + message);
    }

    private static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }
}
