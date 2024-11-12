package edu.gonzaga;

public abstract class Ship {
    private final int x;
    private final int y;

    // Determines if the ship will extend downwards or to the right.
    private final boolean isVertical;

    public Ship(int x, int y, boolean isVertical) {
        this.x = x;
        this.y = y;
        this.isVertical = isVertical;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public abstract int getLength();
}
