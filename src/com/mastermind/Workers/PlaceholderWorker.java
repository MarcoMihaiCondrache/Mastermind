package com.mastermind.Workers;

import com.mastermind.UI.Board;
import com.mastermind.Utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

/**
 * PlaceholderWorker is the class that initialize the placeholders for the game
 * based on size and board
 *
 * <p>
 * It is executed on a different thread from UI. We put a default color in hashmap
 * </p>
 *
 * @see com.mastermind.Game.Color
 * @see Color
 */
public class PlaceholderWorker extends SwingWorker<HashMap<Integer, HashMap<Integer, Color>>, Void> {
    private final int size;
    private final Board board;
    private final Color defaultColor;

    /**
     * Constructor for Placeholder class
     *
     * @param size         Size for the generated sequence
     * @param board        Board where to apply the sequence
     * @param defaultColor color to initialize placeholder
     */
    public PlaceholderWorker(int size, Board board, Color defaultColor) {
        this.size = size;
        this.board = board;
        this.defaultColor = defaultColor;
    }

    /**
     * Method that is used to execute code on another thread
     *
     * <p>
     * Here we implement all generation logic
     * </p>
     *
     * @return The hashmap with the placeholders
     */
    @Override
    protected HashMap<Integer, HashMap<Integer, java.awt.Color>> doInBackground() throws Exception {
        HashMap<Integer, HashMap<Integer, Color>> placeholders = new HashMap<Integer, HashMap<Integer, java.awt.Color>>();

        Logger.debug("Worker: " + this.getClass().getName() + " started");

        for (int i = 0; i < 8; i++) {
            HashMap<Integer, Color> single = new HashMap<Integer, java.awt.Color>();
            for (int j = 0; j < size; j++) {
                single.put(j, defaultColor);
            }

            placeholders.put(i, single);
        }

        return placeholders;
    }

    /**
     * Method called when doInBackground() is terminated
     *
     * <p>
     * Tries to set placeholders and to enable board painting
     * </p>
     */
    @Override
    protected void done() {
        try {
            board.setPlaceholder(get());
            board.setPainting(true);

            Logger.debug("Worker: " + this.getClass().getName() + " done");
        } catch (Exception e) {
            Logger.error("Exception occurred: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
