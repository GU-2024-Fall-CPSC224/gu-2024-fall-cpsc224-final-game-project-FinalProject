package edu.gonzaga;
/*
 * This is Tank class holds all information regarding Tanks the players will be controlling. 
 */

public class Tank { 
    private Integer health; 
    private Integer xCord;
    private Integer yCord;
    private String color;

    public Tank() {
        health = 100;
        xCord = 0;
        yCord = 0; 
        color = "Red"; 
    }

    //constructor that allows for all inputs
    public Tank(Integer healthSet, Integer x, Integer y, String colorSet){
        health = healthSet;
        xCord = x;
        yCord = y;
        color = colorSet; 
    }

    //constructor for just changing the health but all other default
    public Tank(Integer healthSet){
        health = healthSet; 
        xCord = 0;
        yCord = 0;
        color = "Red"; 
    }

    //constructor for just changing the coordinates but all other default
    public Tank(Integer x, Integer y){
        health = 100; 
        xCord = x; 
        yCord = y;
        color = "Red"; 
    }

    //constructor for just changing the color but all other defaults
    public Tank(String colorSet){
        health = 100;
        xCord = 0;
        yCord = 0; 
        color = colorSet; 
    }
    
    public String getColor() {
        return this.color;
    }

    public Integer getHealth(){
        return this.health; 
    }

    public Integer getXCord(){
        return this.xCord;
    }

    public Integer getYCord(){
        return this.yCord;
    }

    public void setHealth(Integer healthSet){
        health = healthSet; 

    }

    public void setXCord(Integer xSet){
        xCord = xSet;
    }

    public void setYCord(Integer ySet){
        yCord = ySet;
    }

    public void setColor(String colorSet){
        color = colorSet; 
    }

}