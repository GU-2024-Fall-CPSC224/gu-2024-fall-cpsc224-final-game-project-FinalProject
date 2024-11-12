package edu.gonzaga;

import java.util.ArrayList;

public class Snake {
    private int length;
    private int headX;
    private int headY;
    private ArrayList<SnakeSegment> segments;
    private char facing; // u, d, l, r
    private int nextRotation;

    public Snake() {
        length = 4;
        headX = 0;
        headY = 0;
        facing = 'r';
        nextRotation = 'l'+'r';
        segments = new ArrayList<>();
        segments.add(new SnakeSegment(-3, 0, 1,'l'+'r'));
        segments.add(new SnakeSegment(-2, 0, 2, 'l'+'r'));
        segments.add(new SnakeSegment(-1, 0, 3, 'l'+'r'));
    }

    public void move() {
        segments.add(new SnakeSegment(headX, headY, length, nextRotation));
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
                headX++;
                break;
        }
        for (SnakeSegment s : segments) {
            s.subtractLife(1);
        }
        if (segments.get(0).getLife() == 0) {
            segments.remove(0);
        }
        nextRotation = facing == 'u' || facing == 'd' ? 'u'+'d' : 'l'+'r';
    }

    public void grow() {
        for (SnakeSegment s : segments) {
            s.subtractLife(-1);
        }
        length++;
    }

    public String toString() {
        String out = "h(" + headX + "," + headY + " " + (char)facing + " " + length + ")\n";
        for (SnakeSegment s : segments) {
            out += s + "\n";
        }
        return out;
    }

    public void turn(char dir) {
        if (canTurn(dir)) {
            //opposite of prev dir, add new dir, set as next rotation
            nextRotation = (facing ^ (facing % 6 == 0 ? 30 : 17)) + dir;
            facing = dir;
        }
    }

    private boolean canTurn(char dir) {
        if (facing == dir) {
            return false;
        }
        if (facing + dir == 'u' + 'd' || facing + dir == 'l' + 'r') {
            return false;
        }
        return true; 
    }

    public int getX() {
        return headX;
    }

    public int getY() {
        return headY;
    }

    public char getFacing() {
        return facing;
    }

    public ArrayList<SnakeSegment> getSegments() {
        return segments;
    }

    public int getLength() {
        return length;
    }
}