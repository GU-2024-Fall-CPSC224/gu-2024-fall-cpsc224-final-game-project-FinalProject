/*
 * Final project main driver class
 * 
 * 
 * Project Description:
 * 
 * 
 * Contributors: Christian Carrington
 * 
 * 
 * Copyright: 2024
 */
package edu.gonzaga;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import org.dyn4j.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** Main program class for launching your team's program. */
public class MainGame {
    private JFrame frame = new JFrame(); // the frame that opens when the program is run
    // panels in the border layout:
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel centerNorthPanel = new JPanel();
    private JPanel centerSouthPanel = new JPanel();
    private JPanel instructionsPanel = new JPanel();
    // the label that goes at the bottom:
    private JLabel title = new JLabel("TANKS");
    private JLabel names = new JLabel("By Christain Carrington, Abby Fewel, and Ayden Humphries");
    // buttons that go in the middle, in panel 5:
    private JButton start = new JButton("Start");
    private JButton howToPlay = new JButton("How to Play");
    // image that also goes in the middle, in panel 4:
    private JLabel tankPicture = new JLabel();
    private JLabel howToPlayFrameIcon = new JLabel();
    private JFileChooser tankImage = new JFileChooser();
    private JFileChooser instructionsFile = new JFileChooser(); // opens when "How to Play" is clicked
    private JFrame instructionsFrame = new JFrame();
    private JLabel instructionsLabel = new JLabel("label");

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainGame game = new MainGame();
                System.out.println("TANKS");
                game.formattingIntroScreen();
            }
        });
    }

    public void formattingIntroScreen() {
        setUpButtonListeners(); // to make Start and How to Play buttons listen

        // formats the frame:
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // defaults settings
        frame.setSize(750, 750);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        northPanel.setBackground(Color.GREEN); // colors
        centerPanel.setBackground(Color.lightGray);
        southPanel.setBackground(Color.white);
        northPanel.setPreferredSize(new Dimension(100, 100)); // dimensions of panels
        centerPanel.setPreferredSize(new Dimension(100, 100));
        southPanel.setPreferredSize(new Dimension(100, 100));
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);
        title.setFont(new Font("Algerian", Font.BOLD, 75)); // formats the title "TANKS"
        northPanel.add(title);
        southPanel.add(names);
        centerPanel.add(centerNorthPanel, BorderLayout.NORTH); // putting border layout in center panel
        centerPanel.add(centerSouthPanel, BorderLayout.SOUTH);
        ImageIcon tankStartScreenIcon = new ImageIcon("tank_intro_screen.jpg");
        tankPicture.setIcon(tankStartScreenIcon);
        centerNorthPanel.add(tankPicture);
        // centerNorthPanel.setIconImage(tankPicture.getImage()); // STILL WORKING ON
        // GETTING THIS PICTURE TO UPLOAD RIGHT :)
        centerSouthPanel.add(start);
        centerSouthPanel.add(howToPlay);
    }

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == howToPlay) { // STILL WORKING ON THIS
                    // this will open a frame that will have a file that has all of the instructions
                    // written out
                    ImageIcon howToPlayIcon = new ImageIcon("how_to_play.png"); // new icon with instructions
                    howToPlayFrameIcon.setIcon(howToPlayIcon); // set the icon for this label
                    instructionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    instructionsFrame.setSize(820, 800);
                    instructionsFrame.setLayout(new BorderLayout()); // Use a simple layout
                    instructionsPanel.setLayout(new BorderLayout()); // Let the image fill the panel
                    instructionsPanel.add(howToPlayFrameIcon, BorderLayout.CENTER); // Add the icon to the center
                    instructionsFrame.add(instructionsPanel, BorderLayout.CENTER); // Add the panel to the frame
                    instructionsFrame.setVisible(true);
                } else if (actionEvent.getSource() == start) {
                    // will start the game
                }
            }
        };
        start.addActionListener(buttonListener);
        howToPlay.addActionListener(buttonListener);
    }

}
