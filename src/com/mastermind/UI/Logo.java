package com.mastermind.UI;

import com.mastermind.Utils.Logger;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Logo extends JPanel {

    private BufferedImage image;

    public Logo() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/logo.jpg"));
        } catch (IOException ex) {
            Logger.info("Image not found");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }
}