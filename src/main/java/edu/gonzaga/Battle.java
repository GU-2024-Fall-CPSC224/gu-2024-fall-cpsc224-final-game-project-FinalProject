package edu.gonzaga;

public class Battle extends Ship{
    private final int length = 4; 
    private shipType shipId = shipType.BATTLE;

    public Battle(int x, int y, boolean isVertical) {
        super(x, y, isVertical);
    }

    //use ability: multishot (maybe shoots in an 'X' pattern?)

    @Override
    public int getLength() {
        return length;
    }
    @Override
    public shipType getType() {
        return shipId;
    }
}
