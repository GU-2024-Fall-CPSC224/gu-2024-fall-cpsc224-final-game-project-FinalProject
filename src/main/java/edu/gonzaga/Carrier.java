package edu.gonzaga;

public class Carrier extends Ship{
    private final int length = 5; 
    private shipType shipId = shipType.CARRIER;

    public Carrier(int x, int y, boolean isVertical) {
        super(x, y, isVertical);
    }

    //use ability: aerial recon

    @Override
    public int getLength() {
        return length;
    }
    @Override
    public shipType getType() {
        return shipId;
    }
   
}
