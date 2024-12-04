package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SingleRound {
    private int pot;
    private int[] saveChipsRaise;
    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private int activePlayers;
    private int playTurn;
    private int flippedCardsCount;
    private int nextStartPlayerIndex;


    public SingleRound(ArrayList<Player> players) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.activePlayers = players.size();
        this.playTurn = 0;
        this.flippedCardsCount = 0;
        this.nextStartPlayerIndex = 0;
        saveChipsRaise = new int[players.size()];
        for (int i = 0; i < saveChipsRaise.length; i++) {
            saveChipsRaise[i] = 0;
        }
        this.pot = 0;
    }

    // fold cards
    public void foldCard(int currentPlayerIndex) {
        Player currentPlayer = players.get(currentPlayerIndex);
        if (currentPlayer.isActive()) {
            currentPlayer.setActive(false);
            saveChipsRaise[currentPlayerIndex] = 0;
            activePlayers--;
            System.out.println("Player " + currentPlayer.getName() + " has folded. Active players remaining: " + activePlayers);
        }

        if (activePlayers == 1) {
            checkWinner();
        }
    }

    // check 0 chip
    public void checkChips(int currentPlayerIndex) {
        saveChipsRaise[currentPlayerIndex] = 0;
    }

    // raise chips
    public int raiseChips(int currentPlayerIndex) {
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("Current player: " + currentPlayer.getName());
        if(!currentPlayer.isActive()){
            return 0;
        }

        int chipsRaise = raiseChipsDialog(currentPlayer);
        if (chipsRaise == -1) {
            // Cancel was pressed; no chips were raised, and no action is taken
            System.out.println("Raise canceled. No changes made.");
            return -1; // Indicate that no raise was made
        }

        // Add the raised chips to the player's total contribution
        saveChipsRaise[currentPlayerIndex] += chipsRaise;

        return chipsRaise;
    }

    // call chips ( match the max raised chips)
    public int callChips(int currentPlayerIndex) {
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("Current player: " + currentPlayer.getName());
        if(!currentPlayer.isActive()){
            return 0;
        }

        // find the max amount of chips
        int maxRaise = getMaxRaise(); // Get the highest raise so far
        int currentRaise = saveChipsRaise[currentPlayerIndex]; // Player's current contribution
        int callAmount = maxRaise - currentRaise; // Additional amount needed to match maxRaise


        // if player already match the max amount
        if(callAmount <= 0){
            saveChipsRaise[currentPlayerIndex] = 0;
            return 0;
        }

        if (callAmount > currentPlayer.getChips()) {
            callAmount = currentPlayer.getChips(); // all in
            System.out.println("Player " + currentPlayer.getName() + " goes all in with " + callAmount + " chips.");
        } else {
            System.out.println("Player " + currentPlayer.getName() + " calls with " + callAmount + " chips.");
        }

        saveChipsRaise[currentPlayerIndex] += callAmount;
        currentPlayer.updateChips(currentPlayer.getChips() - callAmount);
        return callAmount;
    }

    public int getMaxRaise() {
        int maxRaise = 0;
        for(int chips: saveChipsRaise){
            if(chips >= maxRaise){
                maxRaise = chips;
            }
        }
        return maxRaise;
    }

    public int changePot(int chipsRaise) {
        pot += chipsRaise;
        return pot;
    }

    public int resetPot(){
        return pot = 0;
    }

    public int getRaiseChips(int currentPlayerIndex) {
        return saveChipsRaise[currentPlayerIndex];
    }

    public boolean checkRaiseChips() {
        Integer activePlayerRaise = null;
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (player.isActive()) {
                if (activePlayerRaise == null) {
                    activePlayerRaise = saveChipsRaise[i];
                } else if (!activePlayerRaise.equals(saveChipsRaise[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetChipsRaise() {
        for (int i = 0; i < saveChipsRaise.length; i++) {
            saveChipsRaise[i] = 0;
        }
        System.out.println("saveChipsRaise has been reset to 0 for all players.");
    }

    // check the only one player is winner
    public void checkWinner() {
        System.out.println("Checking for winner...");
        System.out.println("active player: " + activePlayers);
        if (activePlayers == 1) {
            for (Player player : players) {
                if (player.isActive()) {
                    JOptionPane.showMessageDialog(null, player.getName() + " wins the pot!");
                    player.updateChips(player.getChips() + pot);
                }
            }
        }
    }

    // switch to next player
    public int nextPlayer(boolean isNewTurn) {
        if (isNewTurn) {
            currentPlayerIndex = 0; // Reset to the first player
            System.out.println("New turn started. Starting from player index: " + currentPlayerIndex);
        } else {
            do {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                System.out.println("Next player index: " + currentPlayerIndex + " (" + players.get(currentPlayerIndex).getName() + ")");
            } while (!players.get(currentPlayerIndex).isActive());
        }
        return currentPlayerIndex;
    }

    public ArrayList<Player> getActivePlayersList() {
        ArrayList<Player> activePlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.isActive() && player.getChips() > 0) {
                activePlayers.add(player);
            }
        }
        return activePlayers;
    }

    public int getActivePlayers() {
        return getActivePlayersList().size();
    }

    public int raiseChipsDialog(Player currentPlayer) {
        int[] chipsRaised = {-1};
        JDialog raiseDialog = new JDialog((Frame) null, "Raise Chips", true);
        raiseDialog.setSize(600, 200);
        raiseDialog.setLocationRelativeTo(null);

        // create the text field that player can input chips
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel promptLabel = new JLabel("Enter the number of chips to raise (you have " + currentPlayer.getChips() + " chips):");
        JTextField raiseInput = new JTextField(4);
        inputPanel.add(promptLabel);
        inputPanel.add(raiseInput);

        // create the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton raiseButton = new JButton("Raise Chips");
        JButton cancelButton = new JButton("Cancel");

        raiseButton.addActionListener(e -> {
            String input = raiseInput.getText();
            try{
                int chips = Integer.parseInt(input); // Chips being added
                int maxRaise = getMaxRaise();
                int currentRaise = getRaiseChips(currentPlayerIndex);

                // update chips after player put into the pot
                if (chips > 0 && (chips + currentRaise) >= maxRaise && chips <= currentPlayer.getChips()) {
                    chipsRaised[0] = chips;
                    raiseDialog.dispose();
                }else if(chips > currentPlayer.getChips()){
                    JOptionPane.showMessageDialog(raiseDialog,
                            "You cannot raise more than your current chips (" + currentPlayer.getChips() + ").",
                            "Invalid Raise",
                            JOptionPane.ERROR_MESSAGE);
                }else if((chips + currentRaise) < maxRaise){
                    JOptionPane.showMessageDialog(raiseDialog,
                            "You cannot raise less than max chips (" + getMaxRaise() + ").",
                            "Invalid Raise",
                            JOptionPane.ERROR_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(raiseDialog,
                            "You must raise a positive amount.",
                            "Invalid Raise",
                            JOptionPane.ERROR_MESSAGE);
                }
            }catch (NullPointerException ex){
                JOptionPane.showMessageDialog(null, "Please enter a valid number!");
            }
        });

        cancelButton.addActionListener(e -> {
            System.out.println("Cancelled raising chips");
            raiseDialog.dispose();
        });

        buttonPanel.add(raiseButton);
        buttonPanel.add(cancelButton);

        raiseDialog.setLayout(new BorderLayout());
        raiseDialog.add(inputPanel, BorderLayout.CENTER);
        raiseDialog.add(buttonPanel, BorderLayout.SOUTH);

        raiseDialog.setVisible(true);
        return chipsRaised[0];
    }

    public int getPot() {
        return pot;
    }

    public boolean checkAllIn() {
        for (Player player : players) {
            if (player.isActive() && player.getChips() > 0) {
                return false;
            }
        }
        return true;
    }

    // Start a new round
    public void startNewRound(Cards deck, int nextStartPlayerIndex) {
        System.out.println("Setting up a new round...");
        deck.initializeDeck();
        resetChipsRaise();
        playTurn = 0;
        flippedCardsCount = 0;
        pot = 0; // Reset the pot

        // Update players' status and draw new cards
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            if (player.getChips() > 0) {
                player.setActive(true);
                player.clearHand(); // Clear old cards
                ArrayList<ArrayList<Object>> newHand = player.drawCards(deck); // Draw new cards
                System.out.println(player.getName() + "'s new hand: " + newHand);
            } else {
                player.setActive(false); // Mark inactive players
                player.setName("LOSE");
                System.out.println(player.getName() + " is out of the game.");
            }
        }

        currentPlayerIndex = nextStartPlayerIndex; // Start from the next dealer
        this.nextStartPlayerIndex = (nextStartPlayerIndex + 1) % players.size(); // Rotate dealer to the next player

        System.out.println("New round setup complete.");
    }
}
