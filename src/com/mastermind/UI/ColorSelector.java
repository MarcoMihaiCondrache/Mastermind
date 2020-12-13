package com.mastermind.UI;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog to allow the user to select the color
 * for the placeholder
 */
public class ColorSelector extends JDialog {
    static final long serialVersionUID = 4L;
    static final String[] colorsString = {"Rosso", "Blu", "Verde", "Giallo", "Bianco", "Arancione", "Magenta",
            "Nero"};

    private final JComboBox<String> colors = new JComboBox<String>(colorsString);

    /**
     * Color instance to save the selected color by the user
     */
    public Color selectedColor = Color.RED;

    /**
     * Bool to check if the user pressed the save button
     */
    public boolean save = false;

    /**
     * Dialog constructor where we create the UI and prepare the dialog
     *
     * @param frame Frame that creates the dialog
     */
    public ColorSelector(JFrame frame) {
        super(frame, "Select Color");

        JButton saveButton = new JButton("Salva");
        JButton closeButton = new JButton("Chiudi");
        JPanel colorsPanel = new JPanel();
        JPanel actionsPanel = new JPanel();

        //disable frame
        frame.setEnabled(false);

        this.setSize(300, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setScreenPosition(frame);

        colors.setPreferredSize(new Dimension(140, 20));

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
            selectedColor = itemToColor(String.valueOf(colors.getSelectedItem()));
        });
    }

    private void setScreenPosition(JFrame frame) {
        Rectangle screen = frame.getGraphicsConfiguration().getBounds();

        // Center dialog in working screen
        this.setLocation(screen.x + (screen.width - (this.getWidth() / 2)) / 2,
                screen.y + (screen.height - (this.getHeight() / 2)) / 2);
    }

    private Color itemToColor(String item) {
        switch (item) {
            case "Rosso":
                return Color.RED;
            case "Blu":
                return Color.BLUE;
            case "Verde":
                return Color.GREEN;
            case "Giallo":
                return Color.YELLOW;
            case "Bianco":
                return Color.WHITE;
            case "Arancione":
                return new Color(255, 69, 0);
            case "Magenta":
                return Color.MAGENTA;
            case "Nero":
                return Color.BLACK;
            default:
                return Color.GRAY;
        }
    }
}
