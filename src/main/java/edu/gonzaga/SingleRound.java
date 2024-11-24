package edu.gonzaga;


import javax.swing.*;
import java.util.ArrayList;

public class SingleRound {
    private ArrayList<Player> players;
    private ArrayList<ArrayList<ArrayList<Object>>> playersHands;
    private int currentPlayerIndex;
    private int activePlayers;

    public SingleRound(ArrayList<Player> players, ArrayList<ArrayList<ArrayList<Object>>> playersHands) {
        this.players = players;
        this.playersHands = playersHands;
        this.currentPlayerIndex = 0;
        updateActivePlayers();
    }

    public void foldCard() {
        // clear the cards
        playersHands.get(currentPlayerIndex).clear();

        updateActivePlayers();
    }

    public void checkWinner() {
        // check the winner
        updateActivePlayers();
        if (activePlayers == 1) {
            for (int i = 0; i < playersHands.size(); i++) {
                if (!playersHands.get(i).isEmpty()) {
                    Player winner = players.get(i);
                    JOptionPane.showMessageDialog(null, winner.getName() + ", you win the pot!");
                    System.exit(0);
                }
            }
        }
    }

    private void updateActivePlayers() {
        activePlayers = 0;
        for (ArrayList<ArrayList<Object>> hand : playersHands) {
            if (!hand.isEmpty()) {
                activePlayers++;
            }
        }
    }
}
