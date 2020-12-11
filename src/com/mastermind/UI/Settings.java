package com.mastermind.UI;

import com.mastermind.Game.Level;
import com.mastermind.Utils.ImageRetriever;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Settings extends JFrame {
    static final long serialVersionUID = 0L;
    static final int WINDOW_WIDTH = 500;
    static final int WINDOW_HEIGHT = 800;
    static final String[] levels = {"Livello 1", "Livello 2", "Livello 3"};

    public Container appContainer;
    public JLabel logo = new JLabel();
    public JComboBox<String> level = new JComboBox<String>(levels);
    public JButton playButton = new JButton("Gioca subito!");

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
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                settings.setVisible(false);
                settings.dispose();

                new AppFrame(Level.values()[level.getSelectedIndex()]);
            }
        });


        this.add(logo, constraints);
        this.add(playButton, constraints);
        this.add(level, constraints);
    }
}
