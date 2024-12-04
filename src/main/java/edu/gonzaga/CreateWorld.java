package edu.gonzaga;

import org.dyn4j.geometry.*;
import org.dyn4j.dynamics.*;
import org.dyn4j.world.World;
import org.dyn4j.world.DetectFilter;


    public class CreateWorld {
    
    private World world;

    public CreateWorld() {
        // Create the world with gravity
        world = new World();
        world.setGravity(new Vector2(0.0, -9.8));  // Gravity pulling down

        // Create two tanks
        Tank tank1 = new Tank(100, 0, 0, "Red");
        Tank tank2 = new Tank(100, 10, 10, "Blue");

        // Add the tanks to the world
        addTankToWorld(tank1);
        addTankToWorld(tank2);

        // Create and add the castle
        Castle castle = new Castle(5, 2, "Stone");
        addCastleToWorld(castle);

        // Create and add the ground
        Ground ground = new Ground(20, 1);  // Ground is a large rectangle
        addGroundToWorld(ground);
    }

    public void addTankToWorld(Tank tank) {
        // Create the body for the tank (circle for simplicity)
        CircleShape tankShape = new CircleShape(1.0);  // Radius of 1.0
        Body tankBody = new Body();
        tankBody.addFixture(new BodyFixture(tankShape));
        tankBody.setMass(MassType.NORMAL);

        // Set the position of the tank
        tankBody.setPosition(new Vector2(tank.getXCord(), tank.getYCord()));

        // Add the body to the world
        world.addBody(tankBody);
    }

    public void addCastleToWorld(Castle castle) {
        // Create a rectangular shape for the castle
        RectangleShape castleShape = new RectangleShape(3.0, 3.0);  // Width x Height
        Body castleBody = new Body();
        castleBody.addFixture(new BodyFixture(castleShape));
        castleBody.setMass(MassType.NORMAL);

        // Set the position of the castle
        castleBody.setPosition(new Vector2(5.0, 5.0));

        // Add the body to the world
        world.addBody(castleBody);
    }

    public void addGroundToWorld(Ground ground) {
        // Create a large rectangle for the ground
        RectangleShape groundShape = new RectangleShape(20.0, 1.0);  // Wide, flat ground
        Body groundBody = new Body();
        groundBody.addFixture(new BodyFixture(groundShape));
        groundBody.setMass(MassType.INFINITE);  // Ground doesn't move

        // Set the position of the ground
        groundBody.setPosition(new Vector2(10.0, -5.0));  // Below tanks and castle

        // Add the body to the world
        world.addBody(groundBody);
    }

    public void updateWorld() {
        // Simulate the world for a few steps
        for (int i = 0; i < 60; i++) {
            world.update(1.0 / 60.0);  // Update the simulation (60 FPS)
        }
    }

    public static void main(String[] args) {
        CreateWorld simulation = new CreateWorld();
        simulation.updateWorld();
     }
}