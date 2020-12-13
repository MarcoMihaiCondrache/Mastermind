package com.mastermind.Workers;

import com.mastermind.UI.Board;
import com.mastermind.Utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * SequenceWorker is the class that generates a new sequence for the game
 * based on size and board
 *
 * <p>
 * It is executed on a different thread from UI
 * </p>
 *
 * <p>
 * We retrieve all the values of our enum Color and then with
 * random indexes we pick colors based on the max size.
 * All colors are stored in a set to prevent duplicates.
 * </p>
 *
 * <p>
 * Finally we convert every color into an Awt Color
 * </p>
 *
 * @see com.mastermind.Game.Color
 * @see Color
 */
public class SequenceWorker extends SwingWorker<HashMap<Integer, Color>, Void> {
    private final int size;
    private final Board board;

    /**
     * Constructor for SequenceWorker class
     *
     * @param size  Size for the generated sequence
     * @param board Board where to apply the sequence
     */
    public SequenceWorker(int size, Board board) {
        this.size = size;
        this.board = board;
    }

    /**
     * Method that is used to execute code on another thread
     *
     * <p>
     * Here we implement all generation logic
     * </p>
     *
     * @return The hashmap with the generated sequence
     */
    @Override
    protected HashMap<Integer, Color> doInBackground() {
        Logger.debug("Worker: " + this.getClass().getName() + " started");

        com.mastermind.Game.Color[] colors = com.mastermind.Game.Color.values();
        HashSet<com.mastermind.Game.Color> generateSequence = new HashSet<com.mastermind.Game.Color>();
        HashMap<Integer, java.awt.Color> sequence = new HashMap<Integer, java.awt.Color>();
        Random random = new Random();

        while (generateSequence.size() < size) {
            generateSequence.add(colors[random.nextInt(colors.length)]);
        }

        Logger.debug(String.valueOf(generateSequence));

        for (int i = 0; i < generateSequence.size(); i++)
            sequence.put(i, colorToAwt((com.mastermind.Game.Color) generateSequence.toArray()[i]));

        return sequence;
    }

    /**
     * Method called when doInBackground() is terminated
     *
     * <p>
     * Tries to set the sequence and to enable board painting
     * </p>
     */
    @Override
    protected void done() {
        try {
            board.setSequence(get());
            board.setPainting(true);

            Logger.debug("Worker: " + this.getClass().getName() + " done");
        } catch (Exception e) {
            Logger.error("Exception occurred: " + Arrays.toString(e.getStackTrace()));
        }
    }

    private java.awt.Color colorToAwt(com.mastermind.Game.Color color) {
        Color c = Color.WHITE;
        switch (color) {
            case RED:
                c = Color.RED;
                break;
            case BLACK:
                c = Color.BLACK;
                break;
            case WHITE:
                c = Color.WHITE;
                break;
            case BLUE:
                c = Color.BLUE;
                break;
            case YELLOW:
                c = Color.YELLOW;
                break;
            case MAGENTA:
                c = Color.MAGENTA;
                break;
            case GREEN:
                c = Color.GREEN;
                break;
            case ORANGE:
                c = new Color(255, 69, 0);
                break;
        }

        return c;
    }
}
