/*
 * Final project main driver class
 * 
 * 
 * Project Description:
 * 
 * 
 * Contributors:
 * 
 * 
 * Copyright: 2024
 */
package edu.gonzaga;

import javax.swing.JFrame;

/** Main program class for launching your team's program. */
public class MainGame {
    public static void main(String[] args) {
        // intro window
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Game game = new Game();
        frame.add(game);
        game.startMenu(frame);
    }
}
