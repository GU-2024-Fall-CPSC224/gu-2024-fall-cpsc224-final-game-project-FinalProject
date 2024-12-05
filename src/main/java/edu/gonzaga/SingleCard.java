package edu.gonzaga;

import javax.swing.*;

public class SingleCard {
    String suit;
    int value;
    ImageIcon image;

    public SingleCard(String suit, int value, ImageIcon image) {
        this.suit = suit;
        this.value = value;
        this.image = image;
    }
}
