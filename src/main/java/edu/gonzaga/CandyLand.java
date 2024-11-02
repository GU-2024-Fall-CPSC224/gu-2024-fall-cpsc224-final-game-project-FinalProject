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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

/** Main program class for launching your team's program. */
public class CandyLand {
    static int numberOfPlayers;
    public static void main(String[] args) {

        boolean done = false;
        while(!done) {
            JFrame frame1 = new JFrame();
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel label1 = new JLabel("Choose number of players:");
            label1.setBounds(10, 10, 200, 50);
            frame1.add(label1);

            JButton button1 = new JButton("2");
            button1.setBounds(100, 200, 50, 50);
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    numberOfPlayers = 2;
                }
            });
            frame1.add(button1);

            JButton button2 = new JButton("3");
            button2.setBounds(200, 200, 50, 50);
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {                    
                    numberOfPlayers = 3;
                }
            });

            frame1.add(button2);

            JButton button3 = new JButton("4");
            button3.setBounds(300, 200, 50, 50);
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {                    
                    numberOfPlayers = 4;
                }
            });

            JButton button4 = new JButton("Enter");
            button4.setBounds(650, 500, 100, 50);
            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {                   

                }
            });

            frame1.add(button3);
            frame1.add(button4);


            frame1.setSize(800, 600);
            frame1.setLayout(null);
            frame1.setVisible(true);

            System.out.println("Hello Candy Land!");

            int numberOfPlayers = getPlayerInput();
            ArrayList <Player> players = new ArrayList<>();
            Board board = new Board(players);
            Scanner sc = new Scanner(System.in);
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.println("Pick character 1-4!"); //the gui will fix this logic
                int character = sc.nextInt();
                players.add(new Player());
                players.get(i).setCharacter(board.playerNames.get(character));
                board.removePlayerName(character);
            }


            boolean continueGame = true;
            while(continueGame){
                for(int i = 0; i < players.size(); i++){
                    players.get(i).playTurn(board);
                    if(players.get(i).checkWinner()){
                        System.out.println(players.get(i).getCharacter() + " is the Winner");
                        continueGame = false;
                        break;
                    }
                }
        }

        }

    }

    public static int getPlayerInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of players playing (2-4): ");
        int numberOfPlayers = scanner.nextInt();
//        instantiate the number of players
        //scanner.close();
        return numberOfPlayers;
    }
}