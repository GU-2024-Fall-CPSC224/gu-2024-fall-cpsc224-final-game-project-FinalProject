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
        for(int i = 0; i < numberOfPlayers; i++){
            players.add(i, new Player());
        }

        Board board = new Board(players);
        boolean winner = false;
        while(!winner){
            for(int i = 0; i < players.size(); i++){
                players.get(i).playTurn(board);
                if(players.get(i).checkWinner()){
                    winner = true;
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

Scanner sc = new Scanner(System.in);
        System.out.println("How many points would you like to play in this game?");
        String pointsInput = sc.nextLine();
        int targetPoints;
        if (pointsInput.equals("")) {
            targetPoints = 10000;
        } else {
            targetPoints = Integer.parseInt(pointsInput);
        }
        System.out.println("How many players are going to play in this game?");
        String playersInput = sc.nextLine();
        int numPlayers = Integer.parseInt(playersInput);
        boolean continueGame = true;
        boolean endGameFlag = false;
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player());
            for (int j = 0; j < players.size() - 1; j++) {
                if (players.get(i).getPlayerName().equals(players.get(j).getPlayerName())) {
                    players.get(i).setPlayerName(players.get(i).getPlayerName() + players.get(i).getPlayerID());
                }
            }
        }