package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
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
    int playTurn;
    int nextStartPlayerIndex;
    ArrayList<ArrayList<ArrayList<Object>>> playersHands; // Each player has a hand (list of cards)

    Cards deck;
    SingleRound round;
    MutipleTurn mutipleTurn;

    public GUI() {
        roleImage = new RoleImage("media/poker rules.png");
        players = new ArrayList<>();
        playersHands = new ArrayList<>();
        currentPlayerIndex = 0;
        currentNum = 0; // Initialize the number of turns taken
        potChips = 0;
        playTurn = 0;
        cardBackImage = new CardBackImage(imagePath);
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

            // Draw cards for the player and store in playersHands
            ArrayList<ArrayList<Object>> hand = player.drawCards(deck);
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
            Player currentPlayer = players.get(currentPlayerIndex);

            // Use the player's displayHand method to update card labels
            currentPlayer.displayHand(deck, cardLabels);

            // Enable action buttons
            int maxRaise = round.getMaxRaise();
            check.setEnabled(maxRaise <= 0);
            call.setEnabled(true);
            raise.setEnabled(true);
            fold.setEnabled(true);

            checkCard.setEnabled(false); // Disable after displaying cards

            // Log the displayed hand for debugging
            System.out.println("Player " + currentPlayer.getName() + "'s hand displayed: " + playersHands.get(currentPlayerIndex));
        });

        // player can make choice
        check.addActionListener(e -> {
            player.makeDecision("check", 0, 0, playersHands, currentPlayerIndex);
            round.checkChips(currentPlayerIndex);

            updatePokerPanel(player);

            // Check if players can advance
            if (round.checkAllIn() || (checkNextTurn() && currentNum >= round.getActivePlayers() - 1)) {
                currentNum = 0;
                mutipleTurn.nextTurn(false);
                resetPlayerDecisions();
                round.resetChipsRaise();
                playTurn++;
                currentPlayerIndex = round.nextPlayer(true);
            } else if (round.getActivePlayers() > 1 || currentNum < players.size()) {
                currentPlayerIndex = round.nextPlayer(false);
                updatePokerPanel(players.get(currentPlayerIndex));
                currentNum++;
            }


            for(JLabel cardLabel : cardLabels){
                cardLabel.setIcon(cardBackImage.getBackImage());
            }
        });

        call.addActionListener(e -> {
            int callAmount = round.callChips(currentPlayerIndex);
            if(callAmount > 0){
                int totalRaisedChips = round.getRaiseChips(currentPlayerIndex);
                int updateChips = player.makeDecision("call", callAmount, totalRaisedChips, playersHands, currentPlayerIndex);
                playerChips[currentPlayerIndex] = updateChips;
                potLabelText.setText("Pot: " + round.changePot(callAmount));
            }else{
                player.makeDecision("check", 0, 0, playersHands, currentPlayerIndex);
                round.checkChips(currentPlayerIndex);
                System.out.println("chips already match");
            }

            updatePokerPanel(player);

            // Check if players can advance
            if (round.checkAllIn() || (checkNextTurn() && currentNum >= round.getActivePlayers() - 1)) {
                currentNum = 0;
                mutipleTurn.nextTurn(false);
                resetPlayerDecisions();
                round.resetChipsRaise();
                playTurn++;
                currentPlayerIndex = round.nextPlayer(true);
            } else if (round.getActivePlayers() > 1 || currentNum < players.size()) {
                currentPlayerIndex = round.nextPlayer(false);
                updatePokerPanel(players.get(currentPlayerIndex));
                currentNum++;
            }

            // flip the card to be back
            for (JLabel cardLabel : cardLabels) {
                cardLabel.setIcon(cardBackImage.getBackImage());
            }
        });

        raise.addActionListener(e -> {
            int chipsToRaise = round.raiseChips(currentPlayerIndex);

            if(chipsToRaise > 0){
                int totalRaisedChips = round.getRaiseChips(currentPlayerIndex);
                int updateChips = player.makeDecision("raise", chipsToRaise, totalRaisedChips, playersHands, currentPlayerIndex);
                playerChips[currentPlayerIndex] = updateChips;
                potLabelText.setText("Pot: " + round.changePot(chipsToRaise));
            }else{
                System.out.println("No chips raised");
                return;
            }

            updatePokerPanel(player);

            // check if player all in
            if (round.checkAllIn()) {
                mutipleTurn.nextTurn(true);
                return;
            }

            // Check if players can advance
            if (round.checkAllIn() || (checkNextTurn() && currentNum >= round.getActivePlayers() - 1)) {
                currentNum = 0;
                mutipleTurn.nextTurn(false);
                resetPlayerDecisions();
                round.resetChipsRaise();
                playTurn++;
                currentPlayerIndex = round.nextPlayer(true);
            } else if (round.getActivePlayers() > 1 || currentNum < players.size()) {
                currentPlayerIndex = round.nextPlayer(false);
                updatePokerPanel(players.get(currentPlayerIndex));
                currentNum++;
            }

            // flip the card to be back
            for (JLabel cardLabel : cardLabels) {
                cardLabel.setIcon(cardBackImage.getBackImage());
            }
        });

        fold.addActionListener(e -> {
            player.makeDecision("fold", 0, 0, playersHands, currentPlayerIndex);
            round.foldCard(currentPlayerIndex);

            updatePokerPanel(player);

            // Check if players can advance
            if (round.checkAllIn() || (checkNextTurn() && currentNum >= round.getActivePlayers() - 1)) {
                currentNum = 0;
                mutipleTurn.nextTurn(false);
                resetPlayerDecisions();
                round.resetChipsRaise();
                playTurn++;
                currentPlayerIndex = round.nextPlayer(true);
            } else if (round.getActivePlayers() > 1 || currentNum < players.size()) {
                currentPlayerIndex = round.nextPlayer(false);
                updatePokerPanel(players.get(currentPlayerIndex));
                currentNum++;
            }

            // flip the card to be back
            for (JLabel cardLabel : cardLabels) {
                cardLabel.setIcon(cardBackImage.getBackImage());
            }
        });

        // Add buttons to optionPanel
        optionPanel.add(check);
        optionPanel.add(call);
        optionPanel.add(raise);
        optionPanel.add(fold);
        optionPanel.add(checkCard);


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

        // Action listener for the Start button
        startButton.addActionListener(e -> {
            int chips;
            int playerCount;

            // Parse and validate chips and player count input
            try {
                chips = Integer.parseInt(chipsField.getText());
                playerCount = Integer.parseInt(playerField.getText());
            } catch (NumberFormatException ex) {
                startAlert();
                return;
            }

            if (chips < 100 || chips > 999 || playerCount < 2 || playerCount > 8) {
                startAlert();
                return;
            }

            // Disable name editing and input fields after starting the game
            for (Player player : players) {
                player.setNameEditable(false);
            }
            playerField.setEditable(false);
            chipsField.setEditable(false);
            okPlayerButton.setEnabled(false);

            // Initialize river cards with card back images
            ArrayList<JLabel> riverCards = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                riverCards.add(new JLabel(cardBackImage.getBackImage()));
            }

            // Initialize MutipleTurn
            mutipleTurn = new MutipleTurn(deck, players, round, riverCards);

            // Remove the start panel
            mainPanel.remove(startPanel);

            // Update poker panel for the first player
            if (!players.isEmpty()) {
                updatePokerPanel(players.get(0));
            }

            // Set up the game view
            JPanel gamePanel = new JPanel();
            gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

            // Add vertical glue to center content
            gamePanel.add(Box.createVerticalGlue());

            // Create the river panel for card display
            JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            for (JLabel card : riverCards) {
                cardPanel.add(card); // Add all river cards (back side) to the panel
            }

            // Panel for displaying pot information
            JPanel potPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            potLabelText = new JLabel("Pot: 0");
            potPanel.add(potLabelText);

            // Add cardPanel and potPanel to gamePanel
            gamePanel.add(cardPanel);
            gamePanel.add(Box.createVerticalStrut(10)); // Space between cards and pot
            gamePanel.add(potPanel);
            gamePanel.add(Box.createVerticalGlue());

            // Add gamePanel to mainPanel
            mainPanel.add(gamePanel, BorderLayout.CENTER);

            // Refresh UI
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        return mainPanel;
    }


    // check if players can do the next turn
    private boolean checkNextTurn() {
        int maxRaise = round.getMaxRaise();
        ArrayList<Player> activePlayers = round.getActivePlayersList();

        for (Player player : activePlayers) {
            int playerIndex = players.indexOf(player);
            if (round.getRaiseChips(playerIndex) != maxRaise) {
                return false;
            }
        }
        return true;
    }

    // reset player decision for new turn
    public void resetPlayerDecisions(){
        currentNum = 0;
        currentPlayerIndex = 0;
        for (Player player : players) {
            if (player.isActive()) {
                player.resetDecision();
            }
        }
        System.out.println("Player decisions have been reset for the next turn.");

        updatePokerPanel(players.get(currentPlayerIndex));
    }

    // remove players
    public void removePlayer(){
        ArrayList<Player> playersToRemove = new ArrayList<>();

        for(int i = 0; i < players.size(); i++){
            Player player = players.get(i);

            // if this player didn't have any chips and didn't raise any chips
            if(player.getChips() == 0 && round.getRaiseChips(i) == 0){
                playersToRemove.add(player);
            }
        }

        // remove player
        players.removeAll(playersToRemove);

        // update playerPanel
        playerPanel.removeAll();
        for(Player player : players){
            playerPanel.add(player.getPlayerPanel());
        }

        playerPanel.revalidate();
        playerPanel.repaint();
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

