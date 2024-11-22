package edu.gonzaga;

import java.util.Scanner;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {
    // dimensions
    private static final int game_width = 1000;
    private static final int game_height = 1000;

    private Board board = new Board();
    private Snake snake = new Snake();
    // private Score score = new Score();

    /* timer? */
    private boolean playGame = false;
    private boolean snakeDeath = false;

    // panel
    Game() {
        setPreferredSize(new Dimension(game_width, game_height));
        setBackground(Color.green);
    }

    // start menu
    public void startMenu(JFrame frame) {
        JPanel startWindow = new JPanel();
        startWindow.setBackground(Color.black);

        // layout
        startWindow.add(Box.createRigidArea(new Dimension(0, 50)));
        JLabel title = new JLabel("SNAKE");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        startWindow.add(title);

        // different levels for boards here ?

        // options for different speeds
        startWindow.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton speed1 = new JButton("Easy");
        speed1.addActionListener(event -> setUpStart(150));
        startWindow.add(speed1);

        JButton speed2 = new JButton("Normal");
        speed2.addActionListener(event -> setUpStart(100));
        startWindow.add(speed2);

        JButton speed3 = new JButton("Hard");
        speed3.addActionListener(event -> setUpStart(80));
        startWindow.add(speed3);

        JButton speed4 = new JButton("Impossible");
        speed4.addActionListener(event -> setUpStart(60));
        startWindow.add(speed4);
    
        frame.add(startWindow);
        frame.pack();
        frame.setVisible(true);
    }

    public void setUpStart(int selectedSpeed) {
        playGame = true;
        startGame();
    }

    // tick -> startGame
    private void startGame() {
        if (!playGame || snakeDeath) return;

       // board.displayBoard();
        snake.move();
        switch (board.detectCollision()) {
            case "food":
                //score++
                snake.grow();
               // food.applyAffect(snake);
               // food = new Food(0, 0); //todo: randomize pos and type
                break;
            case "edge":
                snakeDeath = true;
                break;
            case "obstacle":
                snakeDeath = true;
                break;
        }
        
        checkGameOver();
    }

    private void pauseGame() {

    }

    private void checkGameOver() {    
        if (snakeDeath) {
            gameOver();
        }
    }

    private void gameOver() {
        String[] options = {"Try Again", "Menu", "Exit"};
        var choice = JOptionPane.showOptionDialog(this, "GAME OVER", "Snake Game", 0, 3, null, options, options[0]);

        // WILL IMPLEMENT ONCE BOARDS HAVE BEEN CREATED
        // try again
        // if (choice == 0) {
        //     resetGame();
        // }
        // // go to menu
        // if (choice == 1) {
        //     exitGame();
        // }
        // // close window
        // if (choice == 2) {
        //     System.exit(0);
        // }
    }

    // private void resetGame() {
    //     playGame = false;
    //     snakeDeath = false;
    //     board = new Board();
    //     snake = new Snake();
    //     startMenu((JFrame) this.getTopLevelAncestor());
    // }
    
    // private exitGame() {
    //     JFrame frame = (JFrame) this.getTopLevelAncestor();

    //     if (frame != null) {
    //         frame.getContentPane().removeAll();
    //         startMenu(frame);
    //         frame.revalidate();
    //     }
    // }

    @Override
    public void actionPerformed(ActionEvent event) {
      //  tick();
    }
}
