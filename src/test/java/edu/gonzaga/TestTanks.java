package edu.gonzaga;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TankTest {

    @Test
    void testConstructor() {
        Tank tank = new Tank(2.0, 1.0, 100, "red",100);

        // Verify initial values
        assertEquals(2.0, tank.getWidth(), "Width should be 2.0");
        assertEquals(1.0, tank.getHeight(), "Height should be 1.0");
        assertEquals(100, tank.getHealth(), "Health should be 100");
        assertEquals("red", tank.getColor(), "Color should be red");
    }

    @Test
    void testSetAndGetHealth() {
        Tank tank = new Tank(2.0, 1.0, 100, "red",100);

        // Change health
        tank.setHealth(80);
        assertEquals(80, tank.getHealth(), "Health should be 80");

        // Set health to zero
        tank.setHealth(0);
        assertEquals(0, tank.getHealth(), "Health should be 0");

        // Set health to a negative value
        tank.setHealth(-10);
        assertEquals(-10, tank.getHealth(), "Health should allow negative values");
    }

    @Test
    void testSetAndGetColor() {
        Tank tank = new Tank(2.0, 1.0, 100, "red",100);

        // Change color
        tank.setColor("blue");
        assertEquals("blue", tank.getColor(), "Color should be blue");

        // Set to an empty color
        tank.setColor("");
        assertEquals("", tank.getColor(), "Color should be an empty string");
    }

    @Test
    void testHit() {
        Tank tank = new Tank(2.0, 1.0, 100, "red", 100);

        // Take damage
        tank.hit(30);
        assertEquals(70, tank.getHealth(), "Health should decrease by 30");

        // Take more damage
        tank.hit(50);
        assertEquals(20, tank.getHealth(), "Health should decrease by 50");

        // Take excessive damage
        tank.hit(25);
        assertEquals(0, tank.getHealth(), "Health should not go below 0");
    }

    @Test
    void testToString() {
        Tank tank = new Tank(2.0, 1.0, 100, "red",100);

        String expected = "Tank [width=2.0, height=1.0, health=100, color=red , X Coordinate=100]";
        assertEquals(expected, tank.toString(), "toString should match expected format");

        // Update values and recheck
        tank.setHealth(80);
        tank.setColor("blue");
        expected = "Tank [width=2.0, height=1.0, health=80, color=blue]";
        assertEquals(expected, tank.toString(), "toString should match updated format");
    }
}
