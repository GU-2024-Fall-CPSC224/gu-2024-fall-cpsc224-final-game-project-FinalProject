package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ArtilleryTest {

    @Test
    void testDefaultConstructor() {
        Artillery artillery = new Artillery();

        // Verify default values
        assertEquals(1.0, artillery.getRadius(), "Default radius should be 1.0");
        assertEquals(0, artillery.getPower(), "Default power should be 0");
        assertEquals(0, artillery.getArtilleryX(), "Default X-coordinate should be 0");
        assertEquals(0, artillery.getArtilleryY(), "Default Y-coordinate should be 0");
        assertFalse(artillery.isHit(), "Default hit should be false");
    }

    @Test
    void testParameterizedConstructor() {
        Artillery artillery = new Artillery(5.0, 100, 10, 20, true);

        // Verify initialized values
        assertEquals(5.0, artillery.getRadius(), "Radius should be 5.0");
        assertEquals(100, artillery.getPower(), "Power should be 100");
        assertEquals(10, artillery.getArtilleryX(), "X-coordinate should be 10");
        assertEquals(20, artillery.getArtilleryY(), "Y-coordinate should be 20");
        assertTrue(artillery.isHit(), "Hit should be true");
    }

    @Test
    void testSetAndGetPower() {
        Artillery artillery = new Artillery();

        artillery.setPower(50);
        assertEquals(50, artillery.getPower(), "Power should be set to 50");

        artillery.setPower(0);
        assertEquals(0, artillery.getPower(), "Power should be set to 0");
    }

    @Test
    void testSetAndGetHit() {
        Artillery artillery = new Artillery();

        artillery.setHit(true);
        assertTrue(artillery.isHit(), "Hit should be true");

        artillery.setHit(false);
        assertFalse(artillery.isHit(), "Hit should be false");
    }

    @Test
    void testSetAndGetArtilleryX() {
        Artillery artillery = new Artillery();

        artillery.setArtilleryX(15);
        assertEquals(15, artillery.getArtilleryX(), "X-coordinate should be set to 15");

        artillery.setArtilleryX(-5);
        assertEquals(-5, artillery.getArtilleryX(), "X-coordinate should be set to -5");
    }

    @Test
    void testSetAndGetArtilleryY() {
        Artillery artillery = new Artillery();

        artillery.setArtilleryY(25);
        assertEquals(25, artillery.getArtilleryY(), "Y-coordinate should be set to 25");

        artillery.setArtilleryY(-10);
        assertEquals(-10, artillery.getArtilleryY(), "Y-coordinate should be set to -10");
    }

    @Test
    void testToString() {
        Artillery artillery = new Artillery(5.0, 100, 10, 20, true);

        String expected = "Artillery [radius=5.0, power=100, artilleryX=10, artilleryY=20, hit=true]";
        assertEquals(expected, artillery.toString(), "toString should match the expected format");

        // Update values and recheck
        artillery.setPower(50);
        artillery.setArtilleryX(5);
        artillery.setArtilleryY(15);
        artillery.setHit(false);

        expected = "Artillery [radius=5.0, power=50, artilleryX=5, artilleryY=15, hit=false]";
        assertEquals(expected, artillery.toString(), "toString should reflect updated values");
    }
}
