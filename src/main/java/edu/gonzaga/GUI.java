package edu.gonzaga;

import javax.swing.*;
import java.awt.*;

public class GUI {
    JFrame mainWindowFrame;
    JPanel rolePanel = new JPanel();
    JPanel playerPanel = new JPanel();
    JPanel pokerPanel = new JPanel();
    JPanel gamePanel = new JPanel();
    JPanel reminder = new JPanel();
    RoleImage roleImage;

    JTextField chipsField;
    JTextField playerField;

    public GUI(){
        roleImage = new RoleImage("media/poker rules.png");
    }

    public static void main(String[] args) {
        GUI app = new GUI();
        app.runGUI();
    }

    void setGUI(){
        // set up the main window
        this.mainWindowFrame = new JFrame("Poker Game");
        this.mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainWindowFrame.setResizable(true);

        this.mainWindowFrame.setLocation(100, 100);
        this.mainWindowFrame.setSize(1300, 1000);
        this.mainWindowFrame.setLayout(new BorderLayout());


        // set up role panel
        this.rolePanel = getRolePanel();

        // set up player panel
        this.playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        // set up poker panel
        this.pokerPanel = getPokerPanel();

        // set up game panel
        this.gamePanel = getGamePanel();

        // set up game reminder
        this.reminder = getReminder();

        // input all panels in main window
        this.mainWindowFrame.add(this.rolePanel, BorderLayout.WEST);
        this.mainWindowFrame.add(this.playerPanel, BorderLayout.EAST);
        this.mainWindowFrame.add(this.pokerPanel, BorderLayout.SOUTH);
        this.mainWindowFrame.add(this.reminder, BorderLayout.NORTH);
        this.mainWindowFrame.add(this.gamePanel, BorderLayout.CENTER);
    }

    private JPanel getRolePanel() {
        JPanel newPanel = new JPanel(new BorderLayout());
        JPanel rolePanel = new JPanel();

        // set up the role
        rolePanel.setLayout(new BoxLayout(rolePanel, BoxLayout.Y_AXIS));
        rolePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel imageLabel = new JLabel(roleImage.getRoleImage());
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rolePanel.add(imageLabel);

        // set up chips
        JPanel chipsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel chipsLabel = new JLabel("Chips (100 - 999):");
        chipsField = new JTextField(5);
        chipsField.setPreferredSize(new Dimension(100, 25));
        chipsPanel.add(chipsLabel);
        chipsPanel.add(chipsField);
        chipsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rolePanel.add(chipsPanel);

        // set up the panel for player number
        JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel playerLabel = new JLabel("Players (1 - 8):");
        playerField = new JTextField(5);
        playerField.setPreferredSize(new Dimension(100, 25));
        playerPanel.add(playerLabel);
        playerPanel.add(playerField);
        playerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rolePanel.add(playerPanel);

        // set up ok button
        JButton okPlayerButton = new JButton("OK");
        okPlayerButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        okPlayerButton.addActionListener(e -> getPlayerPanel());

        rolePanel.add(Box.createVerticalStrut(10));
        rolePanel.add(okPlayerButton);
        newPanel.add(rolePanel, BorderLayout.NORTH);

        return newPanel;
    }



    private void getPlayerPanel() {
        playerPanel.removeAll();

        // get the player number and chips
        int playerCount;
        int chips;

        playerCount = Integer.parseInt(playerField.getText());
        chips = Integer.parseInt(chipsField.getText());

        for (int i = 0; i < playerCount; i++) {
            JPanel singlePlayerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            Profile profile = new Profile("media/profile.png");
            JLabel iconLabel = new JLabel(profile.getRoleImage());
            singlePlayerPanel.add(iconLabel);


            // set up the panel for chips and name
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

            JTextField playerNameField = new JTextField("Player Name");
            playerNameField.setPreferredSize(new Dimension(150, 25));

            JLabel chipsLabel = new JLabel("Chips: " + chips);

            infoPanel.add(playerNameField);
            infoPanel.add(chipsLabel);
            singlePlayerPanel.add(infoPanel);
            playerPanel.add(singlePlayerPanel);
        }
        playerPanel.revalidate();
        playerPanel.repaint();
    }


    private JPanel getPokerPanel() {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());

        return newPanel;
    }

    private JPanel getGamePanel() {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());

        return newPanel;
    }

    private JPanel getReminder() {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());

        return newPanel;
    }

    void runGUI(){
        System.out.println("Starting GUI app");
        setGUI();
        mainWindowFrame.revalidate();
        mainWindowFrame.repaint();
        mainWindowFrame.setVisible(true);
        System.out.println("Done in GUI app");
    }
}

