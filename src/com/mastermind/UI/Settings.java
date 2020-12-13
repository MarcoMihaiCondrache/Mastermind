package com.mastermind.UI;

import com.mastermind.Game.Level;
import com.mastermind.Utils.ImageRetriever;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Starting JFrame where user can select the level and start the game
 *
 * <p>
 * Here we create the AppFrame with the selected level
 * </p>
 */
public class Settings extends JFrame {
    static final long serialVersionUID = 0L;

    /**
     * Default window width
     */
    static final int WINDOW_WIDTH = 500;

    /**
     * Default window height
     */
    static final int WINDOW_HEIGHT = 800;

    /**
     * Available levels strings
     */
    static final String[] levels = {"Livello 1", "Livello 2", "Livello 3"};

    /**
     * Container where we'll store the content pane
     */
    public Container appContainer;

    /**
     * JLabel to display the game logo
     */
    public JLabel logo = new JLabel();

    /**
     * JComboBox instance for levels selector
     */
    public JComboBox<String> level = new JComboBox<String>(levels);

    /**
     * JButton instance to start the next frame
     */
    public JButton playButton = new JButton("Gioca subito!");

    /**
     * Settings constructor where we create the frame and load image
     *
     * @throws IOException Exception when logo isn't found
     */
    public Settings() throws IOException {
        super("MasterMind");

        Settings settings = this;
        GridBagConstraints constraints = new GridBagConstraints();
        ImageRetriever retriever = new ImageRetriever(logo, this.getClass().getResource("/logo.jpg"));

        // Load as possible as soon the image (async method)
        retriever.execute();

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weighty = 1;

        this.appContainer = this.getContentPane();

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        this.appContainer.setBackground(Color.WHITE);

        playButton.setFocusPainted(false);
        playButton.setBackground(Color.ORANGE);
        playButton.setBorderPainted(false);
        playButton.setPreferredSize(new Dimension(200, 40));
        playButton.addActionListener(actionEvent -> {
            settings.setVisible(false);
            settings.dispose();

            new AppFrame(Level.values()[level.getSelectedIndex()]);
        });


        this.add(logo, constraints);
        this.add(playButton, constraints);
        this.add(level, constraints);
    }
}
