package edu.gonzaga;

import javax.swing.*;
import java.util.ArrayList;

public class MutipleTurn {
    private CardBackImage cardBackImage;
    private Cards card;
    private int playTurn;
    private ArrayList<JLabel> riverCards;
    private ArrayList<Object> riverCardsSave;
    private ArrayList<Player> players;
    private SingleRound singleRound;
    private int flippedCardsCount;
    private int nextStartPlayerIndex;

    public MutipleTurn(Cards cards, ArrayList<Player> players, SingleRound singleRound, ArrayList<JLabel> riverCards) {
        this.card = cards;
        this.players = players;
        this.singleRound = singleRound;
        this.riverCards = riverCards;
        this.riverCardsSave = new ArrayList<>();
        this.flippedCardsCount = 0;
        this.playTurn = 0;
        cardBackImage = new CardBackImage("media/purple_back.jpg");
        this.nextStartPlayerIndex = 0; // Start with the first player as the dealer
    }

    // Calculate active players
    public ArrayList<Player> getActivePlayersList() {
        ArrayList<Player> activePlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.isActive()) {
                activePlayers.add(player);
            }
        }
        return activePlayers;
    }

    // Next turn
    public void nextTurn(boolean allIn) {
        if (allIn) {
            System.out.println("All players are All-In. Automatically running through turns...");
            while (playTurn <= 3) {
                executeTurn();
                playTurn++;
            }
        } else {
            if (!singleRound.checkRaiseChips()) {
                return;
            }
            executeTurn();
            playTurn++;
        }
    }

    private void executeTurn() {
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
        System.out.println("Executed turn " + playTurn);
    }

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

    public void checkSingleWinner() {
        ArrayList<Player> activePlayers = getActivePlayersList();
        Player winner = activePlayers.get(0);
        winner.updateChips(winner.getChips() + singleRound.getPot());
        singleRound.resetPot(); // Reset pot after chips are updated
    }

    // Check winner or determine winner by points
    public void checkWinner(ArrayList<Object> riverCardsSave) {
        int pot = singleRound.getPot();
        ArrayList<Player> activePlayers = getActivePlayersList();

        if (activePlayers.isEmpty()) {
            System.out.println("No active players remaining.");
            promptNewRound();
            return;
        }

        System.out.println("Active players: " + activePlayers.size());

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

        StringBuilder winnerMessage = new StringBuilder();
        for (int i = 0; i < winners.size(); i++) {
            Player winner = winners.get(i);
            int playerShare = share + (remainder > 0 ? 1 : 0);
            remainder--;
            winner.updateChips(winner.getChips() + playerShare);
            winnerMessage.append(winner.getName()).append(" wins ").append(playerShare).append(" chips!\n");
        }

        JOptionPane.showMessageDialog(null, winnerMessage.toString());

        // Ask if players want to start a new round
        promptNewRound();
    }

    public void promptNewRound() {
        for (Player player : players) {
            if (player.getChips() <= 0) {
                player.setActive(false);
                player.setName("LOSE");
                System.out.println(player.getName() + " is out of the game.");
            }
        }

        int response = JOptionPane.showConfirmDialog(
                null,
                "Do you want to play another round?",
                "New Round",
                JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            resetForNewRound();
        } else {
            System.out.println("Game over. Thanks for playing!");
            System.exit(0);
        }
    }


    public void initializeRiverCards() {
        for (JLabel riverCard : riverCards) {
            riverCard.setIcon(cardBackImage.getBackImage());
        }
        riverCardsSave.clear();
        flippedCardsCount = 0;
        System.out.println("River cards have been reset to back images.");
    }

    private void resetForNewRound() {
        card.initializeDeck();
        playTurn = -1;
        riverCardsSave.clear();
        flippedCardsCount = 0;

        initializeRiverCards();

        for (Player player : players) {
            if (!"LOSE".equals(player.getName())) {
                player.setActive(true);
            } else {
                player.setActive(false);
            }
        }

        for (JLabel riverCard : riverCards) {
            riverCard.revalidate();
            riverCard.repaint();
        }

        singleRound.startNewRound(card, nextStartPlayerIndex);
        nextStartPlayerIndex = (nextStartPlayerIndex + 1) % players.size();

        System.out.println("New round started with next dealer: " + players.get(nextStartPlayerIndex).getName());
    }
}

