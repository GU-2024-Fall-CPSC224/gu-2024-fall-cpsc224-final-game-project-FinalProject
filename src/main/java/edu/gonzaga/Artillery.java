package edu.gonzaga;

import org.dyn4j.geometry.Circle;

public class Artillery extends Circle {
    private int power;
    private boolean hit;
    private int artilleryX;
    private int artilleryY;

    /**
     * Default constructor initializes the Circle with radius 1.0 and other fields to default values.
     */
    public Artillery() {
        super(1.0); // Initialize Circle with a default radius of 1.0
        this.power = 0;
        this.hit = false;
        this.artilleryX = 0;
        this.artilleryY = 0;
    }

    /**
     * Constructor to initialize the Circle and additional fields.
     * 
     * radius     Radius of the circle representing the artillery.
     * power      Power of the artillery.
     * artilleryX X-coordinate of the artillery.
     * artilleryY Y-coordinate of the artillery.
     * hit        Whether the artillery has hit its target.
     */
    public Artillery(double radius, int power, int artilleryX, int artilleryY, boolean hit) {
        super(radius); // Initialize Circle with specified radius
        this.power = power;
        this.artilleryX = artilleryX;
        this.artilleryY = artilleryY;
        this.hit = hit;
    }

    // Getter and Setter for power
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    // Getter and Setter for hit
    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    // Getter and Setter for artilleryX
    public int getArtilleryX() {
        return artilleryX;
    }

    public void setArtilleryX(int artilleryX) {
        this.artilleryX = artilleryX;
    }

    // Getter and Setter for artilleryY
    public int getArtilleryY() {
        return artilleryY;
    }

    public void setArtilleryY(int artilleryY) {
        this.artilleryY = artilleryY;
    }

    @Override
    public String toString() {
        return "Artillery [radius=" + this.getRadius() + ", power=" + power + 
               ", artilleryX=" + artilleryX + ", artilleryY=" + artilleryY + 
               ", hit=" + hit + "]";
    }
}
