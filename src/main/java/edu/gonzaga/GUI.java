package edu.gonzaga;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.IOException;

public class GUI {
    JFrame splashScreenFrame;

    JFrame mainWindowFrame;
    JPanel playerPanel;
    JPanel gamePanel;
    JPanel reminder;
    JPanel pokerContainerPanel;

    String imagePath = "media/purple_back.jpg";
    CardBackImage cardBackImage;
    JLabel potLabelText;
    JLabel notificationLabel;

    ArrayList<Player> players;
    int currentPlayerIndex;
    int currentNum;
    int potChips;
    int[] playerChips;
    int countTurn;
    int dealer;
    int totalRaisedChips;
    ArrayList<ArrayList<ArrayList<Object>>> playersHands; // Each player has a hand (list of cards)

    Cards deck;
    SingleRound round;
    MutipleTurn mutipleTurn;

    // music
    Clip splashClip;
    Clip mainClip;

    // splash screen
    private JTextField numPlayersField;
    private JTextField startingChipsField;

    private int numPlayers;
    private int startingChips;

    public GUI() {
        players = new ArrayList<>();
        playersHands = new ArrayList<>();
        currentPlayerIndex = 0;
        currentNum = 0; // Initialize the number of turns taken
        potChips = 0;
        cardBackImage = new CardBackImage(imagePath);
        countTurn = 0;
        dealer = 0;
        totalRaisedChips = 0;
    }

    void setSplashScreen() {
        splashScreenFrame = new JFrame("Dogs Playing Poker ... THE GAME!");
        splashScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        splashScreenFrame.setSize(1200, 700);
        splashScreenFrame.setResizable(false);
        splashScreenFrame.setLocationRelativeTo(null);

        ImageIcon backgroundImage = new ImageIcon("media/SplashScreen.png");

        JPanel splashPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        splashPanel.setLayout(null);

        numPlayersField = new JTextField();
        numPlayersField.setBounds(665, 370, 100, 30);
        numPlayersField.setOpaque(false);
        numPlayersField.setCursor(new Cursor(Cursor.HAND_CURSOR));
        numPlayersField.setBorder(new RoundedBorder(15, Color.BLACK, 2)); // Apply custom border

        splashPanel.add(numPlayersField);

        startingChipsField = new JTextField();
        startingChipsField.setBounds(615, 425, 150, 30); // Set location and size
        startingChipsField.setOpaque(false);
        startingChipsField.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startingChipsField.setBorder(new RoundedBorder(15, Color.BLACK, 2)); // Apply custom border

        splashPanel.add(startingChipsField);

        JButton startButton = new JButton();
        startButton.setBounds(505, 475, 170, 35);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.addActionListener(e -> {
            if (validateInputs()) {
                // showExitScreen();
                showPlayerNameInputDialog(numPlayers);
            }
        });

        splashPanel.add(startButton);
        splashScreenFrame.add(splashPanel);
        splashScreenFrame.setVisible(true);

        playAudio("media/gta_reduced.wav", true, 0.0f); // PLAY SPLASH SONG
    }

    private boolean validateInputs() { // used in splash screen for detecting inputs
        try {
            numPlayers = Integer.parseInt(numPlayersField.getText());
            startingChips = Integer.parseInt(startingChipsField.getText());
    
            if (numPlayers < 2 || numPlayers > 6) {
                JOptionPane.showMessageDialog(splashScreenFrame, "Number of players must be between 2 and 6.");
                return false;
            }
    
            if (startingChips < 100 || startingChips > 999) {
                JOptionPane.showMessageDialog(splashScreenFrame, "Starting chips must be between 100 and 999.");
                return false;
            }
    
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(splashScreenFrame, "Please enter valid numbers.");
            return false;
        }
    }
    
