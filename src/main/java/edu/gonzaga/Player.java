package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    private String name;
    private int chips;
    private JPanel playerPanel;
    private JTextField nameField;
    private JLabel chipsLabel;
    private ArrayList<ArrayList<Object>> hand;

    public Player(String name, int chips, String profileImagePath) {
        this.name = name;
        this.chips = chips;
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
    public void drawCards(Cards deck) {
        for (int i = 0; i < 2; i++) {
            ArrayList<Object> card = deck.drawTheCard();
            if (card != null) {
                hand.add(card);
            }
        }
    }

    // Get the player's hand
    public ArrayList<ArrayList<Object>> getHand() {
        return hand;
    }
}

