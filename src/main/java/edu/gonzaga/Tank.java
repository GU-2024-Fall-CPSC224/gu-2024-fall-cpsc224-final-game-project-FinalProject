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