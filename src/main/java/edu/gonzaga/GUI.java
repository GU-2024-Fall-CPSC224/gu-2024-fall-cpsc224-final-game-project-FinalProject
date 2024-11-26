package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    JFrame mainWindowFrame;
    JPanel rolePanel;
    JPanel playerPanel;
    JPanel gamePanel;
    JPanel reminder;
    JPanel pokerContainerPanel;
    RoleImage roleImage;
    JButton okPlayerButton;

    String imagePath = "media/purple_back.jpg";
    CardBackImage cardBackImage;
    JLabel potLabelText;

    JTextField chipsField;
    JTextField playerField;

    ArrayList<Player> players;
    int currentPlayerIndex;
    int currentNum;
    int potChips;
    int[] playerChips;
    ArrayList<ArrayList<ArrayList<Object>>> playersHands; // Each player has a hand (list of cards)

    Cards deck;
    SingleRound round;

    public GUI() {
        roleImage = new RoleImage("media/poker rules.png");
        players = new ArrayList<>();
        playersHands = new ArrayList<>();
        currentPlayerIndex = 0;
        currentNum = 0; // Initialize the number of turns taken
        potChips = 0;
        cardBackImage = new CardBackImage(imagePath);
    }
    public static void main(String[] args) {
        GUI app = new GUI();
        app.runGUI();
    }

    void setGUI(){
        // Initialize the deck
        deck = new Cards("media");
        deck.initializeDeck();

        // set up the main window
        this.mainWindowFrame = new JFrame("Poker Game");
        this.mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainWindowFrame.setSize(1300, 1000);
        this.mainWindowFrame.setResizable(true);

        this.mainWindowFrame.setLocation(100, 100);
        this.mainWindowFrame.setLayout(new BorderLayout());


        // set up role panel
        rolePanel = getRolePanel();

        // set up player panel
        playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        // set up poker panel
        pokerContainerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pokerContainerPanel.add(new JLabel("Waiting for players..."));

        // set up game panel
        gamePanel = getGamePanel();

        // set up game reminder
        reminder = getReminder();

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
        okPlayerButton = new JButton("OK");

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
        playerChips = new int[playerCount];
        for(int i = 0; i < playerCount; i++) {
            playerChips[i] = chips;
        }

        nameAlert();
        playerPanel.removeAll();
        playerPanel.setPreferredSize(new Dimension(300, 1000));

        players.clear();
        playersHands.clear();

        for (int i = 0; i < playerCount; i++) {
            Player player = new Player("Player " + (i + 1), chips, "media/profile.png");
            players.add(player);
            ArrayList<ArrayList<Object>> hand = new ArrayList<>();

            playersHands.add(hand);
            playerPanel.add(player.getPlayerPanel());
        }

        // initial round after create players
        round = new SingleRound(players);

        playerPanel.revalidate();
        playerPanel.repaint();
    }

    private void updatePokerPanel(Player player) {
        pokerContainerPanel.removeAll();
        pokerContainerPanel.add(getPokerPanel(player));
        pokerContainerPanel.revalidate();
        pokerContainerPanel.repaint();
    }

    private JPanel getPokerPanel(Player player) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BorderLayout());

        // Panel for player's cards
        JPanel pokerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel[] cardLabels = new JLabel[2]; // Labels for the two cards

        // Initialize with card backs
        for (int i = 0; i < 2; i++) {
            JLabel cardBack = new JLabel(cardBackImage.getBackImage());
            cardLabels[i] = cardBack; // Save the card back label
            pokerPanel.add(cardBack); // Add card back to panel
        }

        // Panel for action buttons
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.X_AXIS));
        JButton check = new JButton("Check");
        JButton call = new JButton("Call");
        JButton raise = new JButton("Raise");
        JButton fold = new JButton("Fold");

        // Button to reveal cards or switch to the next player
        JButton checkCard = new JButton("Check Cards");
        checkCard.addActionListener(e -> {
            if (currentNum < players.size()) {

                // Draw two cards for the current player
                ArrayList<ArrayList<Object>> currentHand = players.get(currentPlayerIndex).drawCards(deck, cardLabels);
                playersHands.set(currentPlayerIndex, currentHand); // Update the player's hand in playersHands

                // after check cards, player can make decision
                check.setEnabled(true);
                call.setEnabled(true);
                raise.setEnabled(true);
                fold.setEnabled(true);

                // Change button to "Next Player"
                checkCard.setEnabled(false);

            } else {
                // All players have taken their turn
                checkCard.setEnabled(false); // Disable the button when done
            }
        });

        // player can make choice
        check.addActionListener(e -> {
            player.makeDecision("check", 0, playersHands, currentPlayerIndex);
            round.checkChips(currentPlayerIndex);

            for(JLabel cardLabel : cardLabels){
                cardLabel.setIcon(cardBackImage.getBackImage());
            }

            // next player
            if (round.getActivePlayers() > 1 || currentNum < players.size()) {
                currentPlayerIndex = round.nextPlayer();
                updatePokerPanel(players.get(currentPlayerIndex));
                currentNum++;
            }
        });

        call.addActionListener(e -> {
            player.makeDecision("call", 0, playersHands, currentPlayerIndex);
        });

        raise.addActionListener(e -> {
            int chipsToRaise = round.raiseChips(currentPlayerIndex);
            if(chipsToRaise > 0){
                int updateChips = player.makeDecision("raise", chipsToRaise, playersHands, currentPlayerIndex);
                playerChips[currentPlayerIndex] = updateChips;
                potLabelText.setText("Pot: " + round.changePot(chipsToRaise));
            }else{
                System.out.println("No chips raised");
            }

            // flip the card to be back
            for (JLabel cardLabel : cardLabels) {
                cardLabel.setIcon(cardBackImage.getBackImage());
            }

            // next player
            if (round.getActivePlayers() > 1) {
                currentPlayerIndex = round.nextPlayer();
                updatePokerPanel(players.get(currentPlayerIndex));
                System.out.println("Next Player: " + players.get(currentPlayerIndex).getName());
                currentNum++;
            }
        });

        fold.addActionListener(e -> {
            player.makeDecision("fold", 0, playersHands, currentPlayerIndex);
            round.foldCard(currentPlayerIndex);

            // flip the card to be back
            for (JLabel cardLabel : cardLabels) {
                cardLabel.setIcon(cardBackImage.getBackImage());
            }

            // next player
            if (round.getActivePlayers() > 1) {
                currentPlayerIndex = round.nextPlayer();
                updatePokerPanel(players.get(currentPlayerIndex));
                currentNum++;
            }
        });

        // Add buttons to optionPanel
        optionPanel.add(check);
        optionPanel.add(call);
        optionPanel.add(raise);
        optionPanel.add(fold);
        optionPanel.add(checkCard);

        // player must check cards first
        check.setEnabled(false);
        call.setEnabled(false);
        raise.setEnabled(false);
        fold.setEnabled(false);

        // Add pokerPanel and optionPanel to newPanel
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
                for (Player player : players) {
                    player.setNameEditable(false);
                }

                // after game start, you can not change anything
                playerField.setEditable(false);
                chipsField.setEditable(false);
                okPlayerButton.setEnabled(false);

                // Remove the start panel when the button is pressed
                mainPanel.remove(startPanel);

                if (!players.isEmpty()) {
                    updatePokerPanel(players.get(0));
                }

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
                potLabelText = new JLabel("Pot: 0");
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
        JDialog startDialog = new JDialog(mainWindowFrame, "Game Alert", true);
        startDialog.setSize(600, 100);
        startDialog.setLocationRelativeTo(mainWindowFrame);

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
        JDialog nameDialog = new JDialog(mainWindowFrame, "Name Alert", true);
        nameDialog.setSize(600, 100);
        nameDialog.setLocationRelativeTo(mainWindowFrame);

        JLabel alert = new JLabel("Please input your name on the right side, after start game, you can not change your name!!!!", SwingConstants.CENTER);

        // Create an OK button to close the dialog
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> nameDialog.dispose());

        // Add message and button to the dialog
        nameDialog.setLayout(new BorderLayout());
        nameDialog.add(alert, BorderLayout.CENTER);
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

