package edu.gonzaga;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * This class holds all the code under main. This is where the stuff under the abstraction happens.
 */

public class Tanks { 
    private Integer xCord;
    private Integer yCord;
    private String color;

    public void Tank() {
        xCord = 0;
        yCord = 0;
        color = "Red";
    }

    public String getColor() {
        return color;
    }

}