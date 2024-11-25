package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingPlayer extends JPanel implements ActionListener {
    private Image image;
    private int x = 0;
    private int y = 0;

    ImageIcon pinkIcon = new ImageIcon("project design documents/Picture5.jpg");
    ImageIcon purpleIcon = new ImageIcon("project design documents/Picture6.jpg");
    ImageIcon redIcon = new ImageIcon("project design documents/Picture7.jpg");
    ImageIcon yellowIcon = new ImageIcon("project design documents/Picture8.jpg");
    ImageIcon boardIcon = new ImageIcon("project design documents/board2.jpg");
    public MovingPlayer(ImageIcon icon) {
        image = icon.getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        x += 1;
        y += 1;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, x, y, this);
    }
}
