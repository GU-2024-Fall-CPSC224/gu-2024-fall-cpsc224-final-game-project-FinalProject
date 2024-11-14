package edu.gonzaga;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Cards {
    ArrayList<ImageIcon> cardImages;

    void loadImages(String imagesPath) {
        BufferedImage currPicture;
        cardImages.add(null);   // Slot 0 is kept empty (no blank die?)
        for( int i = 1; i < 7; i++) {
            try {
                String filename = imagesPath + "/D6-0" + i + ".png";
                // This shows the current working directory for your running program
                //System.out.println("Working Directory = " + System.getProperty("user.dir"));
                System.out.println("Loading image: " + filename);
                currPicture = ImageIO.read(new File(filename));
                Image dimg = currPicture.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                ImageIcon scaledImage = new ImageIcon(dimg);
                cardImages.add(scaledImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Cards(String imagesPath) {
        cardImages = new ArrayList<>(60);
        loadImages(imagesPath);
    }

    public ImageIcon getDieImage(int dieValue) {
        return cardImages.get(dieValue);
    }
}
