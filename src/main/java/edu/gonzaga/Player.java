package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    private String name;
    private int chips;
    private boolean isActive;
    private JPanel playerPanel;
    private JTextField nameField;
    private JLabel chipsLabel;
    private JLabel chipsChange;
    private ArrayList<ArrayList<Object>> hand;

    public Player(String name, int chips, String profileImagePath) {
        this.name = name;
        this.chips = chips;
        this.isActive = true;
        this.hand = new ArrayList<>();
        initializePlayerPanel(profileImagePath);
    }

    // initialize the player panel
    private void initializePlayerPanel(String profileImagePath) {
        playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // set up the profile
        Profile profile = new Profile(profileImagePath);
        JLabel iconLabel = new JLabel(profile.getRoleImage());
        playerPanel.add(iconLabel);

        // set up information for players
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        nameField = new JTextField(name);
        nameField.setPreferredSize(new Dimension(150, 25));
        chipsLabel = new JLabel("Chips: " + chips);
        chipsChange = new JLabel("");

        infoPanel.add(nameField);
        infoPanel.add(chipsLabel);
        infoPanel.add(chipsChange);
        playerPanel.add(infoPanel);
    }

    // return the player panel
    public JPanel getPlayerPanel() {
        return playerPanel;
    }

    // update player's chips
    public void updateChips(int newChips) {
        this.chips = newChips;
        chipsLabel.setText("Chips: " + chips);
    }

    // check if player can input name
    public void setNameEditable(boolean nameEditable) {
        nameField.setEditable(nameEditable);
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

    // Draw cards from the deck
    public ArrayList<ArrayList<Object>> drawCards(Cards deck, JLabel[] cardLabels) {
        hand.clear();
        for (int i = 0; i < 2; i++) {
            ArrayList<Object> drawnCard = deck.drawTheCard(); // Draw a card from the deck
            if (drawnCard != null) {
                hand.add(drawnCard); // Add the card to the player's hand
                ImageIcon cardImage = deck.getCardImage(drawnCard); // Get the card's image
                cardLabels[i].setIcon(cardImage); // Update the corresponding card label
            }
        }
        return hand;
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

    // make decision
    public void makeDicision(String decision, ArrayList<ArrayList<ArrayList<Object>>> playersHands, int currentPlayer) {
        if (decision.equals("fold")) {
            System.out.println("Player " + this.getName() + " folded.");
            this.setName("Fold");
            setActive(false);
        }
//        else if(decision.equals("chip")){
//
//        }
//        else if(decision.equals("raise")){
//
//        }
//        else{
//            //check()
//        }
    }
}

