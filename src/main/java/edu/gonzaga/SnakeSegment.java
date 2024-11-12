package edu.gonzaga;

import java.util.Map;

public class SnakeSegment {
    private int x;
    private int y;
    private int life; 
    private int rotation; //u+d, l+r, u+l, u+r, d+l, d+r

    public SnakeSegment(int x,int y, int life, int rotation) {
        this.x = x;
        this.y = y;
        this.life = life;
        this.rotation = rotation;
    }

    public void subtractLife(int num) {
        life -= num;
    }

    public int getLife() {
        return life;
    }

    public String toString() {
        return "(" + x + "," + y + " " + getRotAsStr() + " " + life + ")";
    }

    public int getRotation() {
        return rotation;
    }

    public String getRotAsStr() {
        return Map.of(217,"ud",222,"lr",225,"ul",231,"ur",208,"dl",214,"dr").get(rotation);
    }
}
