package edu.gonzaga;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CardBackImage {
    ImageIcon backImage;
    private GUI gui; // PRIVATE GUI FOR SCALING

    void loadBack(String imagesPath) {
        try {
            BufferedImage currPicture;
            System.out.println("Loading image: " + imagesPath);
            currPicture = ImageIO.read(new File(imagesPath));

            int ScaledWidth = gui.getScaledWidth(170);
            int ScaledHeight = gui.getScaledHeight(225);

            Image dimg = currPicture.getScaledInstance(ScaledWidth, ScaledHeight, Image.SCALE_SMOOTH);
            backImage = new ImageIcon(dimg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CardBackImage(String imagesPath, GUI gui) {
        this.gui = gui;
        loadBack(imagesPath);
    }

    public ImageIcon getBackImage() {
        return backImage;
    }
}

