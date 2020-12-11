package com.mastermind.UI;

import java.awt.*;
import javax.swing.*;

import com.mastermind.Game.Generator;
import com.mastermind.Game.Level;
import com.mastermind.Workers.IndicatorsWorker;
import com.mastermind.Workers.PlaceholderWorker;
import com.mastermind.Workers.SequenceWorker;

public class AppFrame extends JFrame {
    static final long serialVersionUID = 1L;
    static final int WINDOW_WIDTH = 500;
    static final int WINDOW_HEIGHT = 800;

    public Container appContainer;
    public Generator generator;
    public Board board;

    public AppFrame(Level level) {
        super("MasterMind");

        this.generator = new Generator(level);
        this.appContainer = this.getContentPane();
        this.board = new Board(this.appContainer, this, generator);

        this.appContainer.add(this.board);

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //workers
        SequenceWorker sequence = new SequenceWorker(generator.size, board);
        PlaceholderWorker placeholder = new PlaceholderWorker(generator.size, board, Color.GRAY);
        IndicatorsWorker indicators = new IndicatorsWorker(generator.size, board, new Color(72, 72, 72));

        sequence.execute();
        placeholder.execute();
        indicators.execute();
    }
}
