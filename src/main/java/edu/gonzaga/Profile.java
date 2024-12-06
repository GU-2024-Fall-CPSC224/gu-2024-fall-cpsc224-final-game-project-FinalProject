package edu.gonzaga;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Profile {
    private ImageIcon roleImage;
    private GUI gui;

    public Profile(String imagesPath, GUI gui) {
        this.gui = gui;
        loadImages(imagesPath);
    }

    private void loadImages(String imagesPath){
        try {
            BufferedImage currPicture;
            String fileName = imagesPath;

            System.out.println("Loading image: " + fileName);
            currPicture = ImageIO.read(new File(imagesPath));
            
            int ScaledWidth = gui.getScaledWidth(75);
            int ScaledHeight = gui.getScaledHeight(75);

            Image dimg = currPicture.getScaledInstance(ScaledWidth, ScaledHeight, Image.SCALE_SMOOTH);
            roleImage = new ImageIcon(dimg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ImageIcon getRoleImage() {
        return roleImage;
    }
}
