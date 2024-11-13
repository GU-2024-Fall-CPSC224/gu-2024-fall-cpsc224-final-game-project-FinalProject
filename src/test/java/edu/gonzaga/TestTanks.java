package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestTanks {
    @Test
    void testTankColor() {
        Tank t = new Tank();
        String expected = "Red";
        Assertions.assertEquals(expected, t.getColor());
    }

    @Test
    void testMyTank() {
        Tank t = new Tank();
        t.setColor("Blue");
        String expected = "Blue";
        assertEquals(expected, t.getColor());
    }
}
