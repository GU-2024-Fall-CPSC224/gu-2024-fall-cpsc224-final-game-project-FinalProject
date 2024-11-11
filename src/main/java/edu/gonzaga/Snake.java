package edu.gonzaga;

import java.util.ArrayList;

public class Snake {
    private int length;
    private int headX;
    private int headY;
    private ArrayList<SnakeSegment> segments;
    private char facing; // u, d, l, r

    public Snake() {
        length = 4;
        headX = 0;
        headY = 0;
        facing = 'r';
        segments = new ArrayList<>();
        segments.add(new SnakeSegment(-3, 0, 1, "lr"));
        segments.add(new SnakeSegment(-2, 0, 2, "lr"));
        segments.add(new SnakeSegment(-1, 0, 3, "lr"));
    }

    public void move() {
        segments.add(new SnakeSegment(headX, headY, length, facing == 'u' || facing == 'd' ? "ud" : "lr"));
        switch (facing) {
            case 'u':
                headY--;
                break;
            case 'd':
                headY++;
                break;
            case 'l':
                headX--;
                break;
            case 'r':
                headY++;
                break;
        }
        for (SnakeSegment s : segments) {
            s.subtractLife(1);
        }
        if (segments.get(0).getLife() == 0) {
            segments.remove(0);
        }
    }

    public void eat() {
        for (SnakeSegment s : segments) {
            s.subtractLife(-1);
        }
        length++;
    }

    public String toString() {
        String out = "h(" + headX + "," + headY + " " + facing + " " + length + ")\n";
        for (SnakeSegment s : segments) {
            out += s + "\n";
        }
        return out;
    }
}
