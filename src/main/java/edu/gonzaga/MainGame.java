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


/** Main program class for launching your team's program. */
public class MainGame {
    public static void main(String[] args) {
        Snake snake = new Snake();
        System.out.println(snake);
        snake.move();
        System.out.println(snake);
        snake.turn('d');
        snake.move();
        System.out.println(snake);
        snake.turn('l');
        snake.move();
        System.out.println(snake);
        snake.move();
        System.out.println(snake);

        // Your code here. Good luck!
    }
}
