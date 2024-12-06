package edu.gonzaga;

import javax.swing.*;
import java.awt.*;

public class ExitScreen extends JPanel {
    public ExitScreen() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(700, 500));

        BackgroundPanel backgroundPanel = new BackgroundPanel("media/exit.png");
        backgroundPanel.setLayout(new BorderLayout());

        this.add(backgroundPanel, BorderLayout.CENTER);
    }
}
