/*
 * Final project main driver class
 * 
 * 
 * Project Description:
 * 
 * 
 * Contributors: Christian Carrington, Ayden Humpries, Abby Fewel
 * 
 * 
 * Copyright: 2023
 */
package edu.gonzaga;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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
    // the label that goes at the bottom:
    private JLabel title = new JLabel("TANKS");
    private JLabel names = new JLabel("By Christain Carrington, Abby Fewel, and Ayden Humphries");
    // buttons that go in the middle, in panel 5:
    private JButton start = new JButton("Start");
    private JButton howToPlay = new JButton("How to Play");
    // image that also goes in the middle, in panel 4:
    private JLabel tankPicture = new JLabel();
    private JFileChooser tankImage = new JFileChooser();
    private JFileChooser instructionsFile = new JFileChooser(); // opens when "How to Play" is clicked
    private JFrame instructionsFrame = new JFrame();
    private JLabel instructionsLabel = new JLabel("label");

    public static void main(String[] args) {
        MainGame game = new MainGame();
        System.out.println("TANKS");
        game.formattingIntroScreen();
    }

    public void formattingIntroScreen() {
        setUpButtonListeners(); // to make Start and How to Play buttons listen

        // formats the frame:
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // defaults settings
        frame.setSize(500, 500);
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
        ImageIcon tankStartScreenIcon = new ImageIcon("TankIntroScreen.png");
        tankPicture.setIcon(tankStartScreenIcon);

        centerNorthPanel.add(tankPicture); // STILL WORKING ON GETTING THIS PICTURE TO UPLOAD RIGHT
        centerSouthPanel.add(start);
        centerSouthPanel.add(howToPlay);
        centerNorthPanel.add(tankPicture);
        centerSouthPanel.add(start);
        centerSouthPanel.add(howToPlay);
    }

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == howToPlay) {
                    instructionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    instructionsFrame.setSize(200, 200);
                    instructionsFrame.setLayout(null);
                    instructionsFrame.setVisible(true);
                } else if (actionEvent.getSource() == start) {
                    // will start the game
                }
            }
        };
        start.addActionListener(buttonListener);
        howToPlay.addActionListener(buttonListener);
    }

    public void howToPlayFrame() {
        // Figuring things out
        System.out.println("Do game things.");
    }
}
