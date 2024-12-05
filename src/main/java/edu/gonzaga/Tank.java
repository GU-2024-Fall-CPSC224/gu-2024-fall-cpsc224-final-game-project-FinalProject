package edu.gonzaga;

import java.awt.Color;
import java.awt.Graphics2D;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

public class Tank extends Rectangle {
    private Integer xCord;
    private Integer yCord;
    private String color;
    private Integer shotCount = 0;
    private Integer trajectory;
    private Integer health;
    private Boolean moved;
    private Body body;

    // Constructor for creating a Tank with default settings
    public Tank() {
        super(10, 10); // Default width and height of the rectangle
        this.health = 100;
        this.xCord = 0;
        this.yCord = 0;
        this.color = "Red";
        initializeBody();
    }

    // Constructor that allows for all inputs
    public Tank(Integer healthSet, Integer x, Integer y, String colorSet) {
        super(10, 10); // Default width and height of the rectangle
        this.health = healthSet;
        this.xCord = x;
        this.yCord = y;
        this.color = colorSet;
        initializeBody();
    }

    // Constructor to change only the health
    public Tank(Integer healthSet) {
        super(10, 10); // Default width and height of the rectangle
        this.health = healthSet;
        this.xCord = 0;
        this.yCord = 0;
        this.color = "Red";
        initializeBody();
    }

    // Constructor to change only the coordinates
    public Tank(Integer x, Integer y) {
        super(10, 10); // Default width and height of the rectangle
        this.health = 100;
        this.xCord = x;
        this.yCord = y;
        this.color = "Red";
        initializeBody();
    }

    // Constructor to change only the color
    public Tank(String colorSet) {
        super(10, 10); // Default width and height of the rectangle
        this.health = 100;
        this.xCord = 0;
        this.yCord = 0;
        this.color = colorSet;
        initializeBody();
    }

    // Initialize the body with a rectangle in the physics world
    private void initializeBody() {
        body = new Body();
        body.addFixture(this);
        body.setMass(org.dyn4j.geometry.MassType.NORMAL);
        body.translate(new Vector2(this.xCord, this.yCord));
    }

    // Getters and Setters
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

    public Body getBody(){
        return this.body; 
    }

    // Setters
    public void setHealth(Integer healthSet) {
        health = healthSet;
    }

    public void setXCord(Integer xSet) {
        xCord = xSet;
        body.translate(new Vector2(xCord, yCord));
    }

    public void setYCord(Integer ySet) {
        yCord = ySet;
        body.translate(new Vector2(xCord, yCord));
    }

    public void setColor(String colorSet) {
        color = colorSet;
    }

    public void setTrajectory(Integer x) {
        trajectory = x;
    }

    public Boolean getMoved() {
        return moved;
    }

    // This method will move the tank left
    public Integer moveMeLeft() {
        if (this.xCord < 5) {
            this.xCord = 0;
        } else {
            this.xCord -= 5;
        }
        moved = true;
        body.translate(new Vector2(-5, 0));
        return xCord;
    }

    // This method will move the tank right
    public Integer moveMeRight() {
        if (this.xCord > 195) {
            this.xCord = 200;
        } else {
            this.xCord += 5;
        }
        moved = true;
        body.translate(new Vector2(5, 0));
        return xCord;
    }

    // Paints the tank with its current color (override the default rendering)
    public void paint(Graphics2D g) {
        g.setColor(Color.getColor(this.color, Color.RED)); // Set the color of the tank
        g.fillRect(this.xCord, this.yCord, (int)this.getWidth(), (int)this.getHeight());
    }

    // Additional functionality for firing, hitting, and other actions can be added here
    public int fire() {
        // Returning 0 until artillery mechanics are added
        return 0;
    }

    public int hit( int hit) {
        // Returning 0 for now (add hit detection logic later)
        return 0;
    }
}
