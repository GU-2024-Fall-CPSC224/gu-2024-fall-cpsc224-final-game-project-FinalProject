package edu.gonzaga;

import java.util.ArrayList;



public class Board {
    
    // -----------------------------------
    // ATTRIBUTES START HERE
    // -----------------------------------

    // BOARD_SIZE holds the current size of the board in both rows and columns
    private static final int BOARD_SIZE = 10;

    // ships stores all ships placed on the player's board.
    private ArrayList<Ship> ships;

    // markers stores all markers placed on the player's board.
    private boolean[][] markers;

    // -----------------------------------
    // METHODS START HERE
    // -----------------------------------


    /**
     * Basic constructor of a board.
     */ 
    public Board() {
        ships = new ArrayList<>();
        // Generate a new blank list for holding markers
        markers = new boolean[BOARD_SIZE][BOARD_SIZE];
    }


    /**
     * isMarked() checks a player-specified space for a marker.
     * @param x coordinate
     * @param y coordinate
     * @return true/false there is a mark on the selected tile.
     */
    public boolean isMarked(int x, int y){
        return markers[x][y];
    }


    /**
     * setMarked() sets the marker of this tile as a hit or miss.
     * @param x x-coordinate of attempted shot
     * @param y y-coordinate of attempted shot
     * @param hit does that tile have a boat on it
     */
    public void setMarked(int x, int y, boolean hit){
        //if hit: do something in Swing ()
        markers[x][y] = true;
    }


    /**
     * isShipSunk() checks whether a just-hit ship is now "sunk" and removed from the game.
     * @param attackedShip ship that has part of itself in the tile that was hit
     * @return if the ship is has all tiles hit, and is sunk
     */
    public boolean isShipSunk(Ship attackedShip){
        //check if all other x and y have been hit for the ship, if so return true
        return false;
    }


    /**
     * addShip() adds a ship to the array list of ships sotred on the board.
     * @param boat
     */
    public void addShip(Ship boat){
        //add a boat to the arraylist of active boats
    }


    /**
     * Is this marker a hit or miss
     * @param x -coordinate of attempted shot
     * @param y -coordinate of attempted shot
     * @return is there a ship on that tile
     */
    public boolean isMarkerHit(int x, int y){
        return false;
    }
}
