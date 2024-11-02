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
        Board board = new Board();
        ArrayList <Player> players = new ArrayList<>();
        boolean done = false;
        while(!done) {

//             image icon class for images
            ImageIcon imageIcon = new ImageIcon("path/to/your/image.jpg");
            JFrame frame1 = new JFrame();
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JFrame frame2 = new JFrame();
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JFrame frame3 = new JFrame();
            frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JFrame frame4 = new JFrame();
            frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//            frame 1 **************************************
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

            JButton button2 = new JButton("3");
            button2.setBounds(200, 200, 50, 50);
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {                    
                    numberOfPlayers = 3;
                }
            });

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
                   frame1.setVisible(false);
                   frame2.setVisible(true);
                }
            });

            frame1.add(button1);
            frame1.add(button2);
            frame1.add(button3);
            frame1.add(button4);

            frame1.setSize(800, 600);
            frame1.setLayout(null);
            frame1.setVisible(true);

//            frame 2 **************************************
            frame2.setSize(800, 600);
            frame2.setLayout(null);
            frame2.setVisible(false);

            JButton characterA = new JButton("Mally Mallo");
            characterA.setBounds(100, 50, 200, 200);
            characterA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    players.add(new Player());
                    players.get(players.size() - 1).setCharacter("Mally Mallo");
                    board.removePlayerName("Mally Mallo");
                    characterA.setVisible(false);
                }
            });

            JButton characterB = new JButton("Twirly Girl");
            characterB.setBounds(100, 300, 200, 200);
            characterB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    players.add(new Player());
                    players.get(players.size() - 1).setCharacter("Twirly Girl");
                    board.removePlayerName("Twirly Girl");
                    characterB.setVisible(false);
                }
            });

            JButton characterC = new JButton("Cutie Cone");
            characterC.setBounds(450, 50, 200, 200);
            characterC.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    players.add(new Player());
                    players.get(players.size() - 1).setCharacter("Cutie Cone");
                    board.removePlayerName("Cutie Cone");
                    characterC.setVisible(false);
                }
            });

            JButton characterD = new JButton("Giggly Gumdrop");
            characterD.setBounds(450, 300, 200, 200);
            characterD.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    players.add(new Player());
                    players.get(players.size() - 1).setCharacter("Giggly Gumdrop");
                    board.removePlayerName("Giggly Gumdrop");
                    characterD.setVisible(false);
                }
            });

            JButton button5 = new JButton("Enter");
            button5.setBounds(650, 500, 100, 50);
            button5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame2.setVisible(false);
                    frame3.setVisible(true);
                }
            });

            frame2.add(characterA);
            characterA.setVisible(true);
            frame2.add(characterB);
            characterB.setVisible(true);
            frame2.add(characterC);
            characterC.setVisible(true);
            frame2.add(characterD);
            characterD.setVisible(true);

//            frame 3 **************************************
            frame3.setSize(800, 600);
            frame3.setLayout(null);
            frame3.setVisible(false);

//            frame 4 **************************************
            frame4.setSize(800, 600);
            frame4.setLayout(null);
            frame4.setVisible(false);

            System.out.println("Hello Candy Land!");

//            int numberOfPlayers = getPlayerInput();
            
            Scanner sc = new Scanner(System.in);
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.println("Pick character 1-4!"); //the gui will fix this logic
                int character = sc.nextInt();
                players.add(new Player());
                players.get(i).setCharacter(board.playerNames.get(character));
                board.removePlayerNameIndex(character);
            }
            board.addPlayers(players);


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