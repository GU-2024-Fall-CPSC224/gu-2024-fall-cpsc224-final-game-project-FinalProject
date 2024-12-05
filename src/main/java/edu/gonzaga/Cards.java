package edu.gonzaga;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Cards {
    ArrayList<SingleCard> cards; // List of Card objects
    ArrayList<ArrayList<Object>> deckOfCards; // Deck for game logic

    // for scaling
    private GUI gui;

    public Cards(String imagesPath, GUI gui) {
        cards = new ArrayList<>(52); // Initialize the cards list
        deckOfCards = new ArrayList<>();
        loadImages(imagesPath, gui);
        initializeDeck();
    }

    void loadImages(String imagesPath, GUI gui) {
        this.gui = gui;

        BufferedImage currPicture;
        for (int i = 1; i <= 13; i++) { // Card values
            for (char suit : new char[]{'S', 'C', 'D', 'H'}) { // Suits
                try {
                    String filename = imagesPath + "/" + i + suit + ".jpg";
                    System.out.println("Loading image: " + filename);
                    currPicture = ImageIO.read(new File(filename));

                    int ScaledWidth = gui.getScaledWidth(170);
                    int ScaledHeight = gui.getScaledHeight(225);

                    Image dimg = currPicture.getScaledInstance(ScaledWidth, ScaledHeight, Image.SCALE_SMOOTH);
                    ImageIcon scaledImage = new ImageIcon(dimg);
                    cards.add(new SingleCard(getSuitName(suit), i, scaledImage)); // Add Card object to the cards list
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void initializeDeck() {
        String[] suits = {"Spades", "Clubs", "Diamonds", "Hearts"};
        for (String suit : suits) {
            for (int value = 1; value <= 13; value++) {
                ArrayList<Object> card = new ArrayList<>();
                card.add(suit);  // Add suit
                card.add(value); // Add value
                deckOfCards.add(card);
            }
        }
    }

    // Draw a random card from the deck
    public ArrayList<Object> drawTheCard() {
        Random rand = new Random();
        if (deckOfCards.isEmpty()) {
            System.out.println("No more cards in the deck!");
            return null;
        }
        int index = rand.nextInt(deckOfCards.size());
        return deckOfCards.remove(index);
    }

    // Get the card image based on its suit and value
    public ImageIcon getCardImage(ArrayList<Object> card) {
        String suit = (String) card.get(0);
        int value = (int) card.get(1);
        for (SingleCard c : cards) { // Loop through Card objects
            if (c.suit.equals(suit) && c.value == value) {
                return c.image; // Return the corresponding image
            }
        }
        System.out.println("Card not found: " + suit + " " + value);
        return null; // Card not found
    }

    // Map suit abbreviation to suit name
    private String getSuitName(char suit) {
        if (suit == 'S') {
            return "Spades";
        } else if (suit == 'C') {
            return "Clubs";
        } else if (suit == 'D') {
            return "Diamonds";
        } else if (suit == 'H') {
            return "Hearts";
        } else {
            return "Unknown";
        }
    }
}
