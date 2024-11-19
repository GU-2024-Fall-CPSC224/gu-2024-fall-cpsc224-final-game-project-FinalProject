package edu.gonzaga;

import java.util.ArrayList;



public class Board {
    
    // -----------------------------------
    // ATTRIBUTES START HERE
    // -----------------------------------

    // BOARD_SIZE holds the current size of the board in both rows and columns
    private static final int BOARD_SIZE = 10;

    // shipList stores all ships placed on the player's board.
    private ArrayList<Ship> shipList;

    // markers stores all markers placed on the player's board.
    private boolean[][] markers;

    // -----------------------------------
    // METHODS START HERE
    // -----------------------------------


    /**
     * Basic constructor of a board.
     */ 
    public Board() {
        shipList = new ArrayList<>();
        // Generate a new blank list for holding markers
        markers = new boolean[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * Returns an array of ships on the board.
     */
    public Ship[] getShips() {
        return shipList.toArray(new Ship[0]);
    }


    /**
     * isMarked() checks a player-specified space for a marker.
     * @param x coordinate
     * @param y coordinate
     * @return true/false there is a mark on the selected tile.
     */
    public boolean isMarked(int x, int y){
        
        // Adding coordinate object here. Fully transition?
        Coordinate checkCoordinate = new Coordinate( x, y );
        
        //return markers[y][x]; // < --- markers[][] here flips coords?
        return markers[ checkCoordinate.getY() ][ checkCoordinate.getX() ];
    }


    /**
     * setMarked() sets the marker of this tile as a hit or miss.
     * @param x x-coordinate of attempted shot
     * @param y y-coordinate of attempted shot
     * @param hit does that tile have a boat on it
     */
    public void setMarked(int x, int y, boolean hit){

        // Move player x and y into coordinate.
        Coordinate playerCoord = new Coordinate( x, y );

        // When checking a space, determine if a ship occupies it (a.k.a. the ship is "hit").
        isMarkerHit( playerCoord );

        // Once you determine the status of the marker ( hit or miss ), you can update the image appropriately.
        // This would also be where the game tells the ship to note it's been hit? <<<< printboard currently does this too.

        //markers[x][y] = true;     // < --- markers[][] here flips coords?
        markers[ playerCoord.getY() ][ playerCoord.getX() ] = true; 
    }


    /**
     * isShipSunk() checks whether a just-hit ship is now "sunk" and removed from the game.
     * @param attackedShip ship that has part of itself in the tile that was hit
     * @return if the ship is has all tiles hit, and is sunk
     */
    public boolean isShipSunk(Ship attackedShip){

        // segmentCoordnets contains the coordinate objects of each segment of the current ship being checked.
        ArrayList<Coordinate> segmentCoordinates = new ArrayList<>();
        segmentCoordinates = attackedShip.getAllCoordinates();

        for (Coordinate coordinate : segmentCoordinates) {
            // If a segment of the ship remains unmarked, return false.
            if ( isMarked( coordinate.getX(), coordinate.getY() ) == false ) {
                return false;
            }
        }
        // If all ship segments have been marked, return true.
        return true; // <---------- This should probably be changed to call a Ship method setIsSunk on the attacked ship.
    }


    /**
     * addShip() adds a ship to the array list of ships sorted on the board.
     * @param newShip
    */
    public void addShip(Ship newShip ){
        //add a boat to the arraylist of active boats:

        // <-------------- I imagine that this could be where we put the validateShipPlacement method call?

        shipList.add( newShip );
    }


    /**
     * validateShipPlacement() takes a pair of coordinates and checks if a ship
     * can be placed there, comparing ship direction and length.
     * @param newShip
     * @return true / false the ship can be placed at these coordinates.
    */
    public Boolean validateShipPlacement( Ship newShip ){
        // CONCERN: currently, I'm coding this function to take in a ship object, which already
        // knows it's lenght and direction. Do we want this function to compare direct length / x and y instead?

        // Get ship coordinates:
        ArrayList<Coordinate> segmentCoordinates = new ArrayList<>();
        segmentCoordinates = newShip.getAllCoordinates();

        // Check ship placement:
        for (Coordinate coordinate : segmentCoordinates) {
            // Check X:
            if ( ( coordinate.getX() > 10 ) || (coordinate.getX() < 1 ) ) {
                return false;
            }
            // Check Y:
            if ( ( coordinate.getY() > 10 ) || (coordinate.getY() < 1 ) ) {
                return false;
            }
        }
        // If all spaces are checked without issue, then return true.
        return true;
    }


    /**
     * isMarkerHit() determines if the checked space contains a ship by subsiquently
     * checking every space that is overlapping with a ship.
     * 
     * @param markerX -coordinate of attempted shot
     * @param markerY -coordinate of attempted shot
     * @return is there a ship on that tile
     */
    public boolean isMarkerHit( Coordinate playerCoord ){

        // Check if the checked space hits a ship:
        for ( Ship ship : shipList ) {
            // Get the coordinates of the ship's segments:

            // segmentCoordnets contains the coordinate objects of each segment of the current ship being checked.
            ArrayList<Coordinate> segmentCoordinates = new ArrayList<>();
            segmentCoordinates = ship.getAllCoordinates();

            // Compare each ssegment against the space being checked:
            // If they are equal, return true.
            for ( Coordinate checkingCoord : segmentCoordinates ) {
                if ( checkingCoord.equals( playerCoord ) ) {
                    return true;
                }
            }
            // If the coordinates are not equal, check next coordinate.
        } // All ships checked.
        // If no coordinates overlap, than return false. The checked space is not a hit.
        return false;
    }


    /**
     * getAllShipCoordinates takes each ship in the board's shiplist and calculates the coordinates
     * of each "segment" of each ship, returning all these coordinates in an array.
     */
    public ArrayList<Integer> getAllShipCoordinates(){
        ArrayList<Integer> shipCoordinates = new ArrayList<>();
        return shipCoordinates;
    }


    /**
     * printBoard() is used for testing purposes, and prints an ASCII-based version
     * of the player board / G.U.I., with all markers shown.
     * 
     */
    public void printBoard(){
        // for all rows.
        for (int i = 0; i < markers.length; i++){
            // for all columns.
            for (int j = 0; j < markers[i].length; j++){
                // If the space printed has a set marker, display it.
                if (markers[i][j] == true){
                    // If the marker is a hit, display a hit marker.
                    if ( isMarkerHit( new Coordinate( i, j ) ) ){ // <<<<<< Added coordinate here so this still works. Hopefully.
                        System.out.print(" X ");
                    }
                    // If the marker is a miss, display a miss marker.
                    else {
                        System.out.print(" O ");
                    }
                }
                // If the printed space has no marker status, print the default board background.
                else {
                    System.out.print(" ~ ");
                }
            }
            System.out.println("");
        }
    }
}
