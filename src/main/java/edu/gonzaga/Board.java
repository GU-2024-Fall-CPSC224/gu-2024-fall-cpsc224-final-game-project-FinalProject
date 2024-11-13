package edu.gonzaga;

import java.util.ArrayList;

public class Board {
    private int width;
    private int height;
    private Snake snake;
    private ArrayList<Obstacle> obstacles;
    private Food food;

    public String detectCollision() {
        if (snake.getX() == food.getX() && snake.getY() == food.getY()) {
            return "food";
        }
        if (snake.getX() < 0 || snake.getY() < 0 || snake.getX() > width || snake.getY() > height) {
            return "edge";
        }
        for (Obstacle o : obstacles) {
            if (snake.getX() == o.getX() && snake.getY() == o.getY()) {
                return "obstacle";
            }
        }
        return "";
    }

    //called on time interval
    //maybe move to Game?
    public void tick() {
        snake.move();
        switch (detectCollision()) {
            case "food":
                //score++
                snake.grow();
                food.applyAffect(snake);
                food = new Food(0, 0); //todo: randomize pos and type
                break;
            case "edge":
                //die
                break;
            case "obstacle":
                //die
                break;
        }
        //draw graphics
    }
}
