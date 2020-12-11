package com.mastermind.Workers;

import com.mastermind.UI.Board;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PlaceholderWorker extends SwingWorker<HashMap<Integer, HashMap<Integer, Color>>, Void> {
    private final int size;
    private final Board board;
    private final Color defaultColor;

    public PlaceholderWorker(int size, Board board, Color defaultColor){
        this.size = size;
        this.board = board;
        this.defaultColor = defaultColor;
    }

    @Override
    protected HashMap<Integer, HashMap<Integer, java.awt.Color>> doInBackground() throws Exception {
        HashMap<Integer, HashMap<Integer, Color>> placeholders = new HashMap<Integer, HashMap<Integer, java.awt.Color>>();

        for (int i = 0; i < 8; i++) {
            HashMap<Integer, Color> single = new HashMap<Integer, java.awt.Color>();
            for (int j = 0; j < size; j++) {
                single.put(j, defaultColor);
            }

            placeholders.put(i, single);
        }

        return placeholders;
    }

    @Override
    protected void done() {
        try {
            board.setPlaceholder(get());
            board.setPainting(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
