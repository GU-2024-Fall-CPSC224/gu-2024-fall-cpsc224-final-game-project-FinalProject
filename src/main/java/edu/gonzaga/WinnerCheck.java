package edu.gonzaga;

import java.util.ArrayList;
import java.util.Collections;

public class WinnerCheck {
    private ArrayList<Object> riverCards;
    private int points;
    private ArrayList<Object> cardCheck;
    private int[] valueAmount;



    public WinnerCheck(ArrayList<Object> riverCardsSave) {
        this.riverCards = riverCardsSave;
        this.points = 0;
        this.cardCheck = new ArrayList<>();
        this.valueAmount = new int[13];
    }


    public int pointCheck(ArrayList<ArrayList<Object>> playerHand) {
        cardCheck.clear(); // Clear previous data
        cardCheck.addAll(playerHand); // Add player's hand
        cardCheck.addAll(riverCards); // Add river cards

        for (int i = 0; i < 13; i++) {
            valueAmount[i] = 0;
        }

        ArrayList<String> suits = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();

        for (Object card : cardCheck) {
            String suit = extractSuit(card);
            int value = extractValue(card);
            suits.add(suit);
            values.add(value);

            // Update valueAmount array
            if (value == 1) {
                valueAmount[12]++; // Ace is treated as high (13th position)
            } else {
                valueAmount[value - 2]++; // Map values 2-13 to 0-11
            }
        }

        if (areAllSuitsSame(suits)) {
            if (royalFlush(cardCheck)) {
                points = 1000000000;
            } else if (straightFlush(cardCheck)) {
                points = 100000000;
                for (int i = 0; i < valueAmount.length; i++) {
                    if (valueAmount[i] > 0) {
                        points += (i + 2);
                    }
                }
            } else if (flush(cardCheck)) {
                points = 50000000;
                ArrayList<Integer> flushValues = new ArrayList<>();

                for (int i = 0; i < valueAmount.length; i++) {
                    if (valueAmount[i] > 0) {
                        flushValues.add(i + 2);
                    }
                }

                flushValues.sort(Collections.reverseOrder());

                for (int i = 0; i < flushValues.size(); i++) {
                    points += flushValues.get(i) * 100;
                }
            }
        } else if (fourOfAKind(cardCheck)) {
            points = 70000000;
            for (int i = 0; i < valueAmount.length; i++) {
                if (valueAmount[i] == 4) {
                    points += (i + 2) * 1000;
                }
            }
        } else if (fullHouse(cardCheck)) {
            points = 60000000; // Base weight
            int threeValue = 0, pairValue = 0;
            for (int i = valueAmount.length - 1; i >= 0; i--) {
                if (valueAmount[i] == 3) {
                    threeValue = i + 2;
                } else if (valueAmount[i] == 2) {
                    pairValue = i + 2;
                }
            }
            points += threeValue * 1000 + pairValue * 10; // Weighted score
        } else if (straight(cardCheck)) {
            points = 40000000;
            for (int i = 0; i < valueAmount.length; i++) {
                if (valueAmount[i] > 0) {
                    points += (i + 2) * 100;
                }
            }
        } else if (threeOfAKind(cardCheck)) {
            points = 30000000;
            for (int i = 0; i < valueAmount.length; i++) {
                if (valueAmount[i] == 3) {
                    points = (i + 2) * 1000;
                }
            }
        } else if (twoPair(cardCheck)) {
            points = 20000000; // Base weight
            int pair1 = 0, pair2 = 0, highCard = 0;
            for (int i = valueAmount.length - 1; i >= 0; i--) {
                if (valueAmount[i] == 2) {
                    if (pair1 == 0) {
                        pair1 = i + 2;
                    } else {
                        pair2 = i + 2;
                    }
                } else if (valueAmount[i] == 1) {
                    highCard = i + 2;
                }
            }
            points += pair1 * 1000 + pair2 * 100 + highCard;
        } else if (onePair(cardCheck)) {
            points = 10000000; // Base weight
            int pairValue = 0, highCard = 0;
            for (int i = valueAmount.length - 1; i >= 0; i--) {
                if (valueAmount[i] == 2) {
                    pairValue = i + 2;
                } else if (valueAmount[i] == 1) {
                    highCard = i + 2;
                }
            }
            points += pairValue * 1000 + highCard;
        } else{
            points = 0;
            for (int i = 0; i < valueAmount.length; i++) {
                if (valueAmount[i] == 1) {
                    points = (i + 2) * valueAmount[i];
                }
            }
        }
        return points;
    }

