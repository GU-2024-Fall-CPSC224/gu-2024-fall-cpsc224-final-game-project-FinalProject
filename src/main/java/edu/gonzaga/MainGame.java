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
import java.util.ArrayList;
import java.util.Scanner; //just used for testing, delete when swing is integrated
/*
 * right now this has no java swing, and is text-based and not actually playable. just a starting point for the main function
 * change whatever, the comments are just to help organize thoughts and code, and mark what needs to be changed
 * work on integrating swing 
 */
public class MainGame {
    public static void main(String[] args) {

        // -----------------------------------
        // ATTRIBUTES START HERE
        // -----------------------------------

        Scanner input = new Scanner(System.in);

        // Stores the state of the current game. Automatically set to false at the start
        Boolean gameWon = false;

        // playerBoards holds the battleship board for each player.
        ArrayList<Board> playerBoards = new ArrayList<Board>();

        // -----------------------------------
        // METHODS / MAIN GAME STARTS HERE
        // -----------------------------------




        // PART 1.) ---------- GREET PLAYERS / AQUIRE USERNAMES ----------
        
        //SWING: welcome screen

        // Start game as text, in final this will be done in Swing
        System.out.println("Welcome to Battleship!");
        System.out.print("Player 1 Name: ");
        String player1 = input.nextLine();
        System.out.print("Player 2 Name: ");
        String player2 = input.nextLine();

        gameWon = false;


        // PART 2.) ---------- GENERATE INITIAL BOARDS ----------

        //SWING: set up board / add boards to array list
        for ( int i = 0; i < 2; i++ ) {
            playerBoards.add( new Board() );
        }
        

        //String[] shipTypes = [sub, destroyer, aircraft carrier, cruiser, battleship]
        //there is probably a smoother way to do this, but just to see the types


        // PART 3.) ---------- PLAYER SETUP: ----------

        System.out.println(player1 + " it's time to set up!");
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


        System.out.println(player2 + " it's time to set up!");
        
        /* 
         * for (ship Type: shipTypes){
         *   user input x and y, and vertical
         *   check if valid
         *   board.addShip()
         * }
        */

        Boolean turn1 = true; //track which player's turn (true = player1, false = player2)
        int turnLimit = 5; //just for testing purposes, delete before final submission


        // PART 4.) ---------- PLAY GAME / PROCESS TURNS ----------

        //SWING: draw Boards
        
        while (!gameWon) { //main gameplay loop
            if (turn1){
                System.out.println(player1 + " it's your turn!");
                
                //Shoot at player 2 (select a tile in swing)
                
                //setHit, isHit, 
                
                //update board
            }
            else {
                System.out.println(player2 + " it's your turn!");
                //shoot at player 1 (select a tile in swing)
                //setHit, isHit
                //update board
            }
            //if all ships sunk, gameWon = true

            //turn limit variable is just for testing purposes, to be deleted
            turnLimit --;
            if (turnLimit <= 0){
                gameWon = true;
            }

            turn1 = !turn1; //switch turn
        }


        // PART 5.) ---------- END GAME ----------

        String gameWinner = "";
        //winner is the last player's turn
        if (turn1){
            gameWinner = player1;
        }
        else {
            gameWinner = player2;
        }

        System.out.println("Congratulations "+ gameWinner + "!");
        //SWING: end screen
    }
}
