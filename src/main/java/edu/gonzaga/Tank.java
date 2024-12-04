package edu.gonzaga;
/*
 * This is Tank class holds all information regarding Tanks the players will be controlling. 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tank {
    private Integer xCord;
    private Integer yCord;
    private String color;
    private Integer shotCount = 0;
    private Integer trajectory;
    private Integer health;
    private Boolean moved;

    public Tank() {
        health = 100;
        xCord = 0;
        yCord = 0;
        color = "Red";
    }

    // constructor that allows for all inputs
    public Tank(Integer healthSet, Integer x, Integer y, String colorSet) {
        health = healthSet;
        xCord = x;
        yCord = y;
        color = colorSet;
    }

    // constructor for just changing the health but all other default
    public Tank(Integer healthSet) {
        health = healthSet;
        xCord = 0;
        yCord = 0;
        color = "Red";
    }

    // constructor for just changing the coordinates but all other default
    public Tank(Integer x, Integer y) {
        health = 100;
        xCord = x;
        yCord = y;
        color = "Red";
    }

    // constructor for just changing the color but all other defaults
    public Tank(String colorSet) {
        health = 100;
        xCord = 0;
        yCord = 0;
        color = colorSet;
    }

    // Getters
    public String getColor() {
        return this.color;
    }

    public Integer getHealth() {
        return this.health;
    }

    public Integer getXCord() {
        return this.xCord;
    }

    public Integer getYCord() {
        return this.yCord;
    }

    public Integer getTrajectory() {
        return this.trajectory;
    }

    // Setters
    public void setHealth(Integer healthSet) {
        health = healthSet;
    }

    public void setXCord(Integer xSet) {
        xCord = xSet;
    }

    public void setYCord(Integer ySet) {
        yCord = ySet;
    }

    public void setColor(String colorSet) {
        color = colorSet;
    }

    public void setTrajectory(Integer x) {
        trajectory = x;
    }

    // This will change the x and y coordinates depending on how much the player
    // moves
    public void move() {

    }

    // This will fire the tank and increase shotcount, uses artillery
    public int fire() {
        // returning 0 until artillery is made and can be used
        return 0;
    }

    // This will tell if we hit the tank by comparing the missle location with the
    // location of enemy tank
    public int hit() {
        // returning 0 for now
        return 0;
    }

    public Integer moveMeLeft() {
        if (this.xCord < 5) {
            this.xCord = 0;
        } else {
            this.xCord -= 5;
        }
        moved = true;
        return xCord;
    }

    public Integer moveMeRight() {
        if (this.xCord > 195) {
            this.xCord = 200;
        } else {
            this.xCord += 5;
        }
        moved = true;
        return xCord;
    }

    public Boolean getMoved() {
        return moved;
    }

}