    private boolean areAllSuitsSame(ArrayList<String> suits) {
        if (suits.isEmpty()) {
            return false;
        }

        // Use the first suit as the reference
        String referenceSuit = suits.get(0);
        for (String suit : suits) {
            if (!suit.equals(referenceSuit)) {
                return false; // Found a different suit
            }
        }
        return true; // All suits are the same
    }

    private boolean royalFlush(ArrayList<Object> cardCheck) {
        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<String> suits = new ArrayList<>();

        for (Object card : cardCheck) {
            values.add(extractValue(card));
            suits.add(extractSuit(card));
        }

        if (areAllSuitsSame(suits) && valueAmount[8] == 1 && valueAmount[9] == 1 && valueAmount[10] == 1 && valueAmount[11] == 1 && valueAmount[12] == 1) {
            return true;
        }
        return false;
    }

    private boolean straightFlush(ArrayList<Object> cardCheck){
        ArrayList<String> suits = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();

        for (Object card : cardCheck) {
            suits.add(extractSuit(card));
            int value = extractValue(card);
            if (!values.contains(value)) { // Avoid duplicates
                values.add(value);
            }
        }

        if (!areAllSuitsSame(suits)) {
            return false;
        }else{
            Collections.sort(values);

            // Check for consecutive sequence
            for (int i = 0; i <= values.size() - 5; i++) {
                if (values.get(i) + 1 == values.get(i + 1) &&
                        values.get(i + 1) + 1 == values.get(i + 2) &&
                        values.get(i + 2) + 1 == values.get(i + 3) &&
                        values.get(i + 3) + 1 == values.get(i + 4)) {
                    return true;
                }
            }

            // Special case: Ace as the low card (A-2-3-4-5)
            if (values.contains(14) && values.contains(2) && values.contains(3) && values.contains(4) && values.contains(5)) {
                return true;
            }
        }
        return false;
    }

    private boolean fourOfAKind(ArrayList<Object> cardCheck){
        for(int count: valueAmount){
            if(count == 4){
                return true;
            }
        }
        return false;
    }

    private boolean fullHouse(ArrayList<Object> cardCheck){
        boolean hasThree = false;
        boolean hasTwo = false;

        for (int count : valueAmount) {
            if (count == 3) {
                hasThree = true;
            } else if (count == 2) {
                hasTwo = true;
            }
        }

        return hasThree && hasTwo;
    }

    private boolean flush(ArrayList<Object> cardCheck) {
        ArrayList<String> suits = new ArrayList<>();
        for (Object card : cardCheck) {
            suits.add(extractSuit(card));
        }

        if (areAllSuitsSame(suits)) {
            return true; // Explicitly return true if all suits are the same
        } else {
            return false; // Explicitly return false otherwise
        }
    }

    private boolean straight(ArrayList<Object> cardCheck){
        ArrayList<Integer> values = new ArrayList<>();
        for (Object card : cardCheck) {
            int value = extractValue(card);
            if (!values.contains(value)) { // Avoid duplicates
                values.add(value);
            }
        }

        Collections.sort(values);

        // Check for consecutive sequence
        for (int i = 0; i <= values.size() - 5; i++) {
            if (values.get(i) + 1 == values.get(i + 1) &&
                    values.get(i + 1) + 1 == values.get(i + 2) &&
                    values.get(i + 2) + 1 == values.get(i + 3) &&
                    values.get(i + 3) + 1 == values.get(i + 4)) {
                return true;
            }
        }

        // Special case: Ace as the low card (A-2-3-4-5)
        if (values.contains(14) && values.contains(2) && values.contains(3) && values.contains(4) && values.contains(5)) {
            return true;
        }

        return false;
    }

    private boolean threeOfAKind(ArrayList<Object> cardCheck){
        for (int count : valueAmount) {
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean twoPair(ArrayList<Object> cardCheck){
        int pairs = 0;

        // Count pairs
        for (int count : valueAmount) {
            if (count == 2) {
                pairs++;
            }
        }

        return pairs >= 2;
    }

    private boolean onePair(ArrayList<Object> cardCheck){
        for (int count : valueAmount) {
            if (count == 2) {
                return true;
            }
        }
        return false;
    }

    private String extractSuit(Object card) {
        // Extract suit from the card, which is a list [Suit, Value]
        return (String) ((ArrayList<Object>) card).get(0);
    }

    private int extractValue(Object card) {
        // Extract value from the card, which is a list [Suit, Value]
        return (Integer) ((ArrayList<Object>) card).get(1);
    }
}
