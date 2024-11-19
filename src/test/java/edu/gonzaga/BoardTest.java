package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {
    
    @Test
    void checkIsMarked() {
        // Generate test board
        Board testBoard = new Board();

        System.out.println( "Expected boolean state is false: the checked tile is not marked." );
        assertFalse( testBoard.isMarked(0, 0) );
    }

    @Test
    void checkSetMarked() {
        // Generate test board
        Board testBoard = new Board();

        testBoard.setMarked(0, 0, false);

        System.out.println( "Expected boolean state is true: the checked tile is not marked." );
        assertTrue( testBoard.isMarked(0, 0) );
    }

    @Test 
    void checkVerticalFrontShipDetection() {

        // Generate test board
        Board testBoard = new Board();

        // Add a test ship to the board, facing downward and starting at coordinate [5, 5]:
        testBoard.addShip( new GenericShip(5, 5, true, 3) );
        // Add test coordinate to simulate player input:
        Coordinate testCoordinate = new Coordinate( 5, 5 );

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is true: the checked tile contains a ship. HIT!" );
        assertTrue( testBoard.isMarkerHit( testCoordinate ) );
    }

    @Test 
    void checkVerticalBackShipDetection() {

        // Generate test board
        Board testBoard = new Board();

        // Add a test ship to the board, facing downward and starting at coordinate [5, 5]:
        testBoard.addShip( new GenericShip(5, 5, true, 3) );
        // Add test coordinate to simulate player input:
        Coordinate testCoordinate = new Coordinate( 5, 7 );

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is true: the checked tile contains a ship. HIT!" );
        assertTrue( testBoard.isMarkerHit( testCoordinate ) );
    }

    @Test 
    void checkHorizontalFrontShipDetection() {

        // Generate test board
        Board testBoard = new Board();

        // Add a test ship to the board, facing downward and starting at coordinate [5, 5]:
        testBoard.addShip( new GenericShip(5, 5, false, 3) );
        // Add test coordinate to simulate player input:
        Coordinate testCoordinate = new Coordinate( 5, 5 );

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is true: the checked tile contains a ship. HIT!" );
        assertTrue( testBoard.isMarkerHit( testCoordinate ) );
    }

    @Test 
    void checkHorizontalBackShipDetection() {

        // Generate test board
        Board testBoard = new Board();

        // Add a test ship to the board, facing downward and starting at coordinate [5, 5]:
        testBoard.addShip( new GenericShip(5, 5, false, 3) );
        // Add test coordinate to simulate player input:
        Coordinate testCoordinate = new Coordinate( 7, 5 );

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is true: the checked tile contains a ship. HIT!" );
        assertTrue( testBoard.isMarkerHit( testCoordinate ) );
    }
}