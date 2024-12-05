package edu.gonzaga;

import java.awt.Color;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;

public class Ground {
    private Body body; 
    private Color color; 


    public Ground(){
        this.body = new Body(); 
        Rectangle body = new Rectangle(0, 0);
        this.body.setMassType(MassType.INFINITE);
    }

}
