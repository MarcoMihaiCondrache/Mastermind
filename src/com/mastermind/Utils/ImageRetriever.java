package com.mastermind.Utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

public class ImageRetriever extends SwingWorker<Icon, Void> {
    private final URL imageUrl;
    private final JLabel lblImage;

    public ImageRetriever(JLabel lblImage, URL imageUrl) {
        this.imageUrl = imageUrl;
        this.lblImage = lblImage;
    }

    @Override
    protected Icon doInBackground() throws Exception {
        Logger.debug("Started to retrieve image: " + imageUrl.getPath());

        return retrieveImage(imageUrl);
    }

    private Icon retrieveImage(URL imageUrl)
            throws IOException {
        InputStream is = imageUrl.openStream();
        ImageInputStream iis = ImageIO.createImageInputStream(is);
        Iterator<ImageReader> it =
                ImageIO.getImageReadersBySuffix("jpg");

        ImageReader reader = it.next();
        reader.setInput(iis);
        return new ImageIcon(reader.read(0));
    }

    @Override
    protected void done() {
        try {
            lblImage.setIcon(get());

            Logger.debug("Image retrieved: " + imageUrl.getPath());
        } catch (Exception ignore) {
            Logger.error("Failed to retrieve image: " + imageUrl.getPath());
        }
    }
}