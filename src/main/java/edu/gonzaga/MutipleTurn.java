package edu.gonzaga;

import javax.swing.*;
import java.util.ArrayList;

public class MutipleTurn {
    private Cards card;
    private int playTurn;
    private ArrayList<JLabel> riverCards;
    private ArrayList<Object> riverCardsSave;
    private ArrayList<Player> players;
    private SingleRound singleRound;
    private int flippedCardsCount;

    public MutipleTurn(Cards cards, ArrayList<Player> players, SingleRound singleRound, ArrayList<JLabel> riverCards) {
        this.card = cards;
        this.players = players;
        this.singleRound = singleRound;
        this.riverCards = riverCards;
        this.riverCardsSave = new ArrayList<>();
        this.flippedCardsCount = 0;
        this.playTurn = 0;
    }

    // Calculate active players
    public ArrayList<Player> getActivePlayersList() {
        ArrayList<Player> activePlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.isActive() && player.getChips() > 0) {
                activePlayers.add(player);
            }
        }
        return activePlayers;
    }

    // Next turn
    public void nextTurn() {
        if (!singleRound.checkRaiseChips()) {
            return;
        }

        switch (playTurn) {
            case 0:
                flipCards(3);
                break;
            case 1:
            case 2:
                flipCards(1);
                break;
            case 3:
                checkWinner(riverCardsSave);
                break;
            default:
                System.out.println("No more turns.");
        }

        playTurn++;
    }

    // Second turn, flip 3 cards
    private void flipCards(int count) {
        for (int i = 0; i < count && flippedCardsCount < riverCards.size(); i++) {
            flipNextRiverCard();
        }
    }

    // Flip next card
    private void flipNextRiverCard() {
        if (flippedCardsCount < riverCards.size()) {
            ArrayList<Object> drawCard = card.drawTheCard();
            if (drawCard != null) {
                ImageIcon cardImage = card.getCardImage(drawCard);
                riverCards.get(flippedCardsCount).setIcon(cardImage);
                riverCardsSave.add(drawCard);
                flippedCardsCount++;
            } else {
                System.out.println("No more cards to flip.");
            }
        }
    }

    // Check winner or determine winner by points
    public void checkWinner(ArrayList<Object> riverCardsSave) {
        int pot = singleRound.getPot();
        ArrayList<Player> activePlayers = getActivePlayersList();

        if (activePlayers.isEmpty()) {
            System.out.println("No active players remaining.");
            return;
        }

        System.out.println("Active players: " + activePlayers.size());

        if (activePlayers.size() == 1) {
            Player winner = activePlayers.get(0);
            JOptionPane.showMessageDialog(null, winner.getName() + " wins the pot!");
            winner.updateChips(winner.getChips() + pot);
            return;
        }

        // Calculate scores for active players
        int highestPoints = 0;
        ArrayList<Player> winners = new ArrayList<>();

        for (Player player : activePlayers) {
            try {
                WinnerCheck winnerCheck = new WinnerCheck(riverCardsSave);
                int playerPoints = winnerCheck.pointCheck(player.getHand());
                System.out.println("Player " + player.getName() + " has " + playerPoints + " points.");

                if (playerPoints > highestPoints) {
                    highestPoints = playerPoints;
                    winners.clear();
                    winners.add(player);
                } else if (playerPoints == highestPoints) {
                    winners.add(player);
                }
            } catch (Exception e) {
                System.out.println("Error calculating points for player: " + player.getName());
                e.printStackTrace();
            }
        }

        // Distribute pot among winners
        if (winners.isEmpty()) {
            System.out.println("No winners determined.");
            return;
        }

        int share = pot / winners.size();
        int remainder = pot % winners.size();

        for (int i = 0; i < winners.size(); i++) {
            Player winner = winners.get(i);
            int playerShare = share + (remainder > 0 ? 1 : 0);
            remainder--;
            winner.updateChips(winner.getChips() + playerShare);
            JOptionPane.showMessageDialog(null, winner.getName() + " wins " + playerShare + " chips!");
        }
    }
}
