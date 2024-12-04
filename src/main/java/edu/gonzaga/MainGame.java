/*
 * Final project main driver class
 * 
 * 
 * Project Description: Tanks is a two player game that uses physics to aim and fire at the
 * opponent until health completely runs out for a player. 
 * 
 * Contributors: Abby Fewel, Ayden Humphries, Christian Carrington
 * 
 *  
 * Copyright: 2023
 */
package edu.gonzaga;
/**
 * these import statements probably will not be needed anymore because things are done from ToPlay
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import org.dyn4j.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
*/

/** Main program class for launching your team's program. */
public class MainGame {
    public static void main(String[] args) {
        ToPlay playing = new ToPlay();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainGame game = new MainGame();
                System.out.println("Tanks");
                playing.formattingIntroScreen();
            }
        });
    }
}