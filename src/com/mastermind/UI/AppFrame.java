package com.mastermind.UI;

import com.mastermind.Game.Generator;
import com.mastermind.Game.Level;
import com.mastermind.Workers.IndicatorsWorker;
import com.mastermind.Workers.PlaceholderWorker;
import com.mastermind.Workers.SequenceWorker;

import javax.swing.*;
import java.awt.*;

/**
 * Main Game class: here the board is created and the workers are started
 *
 * <p>
 * We create a new Generator for the level and define
 * frame settings like size and close operation
 * </p>
 */
public class AppFrame extends JFrame {
    static final long serialVersionUID = 1L;

    /**
     * Default window width
     */
    static final int WINDOW_WIDTH = 500;

    /**
     * Default window height
     */
    static final int WINDOW_HEIGHT = 800;

    /**
     * Container field: it will contain the content pane where we will
     * add the board
     */
    public Container appContainer;

    /**
     * Generator instance to define sizes based on the user-selected level
     */
    public Generator generator;

    /**
     * Board instance where all the draw starts
     */
    public Board board;

    /**
     * AppFrame constructor where we create the board and the generator
     *
     * <p>
     * Here the workers are started
     * </p>
     *
     * @param level The level selected by the user
     */
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
