package edu.gonzaga;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Profile {
    private ImageIcon roleImage;

    public Profile(String imagesPath) {
        loadImages(imagesPath);
    }

    private void loadImages(String imagesPath){
        try {
            BufferedImage currPicture;
            String fileName = imagesPath;

            System.out.println("Loading image: " + fileName);
            currPicture = ImageIO.read(new File(imagesPath));
            Image dimg = currPicture.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
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
