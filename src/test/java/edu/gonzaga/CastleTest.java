package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CastleTest {
    @Test
    void equalsTest() {
        int x = 1;
        int y = 1;
        Assertions.assertEquals(x, y);
    }

    @Test
    void testHeight() {
        Castle castle = new Castle();
        int heightTest = 100;
        castle.setHeight(heightTest);
        assertEquals(castle.getHeight(), heightTest);
    }

}
