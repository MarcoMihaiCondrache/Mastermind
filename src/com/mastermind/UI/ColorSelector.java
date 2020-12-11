package com.mastermind.UI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import com.mastermind.Utils.Logger;

public class ColorSelector extends JDialog {
    static final long serialVersionUID = 4L;
    static final String[] colorsString = { "Rosso", "Blu", "Verde", "Giallo", "Bianco", "Arancione", "Magenta",
            "Nero" };

    private JButton saveButton = new JButton("Salva");
    private JButton closeButton = new JButton("Chiudi");
    private JPanel colorsPanel = new JPanel();
    private JPanel actionsPanel = new JPanel();
    private final JComboBox<String> colors = new JComboBox<String>(colorsString);

    public Color selectedColor = Color.RED;
    public boolean save = false;

    public ColorSelector(JFrame frame) {
        super(frame, "Select Color");

        frame.setEnabled(false);

        Logger.info("Dialog Created");

        this.setSize(300, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        colors.setPreferredSize(new Dimension(140, 20));

        Rectangle screen = frame.getGraphicsConfiguration().getBounds();

        this.setLocation(screen.x + (screen.width - (this.getWidth() / 2)) / 2,
                screen.y + (screen.height - (this.getHeight() / 2)) / 2);

        colorsPanel.add(colors);
        actionsPanel.add(saveButton);
        actionsPanel.add(closeButton);

        this.add(colorsPanel, BorderLayout.PAGE_START);
        this.add(actionsPanel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);

        ColorSelector selector = this;

        saveButton.addActionListener(e -> {
            save = true;
            selector.setVisible(false);
            selector.dispose();
        });

        closeButton.addActionListener(e -> {
            save = false;
            selector.setVisible(false);
            selector.dispose();
        });

        colors.addActionListener(e -> {
            switch (String.valueOf(colors.getSelectedItem())) {
                case "Rosso":
                    selectedColor = Color.RED;
                    break;
                case "Blu":
                    selectedColor = Color.BLUE;
                    break;
                case "Verde":
                    selectedColor = Color.GREEN;
                    break;
                case "Giallo":
                    selectedColor = Color.YELLOW;
                    break;
                case "Bianco":
                    selectedColor = Color.WHITE;
                    break;
                case "Arancione":
                    selectedColor = new Color(255, 69, 0);
                    break;
                case "Magenta":
                    selectedColor = Color.MAGENTA;
                    break;
                case "Nero":
                    selectedColor = Color.BLACK;
                    break;
            }
        });
    }
}
