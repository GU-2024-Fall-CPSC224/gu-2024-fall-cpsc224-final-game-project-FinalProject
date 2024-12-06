package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MutipleTurn {
    private CardBackImage cardBackImage;
    private Cards deck;
    private int playTurn;
    private ArrayList<JLabel> riverCards;
    private ArrayList<Object> riverCardsSave;
    private ArrayList<Player> players;
    private SingleRound singleRound;
    private int flippedCardsCount;
    private JFrame mainWindowFrame;
    private JLabel potlabel;

    public MutipleTurn(Cards cards, ArrayList<Player> players, SingleRound singleRound, ArrayList<JLabel> riverCards, JFrame mainWindowFrame, GUI gui, JLabel potLabel) {
        this.deck = cards;
        this.players = players;
        this.singleRound = singleRound;
        this.riverCards = riverCards;
        this.riverCardsSave = new ArrayList<>();
        this.flippedCardsCount = 0;
        this.playTurn = 0;
        this.cardBackImage = new CardBackImage("media/card3.png", gui);
        this.mainWindowFrame = mainWindowFrame;
        this.potlabel = potLabel;
    }

    public void updateRound(SingleRound singleRound) {
            this.singleRound = singleRound;
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
        System.out.println("all in pot: " + singleRound.getPot());
        if (allIn) {
            System.out.println("All players are All-In. Automatically running through turns...");
            while (playTurn <= 3) {
                executeTurn();
                playTurn++;
            }
        } else {
            System.out.println("not all in pot: " + singleRound.getPot());
            if (!singleRound.checkRaiseChips()) {
                return;
            }

            System.out.println("turn: " + playTurn + " Pot: " + singleRound.getPot());
            executeTurn();
            System.out.println("turn: " + playTurn + " Pot: " + singleRound.getPot());
            playTurn++;
        }
    }

    public void singlePlayer() {
        ArrayList<Player> activePlayers = getActivePlayersList();
        Player winner = activePlayers.get(0);

        winner.updateChips(winner.getChips() + singleRound.getPot());
        System.out.println("Only one active player remaining: " + winner.getName());
        System.out.println(winner.getName() + " wins the pot of " + singleRound.getPot() + " chips!");

        JOptionPane.showMessageDialog(
                null,
                winner.getName() + " wins the pot of " + singleRound.getPot() + " chips!",
                "Winner",
                JOptionPane.INFORMATION_MESSAGE
        );

        singleRound.resetPot();
        promptNewRound();
        playTurn = 0;

    }

    private void executeTurn() {
        System.out.println("Before executeTurn: pot = " + singleRound.getPot() + "play turn: " + playTurn);
        switch (playTurn) {
            case 0:
                System.out.println("current chips: " + singleRound.getPot());
                flipCards(3);
                break;
            case 1:
            case 2:
                System.out.println("current chips: " + singleRound.getPot());
                flipCards(1);
                break;
            case 3:
                System.out.println("current chips: " + singleRound.getPot());
                checkWinner(riverCardsSave);
                break;
            default:
                System.out.println("No more turns.");
        }
        System.out.println("Executed turn " + playTurn);
    }

    private void flipCards(int count) {
        System.out.println("Before flipCards: pot = " + singleRound.getPot());
        for (int i = 0; i < count && flippedCardsCount < riverCards.size(); i++) {
            flipNextRiverCard();
        }
        System.out.println("After flipCards: pot = " + singleRound.getPot());
    }

    // Flip next card
    private void flipNextRiverCard() {
        if (flippedCardsCount < riverCards.size()) {
            ArrayList<Object> drawCard = deck.drawTheCard();
            if (drawCard != null) {
                ImageIcon cardImage = deck.getCardImage(drawCard);
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
        System.out.println("Pot before distributing: " + pot);

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

        // if everyone fold card before flip 5 cards in the river, so the last one will be winner
        if (activePlayers.size() == 1) {
            Player winner = activePlayers.get(0);
            System.out.println("Only one active player remaining: " + winner.getName());

            winner.updateChips(winner.getChips() + pot);
            System.out.println(winner.getName() + " wins the pot of " + pot + " chips!");

            JOptionPane.showMessageDialog(
                    mainWindowFrame,
                    winner.getName() + " wins the pot of " + pot + " chips!",
                    "Winner",
                    JOptionPane.INFORMATION_MESSAGE
            );

            singleRound.resetPot();
            promptNewRound();
            return;
        }

        int share = pot / winners.size();
        int remainder = pot % winners.size();
        System.out.println("Each winner gets: " + share + " chips, remainder: " + remainder);

        JPanel winnersPanel = new JPanel(new GridLayout(winners.size(), 1));

        for (int i = 0; i < winners.size(); i++) {
            Player winner = winners.get(i);
            int playerShare = share + (remainder > 0 ? 1 : 0);
            remainder--;
            winner.updateChips(winner.getChips() + playerShare);


            JPanel handPanel = new JPanel(new FlowLayout());
            for (ArrayList<Object> card : winner.getHand()) {
                ImageIcon cardImage = deck.getCardImage(card);
                handPanel.add(new JLabel(cardImage));
            }

            JPanel winnerPanel = new JPanel(new BorderLayout());
            winnerPanel.add(new JLabel("Winner: " + winner.getName() + " (" + playerShare + " chips)"), BorderLayout.NORTH);
            winnerPanel.add(handPanel, BorderLayout.CENTER);

            winnersPanel.add(winnerPanel);
        }

        JScrollPane scrollPane = new JScrollPane(winnersPanel);
        JOptionPane.showMessageDialog(mainWindowFrame, scrollPane, "Winners and Their Hands", JOptionPane.PLAIN_MESSAGE);

        // Ask if players want to start a new round
        singleRound.resetPot();

        promptNewRound();
    }

    public void promptNewRound() {
        for (Player player : players) {
            if (player.getChips() <= 0) {
                player.setActive(false);
                player.setName(player.getName() + " (LOSE)");
                System.out.println(player.getName() + " is out of the game.");
            }
        }

        if(getActivePlayersList().size() == 1) {
            showResult();
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
            showResult();
        }
    }

    public void showResult(){
        System.out.println("Game over. Thanks for playing!");

        JDialog exitDialog = new JDialog(mainWindowFrame, "Results", true);
        exitDialog.setSize(700, 500);
        exitDialog.setLocationRelativeTo(mainWindowFrame);
        exitDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Create a layered pane to hold the background and button
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 500));

        // Set the background image
        JLabel backgroundLabel = new JLabel(new ImageIcon("media/exit.png"));
        backgroundLabel.setBounds(0, 0, 700, 500);

        // Create a panel to display player result
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setOpaque(false);
        resultsPanel.setBounds(100, 200, 500, 300);

        for(Player player : players){
            JLabel playerLabel = new JLabel(player.getName() + ": " + player.getChips() + " chips");
            playerLabel.setFont(new Font("Serif", Font.BOLD, 18));
            playerLabel.setForeground(Color.WHITE);
            playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the text
            resultsPanel.add(playerLabel);
            resultsPanel.add(Box.createVerticalStrut(10));
        }
        // Create the OK button
        JButton okButton = new JButton("OK");
        okButton.setBounds(300, 450, 100, 30); // Position the button at the bottom center

        // Add the background and button to the layered pane
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(resultsPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(okButton, JLayeredPane.PALETTE_LAYER);

        // Add action listener to the OK button
        okButton.addActionListener(e -> System.exit(0));

        exitDialog.add(layeredPane);
        exitDialog.pack();
        exitDialog.setVisible(true);
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

        // Pass the current dealer to startNewRound
        int newDealer = singleRound.startNewRound(deck);
        singleRound.initializeBlinds(newDealer);
        playTurn = -1;
        potlabel.setText("Pot: 30");

        System.out.println("New round started with next dealer: " + players.get(newDealer).getName() + ", new play turn: " + playTurn);
    }
}

