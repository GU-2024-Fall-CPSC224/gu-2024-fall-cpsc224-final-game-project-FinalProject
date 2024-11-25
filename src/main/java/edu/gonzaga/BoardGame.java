package edu.gonzaga;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class BoardGame {
    private JFrame frame;
    private JButton[][] boardButtons; // Grid of buttons
    private HashMap<Integer, ImageIcon> playerIcons; // Player icons
    private int[] playerPositions; // Track player positions (1D for simplicity)

    public BoardGame(int rows, int cols, int numPlayers) {
        frame = new JFrame("Board Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(rows, cols));

        boardButtons = new JButton[rows][cols];
        playerIcons = new HashMap<>();
        playerPositions = new int[numPlayers]; // Starting positions

        // Load player icons
        for (int i = 0; i < numPlayers; i++) {
            playerIcons.put(i, new ImageIcon("player" + (i + 1) + ".png"));
        }

        // Create buttons for the board
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JButton button = new JButton();
                boardButtons[row][col] = button;
                frame.add(button);
            }
        }

        // Initial placement of players
        updateBoard();

        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    private void updateBoard() {
        // Clear all buttons
        for (JButton[] row : boardButtons) {
            for (JButton button : row) {
                button.setIcon(null);
            }
        }

        // Place players on the board
        for (int player = 0; player < playerPositions.length; player++) {
            int position = playerPositions[player];
            int row = position / boardButtons[0].length;
            int col = position % boardButtons[0].length;
            boardButtons[row][col].setIcon(playerIcons.get(player));
        }
    }

    public void movePlayer(int player, int steps) {
        playerPositions[player] = (playerPositions[player] + steps) % (boardButtons.length * boardButtons[0].length);
        updateBoard();
    }

    public static void main(String[] args) {
        BoardGame game = new BoardGame(5, 5, 2);
        game.movePlayer(0, 3); // Example: Move player 0 by 3 steps
    }
}
