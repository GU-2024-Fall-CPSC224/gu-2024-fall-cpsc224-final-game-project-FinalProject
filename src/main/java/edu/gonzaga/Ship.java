package edu.gonzaga;

import java.util.ArrayList;

public abstract class Ship {
    
    // -----------------------------------
    // ATTRIBUTES START HERE
    // -----------------------------------
    
    //private final int x;
    //private final int y;

    //Primary ship coordinates are stored here.
    private Coordinate primaryShipCoordinates;

    // Determines if the ship will extend downwards or to the right.
    private final boolean isVertical;

    // Holds the state of whether the ship is still in play.
    private boolean isSunk;

    // -----------------------------------
    // METHODS START HERE
    // -----------------------------------


    /**
     * Basic ship constructor
     * @param x
     * @param y
     * @param isVertical
     */
    public Ship(int x, int y, boolean isVertical) {
        
        //this.x = x;
        //this.y = y;

        //Set ship primary coordinates:
        this.primaryShipCoordinates = new Coordinate( x, y );
        this.isVertical = isVertical;
    }


    /**
     * getPrimaryCoordinate() gets the primary (main) coordinate of a ship.
     * 
     * @return Coordinate primaryShipCoordinates
     */
    public Coordinate getPrimaryShipCoordinates() {
        return primaryShipCoordinates;
    }


    /**
     * getX() gets the x coordinate of the front / nose of the ship.
     */
    public int getX() {
        
        //return x;
        
        return primaryShipCoordinates.getX();
    }


    /**
     * getY() gets the y coordinate of the front / nose of the ship.
     * 
     * @return y coordinate
     */
    public int getY() {
        
        //return y;

        return primaryShipCoordinates.getY();
    }


    /**
     * isVertical() checks the direction the ship is facing (upwards to to the right)
     *
     *  @return True/false the ship is facing vertical (upwards / straight)
     */
    public boolean isVertical() {
        return isVertical;
    }


    /** 
     * getLength() returns the length of the ship.
    */
    public abstract int getLength();


    /**
     * setIsSunk() sets whether the ship is currently in play.
     */
    public void setIsSunk( Boolean newStatus ) {
        isSunk = newStatus; // <------ Does this need newStatus? Can't we just set it to false? It's not coming back INTO play is it?
    }


    /** 
     * getAllCoordinates() takes the primary coordinates of a ship, and calculates each
     * additional coordinate point the ship occupies. A ship should know where it is.
     * 
     * THIS IS THE HELPER FUNCTION THAT REPLACES the isMarkerHit() comparison.
     * This is what the coordinate class was primary implemented for.
     */
    public ArrayList<Coordinate> getAllCoordinates() {
        
        // Generate a new coordinate arraylist. This will contain all the calculated coordinates.
        ArrayList<Coordinate> shipCoordinates = new ArrayList<>();
        
        Integer shipXCoord = primaryShipCoordinates.getX();
        Integer shipYCoord = primaryShipCoordinates.getY();
        // Add the primary ship coordinates to the array as well!
        shipCoordinates.add( primaryShipCoordinates );

        Integer shipLength = this.getLength();

        // For each section of the ship, increment it's coordinates (depending on direction) and add the to the list!
        // NOTE: i is set to 1 to account for the primaryShipCoordinate!
        for ( int i = 1; i < shipLength; i++ ) {
            
            // If the ship is facing downwards:
            if ( isVertical == true ) {
                shipYCoord++;
            }
            // Else if the ship is facing to the right:
            else {
                shipXCoord++;
            }
            // Add the new coordinate to the shipCoordinates list.
            shipCoordinates.add( new Coordinate( shipXCoord, shipYCoord ) );
            
        } // All ship sections assigned coordinates.
        // Return the calculated ship coordinates.
        return shipCoordinates;
    }
}
