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
 * Copyright: 2023
 */
package edu.gonzaga;


import java.util.ArrayList;
import java.util.Scanner;

/** Main program class for launching your team's program. */
public class CandyLand {
    public static void main(String[] args) {
        System.out.println("Hello Candy Land!");

        int numberOfPlayers = getPlayerInput();
        ArrayList <Player> players = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++){
            players.add(i, new Player());
        }

        Board board = new Board(players);
        boolean winner = false;
        while(!winner){
            for(int i = 0; i < players.size(); i++){
                players(i).playturn;
                if(players(i).getwinner){
                    winner = true;
                    break;
                }
            }
        }
        // Your code here. Good luck!
    }

    public static int getPlayerInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of players playing (2-4): ");
        int numberOfPlayers = scanner.nextInt();
//        instantiate the number of players
        scanner.close();
        return numberOfPlayers;
    }
}
