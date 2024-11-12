package edu.gonzaga;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
class TestTanks {    
    @Test
    void testTankColor() {
        Tank t = new Tank();
        String expected = "Red";
        Assertions.assertEquals(expected, t.getColor());
    }

    @Test
    void testTankHealth(){
        Tank t = new Tank();
        Integer expectedHealth = 100;
        assertEquals(expectedHealth, t.getHealth());
    }

    @Test
    void testTankXCordAndYCord(){
        Tank t = new Tank();
        Integer expectedXCord = 0;
        Integer expectedYCord = 0;
        assertEquals(expectedXCord, t.getXCord());
        assertEquals(expectedYCord, t.getYCord());

    }

    @Test
    void testSetTankHealth(){
        Tank t = new Tank();
        t.setHealth(200); 
        Integer expectedHealth = 200; 
        assertEquals(expectedHealth, t.getHealth());
    }

    @Test
    void testSetXAndYCord(){
        Tank t = new Tank();
        t.setXCord(4);
        t.setYCord(5); 
        Integer expectedY = 5;
        Integer expectedX = 4; 

        assertEquals(expectedX, t.getXCord());
        assertEquals(expectedY, t.getYCord());
    }

    @Test
    void testSetColor(){
        Tank t = new Tank();
        t.setColor("Green"); 
        String expectedColor = "Green"; 

        assertEquals(expectedColor, t.getColor());
    }
}
