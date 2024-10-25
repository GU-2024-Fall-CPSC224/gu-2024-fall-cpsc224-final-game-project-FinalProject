package edu.gonzaga;

import java.util.ArrayList;

public class Board {
    public static final int BOARD_SIZE = 10;

    public ArrayList<Ship> ships;
    public boolean[][] hits;

    public Board() {
        ships = new ArrayList<>();
        hits = new boolean[BOARD_SIZE][BOARD_SIZE];
    }
}
