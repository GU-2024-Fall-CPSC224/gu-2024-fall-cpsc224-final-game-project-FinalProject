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

    public SingleRound(ArrayList<Player> players) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.activePlayers = players.size();
        saveChipsRaise = new int[players.size()];
        for (int i = 0; i < saveChipsRaise.length; i++) {
            saveChipsRaise[i] = -1; // 初始化为 -1
        }
        this.pot = 0;
    }

    // fold cards
    public void foldCard(int currentPlayerIndex) {
        saveChipsRaise[currentPlayerIndex] = 0;
        activePlayers--;
        System.out.println("Active players remaining: " + activePlayers);

        if (activePlayers == 1 || currentPlayerIndex == players.size() - 1) {
            checkWinner();
        }
    }

    // check 0 chip
    public void checkChips(int currentPlayerIndex) {
        saveChipsRaise[currentPlayerIndex] = 0;
    }

    // raise chips
    public int raiseChips(int currentPlayerIndex) {
        saveChipsRaise[currentPlayerIndex] = 0;
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("Current player: " + currentPlayer.getName());
        if(!currentPlayer.isActive()){
            return 0;
        }

        int chipsRaise = raiseChipsDialog(currentPlayer);
        saveChipsRaise[currentPlayerIndex] = Math.max(0, chipsRaise);
        return chipsRaise;
    }

    // call chips ( match the max raised chips)
    public int callChips(int currentPlayerIndex) {
        saveChipsRaise[currentPlayerIndex] = 0;
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("Current player: " + currentPlayer.getName());
        if(!currentPlayer.isActive()){
            return 0;
        }

        // find the max amount of chips
        int maxRaise = getMaxRaise();
        int callAmount = maxRaise - saveChipsRaise[currentPlayerIndex];

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

        currentPlayer.updateChips(currentPlayer.getChips() - callAmount);

        saveChipsRaise[currentPlayerIndex] += callAmount;
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
                } else if (saveChipsRaise[i] != activePlayerRaise) {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetChipsRaise() {
        for (int i = 0; i < saveChipsRaise.length; i++) {
            saveChipsRaise[i] = -1;
        }
        System.out.println("saveChipsRaise has been reset to -1 for all players.");
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
    public int nextPlayer() {
        do {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            System.out.println("Next player index: " + currentPlayerIndex + " (" + players.get(currentPlayerIndex).getName() + ")");
        } while (!players.get(currentPlayerIndex).isActive());
        return currentPlayerIndex;
    }


    public int getActivePlayers() {
        return activePlayers;
    }

    public int raiseChipsDialog(Player currentPlayer) {
        int[] chipsRaised = {0};
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
                int chips = Integer.parseInt(input);

                // update chips after player put into the pot
                if(chips > 0 && chips <= currentPlayer.getChips()) {
                    chipsRaised[0] = chips;
                    raiseDialog.dispose();
                }else if(chips > currentPlayer.getChips()){
                    JOptionPane.showMessageDialog(raiseDialog,
                            "You cannot raise more than your current chips (" + currentPlayer.getChips() + ").",
                            "Invalid Raise",
                            JOptionPane.ERROR_MESSAGE);
                }else if(chips < getMaxRaise()){
                    JOptionPane.showMessageDialog(raiseDialog,
                            "You cannot raise less than max chips (" + currentPlayer.getChips() + ").",
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
            chipsRaised[0] = 0;
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
}
