package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    private String name;
    private int chips;
    boolean isActive;
    private JPanel playerPanel;
    private JTextField nameField;
    private JLabel chipsLabel;
    private JLabel chipsChange;
    private ArrayList<ArrayList<Object>> hand;
    private GUI gui;

    public Player(String name, int chips, String profileImagePath, GUI gui) {
        this.name = name;
        this.chips = chips;
        this.isActive = true;
        this.hand = new ArrayList<>();
        initializePlayerPanel(profileImagePath, gui);

    }

    // initialize the player panel
    private void initializePlayerPanel(String profileImagePath, GUI gui) {
        this.gui = gui;
        playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // set up the profile
        Profile profile = new Profile(profileImagePath, gui);
        JLabel iconLabel = new JLabel(profile.getRoleImage());
        playerPanel.add(iconLabel);

        // set up information for players
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        nameField = new JTextField(name);
        nameField.setPreferredSize(new Dimension(150, 25));
        chipsLabel = new JLabel("Chips: " + chips);
        chipsChange = new JLabel("Decision: ");

        infoPanel.add(chipsChange);
        infoPanel.add(nameField);
        infoPanel.add(chipsLabel);
        playerPanel.add(infoPanel);
    }

    // return the player panel
    public JPanel getPlayerPanel() {
        return playerPanel;
    }

    // update player's chips
    public void updateChips(int newChips) {
        this.chips = newChips;
        chipsLabel.setText("Chips: " + newChips);
    }

    // check if player can input name
    public void setNameEditable(boolean nameEditable) {
        nameField.setEditable(nameEditable);
    }

    public void updateDealerStatus(boolean isDealer) {
        if (isDealer) {
            nameField.setText("(dealer) " + name);
        } else {
            nameField.setText(" " + name);
        }
    }

    // return player's name
    public String getName() {
        return nameField.getText();
    }

    // set up the player's name
    public void setName(String name) {
        this.name = name;
        nameField.setText(name);
    }

    // return player's chips
    public int getChips() {
        return chips;
    }

    // Draw cards from the deck and store in hand
    public ArrayList<ArrayList<Object>> drawCards(Cards deck) {
        hand.clear(); // Clear previous hand
        for (int i = 0; i < 2; i++) {
            ArrayList<Object> drawnCard = deck.drawTheCard(); // Draw a card from the deck
            if (drawnCard != null) {
                hand.add(drawnCard); // Add the card to the player's hand
            }
        }
        return hand; // Return the generated hand
    }

    // Display the stored hand using cardLabels
    public void displayHand(Cards deck, JLabel[] cardLabels) {
        for (int i = 0; i < hand.size(); i++) {
            ImageIcon cardImage = deck.getCardImage(hand.get(i)); // Get the card's image
            cardLabels[i].setIcon(cardImage); // Update the corresponding card label
        }
    }


    // Get the player's hand
    public ArrayList<ArrayList<Object>> getHand() {
        return hand;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public void clearHand() {
        hand.clear(); // Clear the player's hand
    }

    public JLabel getChipsChangeLabel() {
        return chipsChange;
    }

    // make decision
    public int makeDecision(String decision, int chipsChange, int totalRaisedChips, Player currentPlayer) {
        int updateChips = currentPlayer.getChips();

        if (decision.equals("fold")) {
            System.out.println("Player " + currentPlayer.getName() + " folded.");
            currentPlayer.getChipsChangeLabel().setText("Decision: fold ");
            currentPlayer.setActive(false);
        } else if (decision.equals("call")) {
            currentPlayer.getChipsChangeLabel().setText("Decision: call " + totalRaisedChips); // 更新显示
            System.out.println("Player " + currentPlayer.getName() + " calls with " + totalRaisedChips + " chips.");
        } else if (decision.equals("raise")) {
            updateChips -= chipsChange;
            currentPlayer.updateChips(updateChips);
            currentPlayer.getChipsChangeLabel().setText("Decision: raise " + totalRaisedChips); // 更新显示
            System.out.println("Player " + currentPlayer.getName() + " raises by " + totalRaisedChips + " chips.");
        } else {
            currentPlayer.getChipsChangeLabel().setText("Decision: check 0"); // 更新显示
            System.out.println("Player " + currentPlayer.getName() + " checks.");
        }
        return updateChips;
    }


    public void resetDecision() {
        this.chipsChange.setText("Decision: ");
    }
}

