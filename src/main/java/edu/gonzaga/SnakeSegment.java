package edu.gonzaga;

public class SnakeSegment {
    private int x;
    private int y;
    private int life; 
    private String rotation; //ud, lr, ul, ur, dl, dr

    public SnakeSegment(int x,int y, int life, String rotation) {
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
        return "(" + x + "," + y + " " + rotation + " " + life + ")";
    }
}
