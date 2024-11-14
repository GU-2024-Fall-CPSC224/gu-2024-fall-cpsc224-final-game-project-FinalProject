package edu.gonzaga;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RoleImage {
    private ImageIcon roleImage;

    // Constructor to load an image from the provided path
    public RoleImage(String imagesPath) {
        loadImage(imagesPath);
    }

    // Method to load and scale the image
    private void loadImage(String imagesPath) {
        try {
            System.out.println("Loading image: " + imagesPath);
            BufferedImage currPicture = ImageIO.read(new File(imagesPath));
            Image dimg = currPicture.getScaledInstance(300, 500, Image.SCALE_SMOOTH);
            roleImage = new ImageIcon(dimg);
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            roleImage = null; // Set to null if loading fails
        }
    }

    // Getter to retrieve the loaded image
    public ImageIcon getRoleImage() {
        return roleImage;
    }
}