    private void showPlayerNameInputDialog(int numPlayers) {
        JDialog nameDialog = new JDialog(splashScreenFrame, "Enter Player Names", true);
        nameDialog.setSize(400, 300);
        nameDialog.setLayout(new BorderLayout());
    
        BackgroundPanel backgroundPanel = new BackgroundPanel("media/felt.png");
        backgroundPanel.setLayout(new GridLayout(numPlayers + 1, 2, 10, 10)); // +1 for the OK button
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
    
        JTextField[] playerNameFields = new JTextField[numPlayers];
    
        for (int i = 0; i < numPlayers; i++) {
            JLabel label = new JLabel("Player " + (i + 1) + ":");
            label.setForeground(Color.WHITE); // Set text color to white
            JTextField textField = new JTextField("Player " + (i + 1));
            textField.setForeground(Color.WHITE);
            textField.setOpaque(false);
            textField.setBorder(new RoundedBorder(15, Color.BLACK, 2)); // Apply custom border
            playerNameFields[i] = textField;
            backgroundPanel.add(label);
            backgroundPanel.add(textField);
        }
    
        JButton okButton = new JButton("OK");
        okButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        okButton.setOpaque(false);
        okButton.setContentAreaFilled(false);
        okButton.setBorderPainted(false);
        okButton.setForeground(Color.WHITE); // Set text color to white
        okButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font
        okButton.addActionListener(e -> {
            players.clear();
            for (int i = 0; i < numPlayers; i++) {
                Player player = new Player(playerNameFields[i].getText(), startingChips, "media/profile.png");
                players.add(player);
                System.out.println("Player " + (i + 1) + " name: " + playerNameFields[i].getText());
            }

            players.get(0).updateDealerStatus(true);

            round = new SingleRound(players);

            nameDialog.dispose();
            splashScreenFrame.dispose();
            stopAudio(splashClip); // STOP SONG ON CLOSE
            runGUI();
            mainWindowFrame.setVisible(true);
        });
    
        backgroundPanel.add(new JLabel()); // Empty cell to align the OK button
        backgroundPanel.add(okButton);
    
        nameDialog.add(backgroundPanel, BorderLayout.CENTER);
        nameDialog.setLocationRelativeTo(splashScreenFrame);
        nameDialog.setVisible(true);
    }

    void setGUI(){
        // Initialize the deck
        deck = new Cards("media");
        deck.initializeDeck();

        // set up the main window
        this.mainWindowFrame = new JFrame("Poker Game");
        this.mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainWindowFrame.setResizable(true);
        this.mainWindowFrame.setLayout(new BorderLayout());

        this.mainWindowFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        String imagePath = "media/table.png";
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            System.err.println("Background image not found: " + imagePath);
            return;
        } else {
            System.out.println("Background image found: " + imagePath);
        }


        BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath);
        backgroundPanel.setLayout(new BorderLayout());

        // set up role panel

        // set up player panel
        playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setOpaque(false);

        for (Player player : players) {
            JPanel playerPanelComponent = player.getPlayerPanel();
            playerPanelComponent.setOpaque(false);
            playerPanel.add(playerPanelComponent);
            player.setNameEditable(false);
        }

        // set up poker panel
        pokerContainerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pokerContainerPanel.add(new JLabel("Waiting for players..."));
        pokerContainerPanel.setOpaque(false);

        // set up game panel
        gamePanel = getGamePanel();
        gamePanel.setOpaque(false);

        // set up game reminder
        reminder = getNotification();
        reminder.setOpaque(false);

        // input all panels in main window
        backgroundPanel.add(this.playerPanel, BorderLayout.EAST);
        backgroundPanel.add(pokerContainerPanel, BorderLayout.SOUTH);
        backgroundPanel.add(this.reminder, BorderLayout.NORTH);
        backgroundPanel.add(this.gamePanel, BorderLayout.CENTER);

        this.mainWindowFrame.setContentPane(backgroundPanel);

        this.mainWindowFrame.revalidate();
        this.mainWindowFrame.repaint();

        playAudio("media/jazz_reduced.wav", false, -16.0f);
    }

    private void getPlayerPanel() {
        int chips;
        int playerCount;
            
        chips = startingChips;
        playerCount = numPlayers;
        
        playerChips = new int[playerCount];
        for(int i = 0; i < playerCount; i++) {
            playerChips[i] = chips;
        }

        playerPanel.removeAll();
        playerPanel.setPreferredSize(new Dimension(300, 1000));

        playersHands.clear();
        for (Player player : players) {
            // Draw cards for the player and store in playersHands
            ArrayList<ArrayList<Object>> hand = player.drawCards(deck);
            playersHands.add(hand);

            playerPanel.add(player.getPlayerPanel());
        }

        // initial round after create players
        round = new SingleRound(players);

        playerPanel.revalidate();
        playerPanel.repaint();
        playerPanel.setOpaque(false);
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
        newPanel.setOpaque(false);

        // Panel for player's cards
        JPanel pokerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pokerPanel.setOpaque(false);
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
            Player currentPlayer = players.get(currentPlayerIndex);
            player.makeDecision("check", 0, 0, currentPlayer);
            round.checkChips(currentPlayerIndex);

            updateReminder(currentPlayer.getName() + " has check.");

            updatePokerPanel(player);

            System.out.println("getActivePlayers()" + (round.getActivePlayers() - 1));
            // Check if players can advance
            if (round.checkAllIn() || (checkNextTurn() && currentNum >= countTurn)) {
                updateReminder("next turn.");
                currentNum = 0;
                mutipleTurn.updateRound(round);
                mutipleTurn.nextTurn(false);
                resetPlayerDecisions();
                round.resetChipsRaise();
                countTurn = round.getActivePlayers() - 1;
                System.out.println("countTurn: " + countTurn);
                currentPlayerIndex = round.nextPlayer(true);
                System.out.println("currentPlayerIndex: " + currentPlayerIndex);
            } else if (round.getActivePlayers() > 1 || currentNum < players.size()) {
                updateReminder(currentPlayer.getName()+ " has checked (0) " + totalRaisedChips + " next player " + players.get(currentPlayerIndex + 1).getName());
                currentPlayerIndex = round.nextPlayer(false);
                updatePokerPanel(players.get(currentPlayerIndex));
                currentNum++;
                System.out.println("currentNum: " + currentNum);
            }


            for(JLabel cardLabel : cardLabels){
                cardLabel.setIcon(cardBackImage.getBackImage());
            }
        });

        call.addActionListener(e -> {
            int callAmount = round.callChips(currentPlayerIndex);
            Player currentPlayer = players.get(currentPlayerIndex);
            if(callAmount > 0){
                totalRaisedChips = round.getRaiseChips(currentPlayerIndex);
                int updateChips = player.makeDecision("call", callAmount, totalRaisedChips, currentPlayer);
                playerChips[currentPlayerIndex] = updateChips;
                potLabelText.setText("Pot: " + round.changePot(callAmount));
            }else{
                player.makeDecision("check", 0, 0, currentPlayer);
                round.checkChips(currentPlayerIndex);
                System.out.println("chips already match");
            }

            updatePokerPanel(player);

            // Check if players can advance
            if (round.checkAllIn() || (checkNextTurn() && currentNum >= countTurn)) {
                updateReminder("next turn.");
                currentNum = 0;
                System.out.println("Before Next Turn, Check Pot: " + round.getPot());
                mutipleTurn.updateRound(round);
                mutipleTurn.nextTurn(false);
                resetPlayerDecisions();
                round.resetChipsRaise();
                countTurn = round.getActivePlayers() - 1;
                currentPlayerIndex = round.nextPlayer(true);
                System.out.println("currentPlayerIndex: " + currentPlayerIndex);
            } else if (round.getActivePlayers() > 1 || currentNum < players.size()) {
                updateReminder(currentPlayer.getName()+ " has called to " + totalRaisedChips + " next player " + players.get(currentPlayerIndex + 1).getName());
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
            Player currentPlayer = players.get(currentPlayerIndex);
            int chipsToRaise = round.raiseChips(currentPlayerIndex);

            if(chipsToRaise > 0){
                totalRaisedChips = round.getRaiseChips(currentPlayerIndex);
                int updateChips = player.makeDecision("raise", chipsToRaise, totalRaisedChips, currentPlayer);
                updateReminder(currentPlayer.getName() + " has raised chips to " + totalRaisedChips);
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
            if (round.checkAllIn() || (checkNextTurn() && currentNum >= countTurn)) {
                updateReminder("next turn.");
                currentNum = 0;
                mutipleTurn.updateRound(round);
                mutipleTurn.nextTurn(false);
                resetPlayerDecisions();
                round.resetChipsRaise();
                countTurn = round.getActivePlayers() - 1;
                currentPlayerIndex = round.nextPlayer(true);
                System.out.println("currentPlayerIndex: " + currentPlayerIndex);
            } else if (round.getActivePlayers() > 1 || currentNum < players.size()) {
                updateReminder(currentPlayer.getName()+ " has raised chips to " + totalRaisedChips + " next player " + players.get(currentPlayerIndex + 1).getName());
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
            Player currentPlayer = players.get(currentPlayerIndex);
            player.makeDecision("fold", 0, 0, currentPlayer);
            round.foldCard(currentPlayerIndex);

            if (round.getActivePlayers() == 1) {
                updateReminder(currentPlayer.getName() + " next round.");
                currentNum = 0;
                mutipleTurn.updateRound(round);
                mutipleTurn.singlePlayer();
                resetPlayerDecisions();
                round.resetChipsRaise();
                countTurn = round.getActivePlayers() - 1;
                currentPlayerIndex = round.nextPlayer(true);
                System.out.println("currentPlayerIndex: " + currentPlayerIndex);
                potLabelText.setText("Pot: 0");
                return;
            }

            updateReminder(currentPlayer.getName() + " has fold.");


            updatePokerPanel(player);

            // Check if players can advance
            if (round.checkAllIn() || (checkNextTurn() && currentNum >= countTurn)) {
                updateReminder("next turn.");
                currentNum = 0;
                mutipleTurn.updateRound(round);
                mutipleTurn.nextTurn(false);
                resetPlayerDecisions();
                round.resetChipsRaise();
                countTurn = round.getActivePlayers() - 1;
                currentPlayerIndex = round.nextPlayer(true);
                System.out.println("currentPlayerIndex: " + currentPlayerIndex);
            } else if (round.getActivePlayers() > 1 || currentNum < players.size()) {
                updateReminder(currentPlayer.getName()+ " has fold." + " next player " + players.get(currentPlayerIndex + 1).getName());
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
        mainPanel.setOpaque(false);

        // Initialize river cards with card back images
        ArrayList<JLabel> riverCards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            riverCards.add(new JLabel(cardBackImage.getBackImage()));
        }

        // Initialize MutipleTurn
        mutipleTurn = new MutipleTurn(deck, players, round, riverCards, mainWindowFrame);

        // Update poker panel for the first player
        if (!players.isEmpty()) {
            updatePokerPanel(players.get(0));
        }

        // Set up the game view
        JPanel gamePanel = new JPanel();
        gamePanel.setOpaque(false);
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        // Add vertical glue to center content
        gamePanel.add(Box.createVerticalGlue());

        // Create the river panel for card display
        JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cardPanel.setOpaque(false);
        for (JLabel card : riverCards) {
            cardPanel.add(card); // Add all river cards (back side) to the panel
        }

        // Panel for displaying pot information
        JPanel potPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        potPanel.setOpaque(false);
        potLabelText = new JLabel("Pot: 0");
        potPanel.add(potLabelText);
        countTurn = round.getActivePlayers() - 1;
        System.out.println("countTurn: " + countTurn);

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

        getPlayerPanel();

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
            player.setNameEditable(false);
        }

        playerPanel.revalidate();
        playerPanel.repaint();
    }

    private JPanel getNotification() {
        JPanel newPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout to center components
        newPanel.setPreferredSize(new Dimension(300, 150));

        notificationLabel = new JLabel("Welcome to POKER game !!!!! Enjoy your time");

        // Use GridBagConstraints to center the label
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center within the cell

        newPanel.add(notificationLabel, gbc);

        return newPanel;
    }

    public void updateReminder(String message) {
        if (notificationLabel != null) {
            notificationLabel.setText(message);
        }
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

    // AUDIO FUNCTIONS
    private void playAudio(String filePath, boolean isSplash, float volume) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // set volume
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);

            clip.start();
            if (isSplash) {
                splashClip = clip;
            } else {
                mainClip = clip;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void stopAudio(Clip clip) {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    // EXIT SCREEN

    private void showExitScreen() {
        JDialog exitDialog = new JDialog(splashScreenFrame, "Exit Screen", true);
        exitDialog.setSize(700, 500);
        exitDialog.setLayout(new BorderLayout());

        ExitScreen exitScreen = new ExitScreen();
        exitDialog.add(exitScreen, BorderLayout.CENTER);

        exitDialog.setLocationRelativeTo(splashScreenFrame);
        exitDialog.setVisible(true);
    }
}

