package edu.gonzaga;

import org.dyn4j.geometry.Geometry;

public class Body {
    private Body body = new Body(); 

    public void createRectBody(){
        body.addFixture(Geometry.createRectangle(5, 3));
    }
}
