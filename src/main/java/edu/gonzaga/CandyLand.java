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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.font.*;

/** Main program class for launching your team's program. */
public class CandyLand {
    static int numberOfPlayers = 2;
    static int playerCounter = 0;

    public static void main(String[] args) {
        Board board = new Board();
        ArrayList<Player> players = new ArrayList<>();
        boolean done = false;

        ImageIcon pinkIcon = new ImageIcon("project design documents/Picture5.jpg");
        ImageIcon purpleIcon = new ImageIcon("project design documents/Picture6.jpg");
        ImageIcon redIcon = new ImageIcon("project design documents/Picture7.jpg");
        ImageIcon yellowIcon = new ImageIcon("project design documents/Picture8.jpg");
        ImageIcon boardIcon = new ImageIcon("project design documents/board2.jpg");
        JFrame frame1 = new JFrame();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JLabel label1 = new JLabel("Choose number of players:");
        label1.setBounds(10, 10, 200, 50);
        panel1.add(label1);

        JButton button1 = new JButton("2");
        button1.setBounds(100, 200, 50, 50);
        JButton button2 = new JButton("3");
        button2.setBounds(200, 200, 50, 50);
        JButton button3 = new JButton("4");
        button3.setBounds(300, 200, 50, 50);
        JButton button4 = new JButton("Enter");
        button4.setBounds(650, 500, 100, 50);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);
        panel1.setSize(1100, 650);
        panel2.setSize(1100, 650);
        panel3.setSize(1100, 650);
        panel4.setSize(1100, 650);
        frame1.setSize(1100, 750);
        frame1.setLayout(null);
        frame1.add(panel1);
        frame1.add(panel2);
        frame1.add(panel3);
        frame1.add(panel4);
        frame1.setVisible(true);
        panel1.setVisible(true);


        // frame 2 **************************************
        panel2.setSize(1100, 650);
        panel2.setLayout(null);
        panel2.setVisible(false);
        JLabel label2 = new JLabel("CHOOSE YOUR CHARACTER!");
        label2.setBounds(350, 250, 400, 50);
        label2.setFont(new Font("Courier", Font.BOLD, 30));
        panel2.add(label2);
        JButton characterA = new JButton(pinkIcon);
        characterA.setBounds(100, 50, 200, 200);
        JButton characterB = new JButton(purpleIcon);
        characterB.setBounds(100, 300, 200, 200);
        JButton characterC = new JButton(yellowIcon);
        characterC.setBounds(800, 50, 200, 200);
        JButton characterD = new JButton(redIcon);
        characterD.setBounds(800, 300, 200, 200);
        JButton button5 = new JButton("Enter");
        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(500, 300, 100, 50);
        panel2.add(enterButton);
        enterButton.setVisible(true);
        button5.setBounds(650, 500, 100, 50);
        panel2.add(characterA);
        characterA.setVisible(true);
        panel2.add(characterB);
        characterB.setVisible(true);
        panel2.add(characterC);
        characterC.setVisible(true);
        panel2.add(characterD);
        characterD.setVisible(true);

        // frame 3 **************************************
        //panel3.setSize(900, 700);
//        try {
//            frame3.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("project design documents/board2.jpg")))));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        panel3.setSize(900, 800);//changes width from 900 to 1200!
        JButton boardButton = new JButton(boardIcon); 
        boardButton.setSize(900,800);
        boardButton.setLocation(0,0);   
        panel3.add(boardButton);
        panel3.add(new MovingPlayer(pinkIcon));
        //        try {
//            panel3.setContentPane(
//                    new JLabel(new ImageIcon(ImageIO.read(new File("project design documents/board2.jpg")))));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        panel3.add(board);
        panel3.setVisible(false);

        // frame 4 **************************************
        panel4.setSize(800, 600);
        panel4.setLayout(null);
        panel4.setVisible(false);

        while (!done) {
            // image icon class for images
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // frame 1 **************************************
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    numberOfPlayers = 2;
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    numberOfPlayers = 3;
                }
            });
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    numberOfPlayers = 4;
                }
            });
            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel1.setVisible(false);
                    panel2.setVisible(true);
                }
            });
            characterA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    players.add(new Player());
                    players.get(players.size() - 1).setCharacter("Mally Mallo");
                    board.removePlayerName("Mally Mallo");
                    characterA.setVisible(false);
                }
            });

            characterB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    players.add(new Player());
                    players.get(players.size() - 1).setCharacter("Twirly Girl");
                    board.removePlayerName("Twirly Girl");
                    characterB.setVisible(false);
                }
            });
            characterC.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    players.add(new Player());
                    players.get(players.size() - 1).setCharacter("Cutie Cone");
                    board.removePlayerName("Cutie Cone");
                    characterC.setVisible(false);
                }
            });

            characterD.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    players.add(new Player());
                    players.get(players.size() - 1).setCharacter("Giggly Gumdrop");
                    board.removePlayerName("Giggly Gumdrop");
                    characterD.setVisible(false);
                }
            });
            button5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel2.setVisible(false);
                    panel3.setVisible(true);
                }
            });
            enterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel2.setVisible(false);
                    panel3.setVisible(true);
                }
            });
            int numberOfPlayers = getPlayerInput();
            Scanner sc = new Scanner(System.in);
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.println("Pick character 1-4!"); // the gui will fix this logic
                int character = sc.nextInt();
                players.add(new Player());
                players.get(i).setCharacter(board.playerNames.get(character));
                // board.removePlayerNameIndex(character);
            }

            board.addPlayers(players);
            
              boolean continueGame = true;
              
              while(continueGame) {
                  for (int i = 0; i < players.size(); i++) {
                      players.get(i).playTurn(board);
                      if (players.get(i).checkWinner()) {
                          System.out.println(players.get(i).getCharacter() + " is the Winner");
                          JLabel winner = new JLabel("The winner is " + players.get(i).getCharacter() + "!!!");
                          JLabel text = new JLabel("Congratulations!!!");
                          winner.setFont(new Font("Courier", Font.BOLD, 20));
                          text.setFont(new Font("Courier", Font.BOLD, 20));
                          winner.setSize(500, 50);
                          text.setSize(500, 50);
                          text.setLocation(250, 100);
                          winner.setLocation(175, 200);
                          panel4.add(winner);
                          panel4.add(text);
                          panel3.setVisible(false);
                          panel4.setVisible(true);
                          continueGame = false;
                          break;
                      }
                  }
              }
//            boolean continueGame = true;

            while (continueGame) {
                for (int i = 0; i < players.size(); i++) {
                    players.get(i).playTurn(board);
                    if (players.get(i).checkWinner()) {
                        System.out.println(players.get(i).getCharacter() + " is the Winner");
                        JLabel winner = new JLabel("The winner is " + players.get(i).getCharacter() + "!!!");
                        JLabel text = new JLabel("Congrtulations!!!");
                        winner.setFont(new Font("Courier", Font.BOLD, 20));
                        text.setFont(new Font("Courier", Font.BOLD, 20));
                        winner.setSize(500, 50);
                        text.setSize(500, 50);
                        text.setLocation(250, 100);
                        winner.setLocation(175, 200);
                        panel4.add(winner);
                        panel4.add(text);
                        panel3.setVisible(false);
                        panel4.setVisible(true);
                        continueGame = false;
                        break;
                    }
                }
            }
        }

    }

    public static int getPlayerInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of players playing (2-4): ");
        int numberOfPlayers = scanner.nextInt();
        // instantiate the number of players
        // scanner.close();
        return numberOfPlayers;
    }
}