package edu.gonzaga;
public class Coordinate implements Comparable<Coordinate> {
    
    // -----------------------------------
    // ATTRIBUTES START HERE
    // -----------------------------------
    
    private Integer x = 0;
    private Integer y = 0;

    // -----------------------------------
    // METHODS START HERE
    // -----------------------------------

    /**
     * Base constructor for a coordinate point. Creates a set of coordinates from
     * x and y values provided by the player(s).
     */
    public Coordinate( Integer xCoordinate, Integer yCoordinate ) {
        x = xCoordinate;
        y = yCoordinate;
    }


    /**
     * getX() returns the x-coordniate of a coordniate point.
     * @return Integer the x-coordinate of the point.
     */
    public Integer getX() {
        return x;
    }


    /**
     * getY() returns the y-coordniate of a coordniate point.
     * @return Integer the y-coordinate of the point.
     */
    public Integer getY() {
        return y;
    }


    /**
     * compareTo() compares the coordniate point to another coordinate point,
     * and is used to sort out coordinates in array lists.
     * @override compareTo()
     */
    @Override
    public int compareTo( Coordinate o ) {
        
        // Check if the two coordinates are equal.
        if ( ( this.x == o.getX() ) && ( this.y == o.getY() ) ) {
            return 0;
        }
        // If the coordinates are not equal, see if they are larger or smaller than each-other.
        // NOTE: This isn't important for battleship, but it needs to be implemented.
        else {
            if ( ( this.x >= o.getX() ) && ( this.y >= o.getY() ) ) {
                return 1;
            }
            else{
                return -1;
            }
        }
    }


    /**
     *  equals() compares Coordinate objects for equality
     *  @override equals()
     */
    @Override
    public boolean equals( Object obj ) {
        // If the object is compared to itself, return true.
        if ( this == obj ) {
            return true;
        }
        // If the object compared to is of a different class / data type, or is null, return false.
        if ( ( obj == null ) || ( getClass() != obj.getClass() ) ) {
            return false;
        } 
        // If both objects are of the same type, compare both object coordinates.
        Coordinate that = (Coordinate) obj;
        return x == that.x && y == that.y;
    }


    // Override hashCode to ensure consistency with equals
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
