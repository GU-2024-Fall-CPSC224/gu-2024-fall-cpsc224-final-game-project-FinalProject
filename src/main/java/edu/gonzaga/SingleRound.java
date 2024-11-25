package edu.gonzaga;


import javax.swing.*;
import java.util.ArrayList;

public class SingleRound {
    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private int activePlayers;

    public SingleRound(ArrayList<Player> players) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.activePlayers = players.size();
    }

    public void foldCard(int currentPlayerIndex) {
        activePlayers--;
        System.out.println("Active players remaining: " + activePlayers);

        if (activePlayers == 1 || currentPlayerIndex == players.size() - 1) {
            checkWinner();
        }
    }

    public void checkWinner() {
        System.out.println("Checking for winner...");
        System.out.println("active player: " + activePlayers);
        if (activePlayers == 1) {
            for (Player player : players) {
                if (player.isActive()) {
                    JOptionPane.showMessageDialog(null, player.getName() + " wins the pot!");
                    System.exit(0);
                }
            }
        }
    }

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

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
}
