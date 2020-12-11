package com.mastermind;

import com.mastermind.UI.Settings;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Settings();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
