package edu.gonzaga;
import org.dyn4j.geometry.Rectangle;

public class Tank extends Rectangle {
    private int health;
    private String color;

    public Tank(double width, double height, int health, String color) {
        super(width, height); // Initialize the Rectangle with width and height
        this.health = health;
        this.color = color;
    }

    // Getter and Setter for health
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // Getter and Setter for color
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Reduce health when hit
    public void hit(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0; // Ensure health doesn't go below zero
        }
    }

    @Override
    public String toString() {
        return "Tank [width=" + this.getWidth() + ", height=" + this.getHeight() + 
               ", health=" + health + ", color=" + color + "]";
    }
}
