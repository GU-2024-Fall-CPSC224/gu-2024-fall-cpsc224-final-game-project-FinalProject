/*
 * Final project main driver class
 *
 *
 * Project Description: Candy Land board game - following rules as according to game rules
 *
 *
 * Contributors: Alexa Williams, Mia Procel, Eva Ulrichsen
 *
 *
 * Copyright: 2024
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
        Board board = new Board(players);
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Pick character 1-4!");
            int character = sc.nextInt();
            players.add(new Player());
            players.get(i).setCharacter(board.players.get(character));
        }

       
        boolean continueGame = true;
        while(continueGame){
            for(int i = 0; i < players.size(); i++){
                players.get(i).playTurn(board);
                if(players.get(i).checkWinner()){
                    continueGame = false;
                    break;
                }
            }
        }
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