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
    static ArrayList <JLabel> playerIcons = new ArrayList<JLabel>();

    public static void moveIcon(JLabel button, Point newLocation) {
        button.setBounds((int)newLocation.getX() - 10, (int)newLocation.getY(), 15, 30);
    }

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
        JButton drawButton = new JButton("Draw Card");
        JButton movePlayerButton = new JButton("Move");
        JLabel cardPrint = new JLabel("bob");
        boardButton.setSize(900,800);
        boardButton.setLocation(0,0);   
        panel3.add(boardButton);
        drawButton.setSize(50,20);
        drawButton.setLocation(825,200);
        movePlayerButton.setSize(50,20);
        movePlayerButton.setLocation(825,750);
        panel3.add(drawButton);
        panel3.add(movePlayerButton);
        panel3.add(cardPrint);
        //        try {
//            panel3.setContentPane(
//                    new JLabel(new ImageIcon(ImageIO.read(new File("project design documents/board2.jpg")))));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        panel3.add(board);
        panel3.setVisible(false);

        // frame 4 **************************************
        JLabel one = new JLabel(pinkIcon);
        JLabel two = new JLabel(purpleIcon);
        JLabel three = new JLabel(redIcon);
        JLabel four = new JLabel(yellowIcon);
        
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
                    players.get(players.size() - 1).setLabel(1);
                }
            });

            characterB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    players.add(new Player());
                    players.get(players.size() - 1).setCharacter("Twirly Girl");
                    board.removePlayerName("Twirly Girl");
                    characterB.setVisible(false);
                    players.get(players.size() - 1).setLabel(2);
                }
            });
            characterC.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    players.add(new Player());
                    players.get(players.size() - 1).setCharacter("Cutie Cone");
                    board.removePlayerName("Cutie Cone");
                    characterC.setVisible(false);
                    players.get(players.size() - 1).setLabel(3);
                }
            });

            characterD.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    players.add(new Player());
                    players.get(players.size() - 1).setCharacter("Giggly Gumdrop");
                    board.removePlayerName("Giggly Gumdrop");
                    characterD.setVisible(false);
                    players.get(players.size() - 1).setLabel(4);
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
                    panel3.setLayout(null);
                    panel3.setComponentZOrder(boardButton, 1);
                    for(int i = 0; i < players.size(); i++)
                    {
                        if(players.get(i).getLabel() == 1)
                        {
                            panel3.add(one);
                            one.setVisible(true);
                            moveIcon(one, board.candyPath.get(0));
                            panel3.setComponentZOrder(one, 0);
                            playerIcons.add(one);
                        }
                        if(players.get(i).getLabel() == 2)
                        {
                            panel3.add(two);
                            two.setVisible(true);
                            moveIcon(two, board.candyPath.get(0));
                            panel3.setComponentZOrder(two, 0);
                            playerIcons.add(two);
                        }
                        if(players.get(i).getLabel() == 3)
                        {
                            panel3.add(three);
                            three.setVisible(true);
                            moveIcon(three, board.candyPath.get(0));
                            panel3.setComponentZOrder(three, 0);
                            playerIcons.add(three);
                        }
                        if(players.get(i).getLabel() == 4)
                        {
                            panel3.add(four);
                            four.setVisible(true);
                            moveIcon(four, board.candyPath.get(0));
                            panel3.setComponentZOrder(four, 0);
                            playerIcons.add(four);
                        }
                    }
                }
            });
           /* int numberOfPlayers = getPlayerInput();
            Scanner sc = new Scanner(System.in);
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.println("Pick character 1-4!"); // the gui will fix this logic
                int character = sc.nextInt();
                players.add(new Player());
                players.get(i).setCharacter(board.playerNames.get(character));
                // board.removePlayerNameIndex(character);
            }
                */
            board.addPlayers(players);
            
              boolean continueGame = true;
              
              while(continueGame) {
                  for (int i = 0; i < players.size(); i++) {
                      players.get(i).playTurn(board);
                      moveIcon(one, players.get(i).loc);
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
                    moveIcon(playerIcons.get(i), players.get(i).loc);
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