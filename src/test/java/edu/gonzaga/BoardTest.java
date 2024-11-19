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
    void checkVerticalFrontShipHitDetection() {

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
    void checkVerticalBackShipHitDetection() {

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
    void checkHorizontalFrontShipHitDetection() {

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
    void checkHorizontalBackShipHitDetection() {

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

    // ------------------------ Valid Ship Placement tests: --------------------------------

    @Test
    void checkValidShipPlacement() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(5, 5, false, 3);

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is true: the ship can be placed." );
        assertTrue( testBoard.validateShipPlacement( testShip ) );
    }

    @Test
    void checkValidShipPlacement2() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(2, 8, false, 7);

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is true: the ship can be placed." );
        assertTrue( testBoard.validateShipPlacement( testShip ) );
    }

    @Test
    void checkValidShipPlacement3() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(11, 5, false, 3);

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is false: the ship cannot be placed." );
        assertFalse( testBoard.validateShipPlacement( testShip ) );
    }

    @Test
    void checkValidShipPlacement4() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(5, 11, false, 1);

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is false: the ship cannot be placed." );
        assertFalse( testBoard.validateShipPlacement( testShip ) );
    }

    @Test
    void checkValidShipPlacement5() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(5, 10, true, 2);

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is false: the ship cannot be placed." );
        assertFalse( testBoard.validateShipPlacement( testShip ) );
    }

    // ---------------------------- ship sinking method checks -----------------------------

    @Test
    void checkIsShipSunk() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(4, 4, true, 4);

        // Hit boat segments:
        testBoard.setMarked(4, 4, true);
        testBoard.setMarked(4, 5, true);
        testBoard.setMarked(4, 6, true);
        testBoard.setMarked(4, 7, true);

        // Check if isShipSunk() recognizses that a ship has been sunk:
        System.out.println( "Expected state is true: A ship has been sunk." );
        assertTrue( testBoard.isShipSunk( testShip ) );
    }

    @Test
    void checkIsShipSunk2() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(4, 4, true, 4);

        // Hit boat segments:
        testBoard.setMarked(4, 4, true);
        testBoard.setMarked(4, 5, true);
        testBoard.setMarked(4, 6, true);
        testBoard.setMarked(4, 8, true);

        // Check if isShipSunk() recognizses that a ship has been sunk:
        System.out.println( "Expected state is false: A ship has not been sunk." );
        assertFalse( testBoard.isShipSunk( testShip ) );
    }
}