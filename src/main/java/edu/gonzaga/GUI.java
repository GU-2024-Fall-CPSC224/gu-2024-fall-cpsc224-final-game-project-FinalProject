package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    JFrame mainWindowFrame;
    JPanel rolePanel = new JPanel();
    JPanel playerPanel = new JPanel();
    JPanel gamePanel = new JPanel();
    JPanel reminder = new JPanel();
    RoleImage roleImage;

    JLabel potLabelText;

    JTextField chipsField;
    JTextField playerField;

    // List to hold player name fields
    ArrayList<JTextField> playerNameFields = new ArrayList<>();

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
        JPanel pokerContainerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pokerContainerPanel.add(getPokerPanel());

        // set up game panel
        this.gamePanel = getGamePanel();

        // set up game reminder
        this.reminder = getReminder();

        // input all panels in main window
        this.mainWindowFrame.getContentPane().add(this.rolePanel, BorderLayout.WEST);
        this.mainWindowFrame.getContentPane().add(this.playerPanel, BorderLayout.EAST);
        this.mainWindowFrame.getContentPane().add(pokerContainerPanel, BorderLayout.SOUTH);
        this.mainWindowFrame.getContentPane().add(this.reminder, BorderLayout.NORTH);
        this.mainWindowFrame.getContentPane().add(this.gamePanel, BorderLayout.CENTER);
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
        JLabel playerLabel = new JLabel("Players (2 - 8):");
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
        int chips;
        int playerCount;
        try {
            chips = Integer.parseInt(chipsField.getText());
            playerCount = Integer.parseInt(playerField.getText());
        } catch (NumberFormatException ex) {
            startAlert();
            return;
        }

        // Validate the input values for chips and player count
        if (chips < 100 || chips > 999 || playerCount < 2 || playerCount > 8) {
            startAlert();
            return;
        }

        nameAlert();
        playerPanel.removeAll();
        playerPanel.setPreferredSize(new Dimension(300, 1000));

        for (int i = 0; i < playerCount; i++) {
            JPanel singlePlayerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            Profile profile = new Profile("media/profile.png");
            JLabel iconLabel = new JLabel(profile.getRoleImage());
            singlePlayerPanel.add(iconLabel);


            // set up the panel for chips and name
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

            JTextField playerNameField = new JTextField("Player Name");
            playerNameField.setPreferredSize(new Dimension(150, 25));
            playerNameFields.add(playerNameField); // add player name in array list

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
        newPanel.setLayout(new BorderLayout());

        // set up the panel for player's cards
        JPanel pokerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String imagePath = "media/purple_back.jpg";
        CardBackImage cardBackImage = new CardBackImage(imagePath);
        for (int i = 0; i < 2; i++) {
            JLabel cardBack = new JLabel(cardBackImage.getBackImage());
            pokerPanel.add(cardBack);
        }

        // set up the panel for options
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.X_AXIS));
        JButton check = new JButton("Check");
        JButton call = new JButton("Call");
        JButton raise = new JButton("Raise");
        JButton fold = new JButton("Fold");
        optionPanel.add(check);
        optionPanel.add(call);
        optionPanel.add(raise);
        optionPanel.add(fold);

        newPanel.add(pokerPanel, BorderLayout.NORTH);
        newPanel.add(optionPanel, BorderLayout.SOUTH);

        return newPanel;
    }

    private JPanel getGamePanel() {
        // Main panel to hold the start button initially
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Set up the start panel with a Start button
        JPanel startPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(200, 100));


        startPanel.add(startButton);

        // Add the start panel to the main panel
        mainPanel.add(startPanel, BorderLayout.CENTER);

        // Set up action listener for the Start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int chips;
                int playerCount;
                try {
                    chips = Integer.parseInt(chipsField.getText());
                    playerCount = Integer.parseInt(playerField.getText());
                } catch (NumberFormatException ex) {
                    startAlert();
                    return;
                }

                // Validate the input values for chips and player count
                if (chips < 100 || chips > 999 || playerCount < 2 || playerCount > 8) {
                    startAlert();
                    return;
                }

                // Disable name editing after starting the game
                for (JTextField playerNameField : playerNameFields) {
                    playerNameField.setEditable(false);
                }

                // Remove the start panel when the button is pressed
                mainPanel.remove(startPanel);

                // Set up the panel for the game view
                JPanel gamePanel = new JPanel();
                gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS)); // Vertical layout for centering

                // Add vertical glue to push content to center
                gamePanel.add(Box.createVerticalGlue());

                // Set up the panel for the "river" (cards display)
                JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

                // Load card back image
                String imagePath = "media/purple_back.jpg";
                CardBackImage cardBackImage = new CardBackImage(imagePath);

                // Create the river by adding five card back images with flip buttons below each
                for (int i = 0; i < 5; i++) {
                    // Panel to hold the card back and the flip button
                    JPanel cardContainer = new JPanel();
                    cardContainer.setLayout(new BoxLayout(cardContainer, BoxLayout.Y_AXIS));

                    JLabel cardBack = new JLabel(cardBackImage.getBackImage());
                    JButton flipButton = new JButton("Flip");
                    flipButton.setEnabled(false);

                    cardBack.setAlignmentX(Component.CENTER_ALIGNMENT);
                    flipButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                    // Add the card back and flip button to the card container
                    cardContainer.add(cardBack);
                    cardContainer.add(Box.createVerticalStrut(5));
                    cardContainer.add(flipButton);

                    // Add the container to the cardPanel
                    cardPanel.add(cardContainer);
                }

                // Set up the panel for pot
                JPanel potPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JLabel potLabel = new JLabel("Pot: ");
                potLabelText = new JLabel("0");
                potPanel.add(potLabel);
                potPanel.add(potLabelText);

                // Add cardPanel and potPanel to gamePanel vertically
                gamePanel.add(cardPanel);
                gamePanel.add(Box.createVerticalStrut(10)); // Add space between cards and pot
                gamePanel.add(potPanel);

                // Add vertical glue to push content to center
                gamePanel.add(Box.createVerticalGlue());

                // Add the gamePanel to the mainPanel
                mainPanel.add(gamePanel, BorderLayout.CENTER);

                // Refresh the mainPanel to show the updated components
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        return mainPanel;
    }



    private JPanel getReminder() {
        JPanel newPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout to center components
        newPanel.setPreferredSize(new Dimension(300, 150));

        JLabel reminderLabel = new JLabel("Initial Reminder Text");

        // Use GridBagConstraints to center the label
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center within the cell

        newPanel.add(reminderLabel, gbc);

        return newPanel;
    }

    private void startAlert(){
        // Create the dialog to show the Farkle message
        JDialog startDialog = new JDialog(mainWindowFrame, "Game Alert", true);
        startDialog.setSize(600, 100);
        startDialog.setLocationRelativeTo(mainWindowFrame);

        // Create a label with a Farkle message
        JPanel alertPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel alert = new JLabel("Please set up correct chips and player number on the left side before you start game" , SwingConstants.CENTER);
        JLabel alertNextLine = new JLabel("(after you start game, you can not change player name, chips and player number)", SwingConstants.CENTER);
        alertPanel.add(alert);
        alertPanel.add(alertNextLine);

        // Create an OK button to close the dialog
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> startDialog.dispose());

        // Add message and button to the dialog
        startDialog.setLayout(new BorderLayout());
        startDialog.add(alertPanel, BorderLayout.CENTER);
        startDialog.add(okButton, BorderLayout.SOUTH);

        // Show the dialog
        startDialog.setVisible(true);
    }

    private void nameAlert(){
        // Create the dialog to show the Farkle message
        JDialog nameDialog = new JDialog(mainWindowFrame, "Name Alert", true);
        nameDialog.setSize(600, 100);
        nameDialog.setLocationRelativeTo(mainWindowFrame);

        // Create a label with a Farkle message
        JLabel farkle = new JLabel("Please input your name on the right side, after start game, you can not change your name!!!!", SwingConstants.CENTER);

        // Create an OK button to close the dialog
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> nameDialog.dispose());

        // Add message and button to the dialog
        nameDialog.setLayout(new BorderLayout());
        nameDialog.add(farkle, BorderLayout.CENTER);
        nameDialog.add(okButton, BorderLayout.SOUTH);

        // Show the dialog
        nameDialog.setVisible(true);
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

