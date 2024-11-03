package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainGameTest {
    @Test
    void alwaysTrue() {
        Assertions.assertTrue(true);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(1100, 650);
        try {
            f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("project design documents/candyLandBoard.jpeg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.setVisible(true);
    }
}
