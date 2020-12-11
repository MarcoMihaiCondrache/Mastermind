package com.mastermind.Workers;

import com.mastermind.UI.Board;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class SequenceWorker extends SwingWorker<HashMap<Integer, Color>, Void> {
    private final int size;
    private final Board board;

    public SequenceWorker(int size, Board board){
        this.size = size;
        this.board = board;
    }

    @Override
    protected HashMap<Integer, Color> doInBackground() {
        com.mastermind.Game.Color[] colors = com.mastermind.Game.Color.values();
        HashSet<com.mastermind.Game.Color> generateSequence = new HashSet<com.mastermind.Game.Color>();
        HashMap<Integer, java.awt.Color> sequence = new HashMap<Integer, java.awt.Color>();
        Random random = new Random();

        while (generateSequence.size() < size) {
            generateSequence.add(colors[random.nextInt(colors.length)]);
        }

        for (int i = 0; i < generateSequence.size(); i++)
            sequence.put(i, colorToAwt((com.mastermind.Game.Color) generateSequence.toArray()[i]));

        return sequence;
    }

    @Override
    protected void done() {
        try {
            board.setSequence(get());
            board.setPainting(true);
        } catch (Exception e) {
            e.printStackTrace();
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
