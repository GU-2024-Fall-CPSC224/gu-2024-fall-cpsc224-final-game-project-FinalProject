package edu.gonzaga;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CardBackImage {
    ImageIcon backImage;

    void loadBack(String imagesPath) {
        try {
            BufferedImage currPicture;
            System.out.println("Loading image: " + imagesPath);
            currPicture = ImageIO.read(new File(imagesPath));
            Image dimg = currPicture.getScaledInstance(170, 255, Image.SCALE_SMOOTH);
            backImage = new ImageIcon(dimg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CardBackImage(String imagesPath) {
        loadBack(imagesPath);
    }

    public ImageIcon getBackImage() {
        return backImage;
    }
}

