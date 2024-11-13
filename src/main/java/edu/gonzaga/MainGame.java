/*
 * Final project main driver class
 * 
 * Project Description:
 * 
 * Contributors:
 * 
 * Copyright: 2024
 */
package edu.gonzaga;
import java.util.Scanner; //just used for testing, delete when swing is integrated
/*
 * right now this has no java swing, and is text-based and not actually playable. just a starting point for the main function
 * change whatever, the comments are just to help organize thoughts and code, and mark what needs to be changed
 * work on integrating swing 
 */
public class MainGame {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //SWING: welcome screen

        // Start game as text, in final this will be done in Swing
        System.out.println("Welcome to Battleship!");
        System.out.print("Player 1 Name: ");
        String player1 = input.nextLine();
        if (player1.equals("")){
            player1 = "Player 1";
        }
        Player playerOne = new Player(player1);
        Board boardOne = new Board();

        System.out.print("Player 2 Name: ");
        String player2 = input.nextLine();
        if (player2.equals("")){
            player2 = "Player 2";
        }
        Player playerTwo = new Player(player2);
        Board boardTwo = new Board();

        Boolean gameWon = false;

        //SWING: set up board 

        //String[] shipTypes = [sub, destroyer, aircraft carrier, cruiser, battleship]
        //there is probably a smoother way to do this, but just to see the types

        System.out.println(playerOne.getName() + " it's time to set up!");
        //go through each ship type and place their ship on the board
        //not sure how we plan to do this in the final game, but this is just to show the set up loop
        /* 
         * for (String Type: shipTypes){
         *   user input x and y, and vertical
         *   check if valid play
         *   board.addShip()
         * }
        */

        //SWING: switch screen
        System.out.println(playerTwo.getName() + " it's time to set up!");
        
        /* 
         * for (ship Type: shipTypes){
         *   user input x and y, and vertical
         *   check if valid
         *   board.addShip()
         * }
        */

        Boolean turn1 = true; //track which player's turn (true = player1, false = player2)
        int turnLimit = 30; //just for testing purposes, delete before final submission

        int xHit = 0;
        int yHit = 0;
        Boolean didHit = true;
        //SWING: draw Boards
        
        while (!gameWon) { //main gameplay loop
            if (turn1){
                System.out.println(playerOne.getName() + " it's your turn!");
                boardTwo.printBoard();

                //Shoot at player 2 (select a tile in swing)
                Boolean validGuess = false;
                //text based shooting before gui is added
                while (!validGuess){ //remove reguess
                    System.out.print("Type a number 1-10 for the x: ");
                    xHit = input.nextInt() -1;

                    System.out.print("Type a number 1-10 for the y: ");
                    yHit = input.nextInt() -1;

                    validGuess  = !boardTwo.isMarked(xHit,yHit);
                    if (!validGuess){
                        System.out.println("Already guessed!");
                    }
                }
                didHit = boardTwo.isMarkerHit(xHit, yHit);
                boardTwo.setMarked(xHit, yHit, didHit);
            }
            else {
                System.out.println(playerTwo.getName() + " it's your turn!");
                //shoot at player 1 (select a tile in swing)

                //boardOne.printBoard();
            }
            //if all ships sunk, gameWon = true

            //turn limit variable is just for testing purposes, to be deleted
            turnLimit --;
            if (turnLimit <= 0){
                gameWon = true;
            }
            
            turn1 = !turn1; //switch turn
        }
        String gameWinner = "";
        //winner is the last player's turn
        if (turn1){
            gameWinner = playerOne.getName();
        }
        else {
            gameWinner = playerTwo.getName();
        }

        System.out.println("Congratulations "+ gameWinner + "!");
        //SWING: end screen
    }
}
