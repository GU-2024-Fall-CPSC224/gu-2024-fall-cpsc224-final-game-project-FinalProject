package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TestTank {    
    @Test
    void testTankColor() {
        Tanks t = new Tanks();
        String expected = "Red";
        Assertions.assertEquals(expected, t.getColor());
    }
}
