package edu.gonzaga;
import java.util.ArrayList;
import java.util.Random;
//test
public class Board {
    ArrayList <Card> deck;
    ArrayList <String> spaces;
    ArrayList <Player> players;
    ArrayList <Card> potentialCards;
    ArrayList <String> playerNames;

    public Board(ArrayList <Player> playerList)
    {   
        potentialCards = new ArrayList<Card>();
        initializePotentialCards();
        deck = new ArrayList<Card>();
        makeNewDeck(deck);
        spaces = new ArrayList<String>();
        initiateSpaces();
        players = playerList;
        playerNames = new ArrayList<String>();
        initializePlayerNames();
    }

    public ArrayList <String> getPlayerNames()
    {
        return playerNames;
    }

    public void updatePlayer(ArrayList <Player> newPlayers)
    {
        players = newPlayers;
    }
    
    public void initializePlayerNames()
    {
        playerNames.add("Mally Mallo");
        playerNames.add("Twirly Girl");
        playerNames.add("Cutie Cone");
        playerNames.add("Giggly Gumdrop");
    }
    
    public void makeNewDeck(ArrayList <Card> deck)
    {
        Random rand = new Random();
        int index = 0;
        for(int i = 0; i < 100; i++)
        {
            index = rand.nextInt(potentialCards.size());
            deck.add(potentialCards.get(index));
            if(potentialCards.get(index).getIsSpecialCard() == true)
            {
                potentialCards.remove(index);
            }
            
        }
        
    }

    public void initiateSpaces()
    {
        Random rand = new Random();
        ArrayList <String> colors = new ArrayList<String>();
        colors.add("blue");
        colors.add("green");
        colors.add("red");
        colors.add("yellow");
        colors.add("purple");
        colors.add("pink");

        for(int i = 0; i < 60; i++)
        {
            spaces.add(colors.get(rand.nextInt(colors.size())));
        }
    }

    public void initializePotentialCards()
    {
        potentialCards.add(new Card("blue", "", false));
        potentialCards.add(new Card("blue", "blue", false));

        potentialCards.add(new Card("green", "", false));
        potentialCards.add(new Card("green", "green", false));

        potentialCards.add(new Card("red", "", false));
        potentialCards.add(new Card("red", "red", false));

        potentialCards.add(new Card("yellow", "", false));
        potentialCards.add(new Card("yellow", "yellow", false));

        potentialCards.add(new Card("purple", "", false));
        potentialCards.add(new Card("purple", "purple", false));

        potentialCards.add(new Card("pink", "", false));
        potentialCards.add(new Card("pink", "pink", false));

        potentialCards.add(new Card("gingerbread", "", true));
        potentialCards.add(new Card("peanut", "", true));
        potentialCards.add(new Card("gumdrop", "", true));
        potentialCards.add(new Card("lollipop", "", true));
        potentialCards.add(new Card("candyCane", "", true));
        potentialCards.add(new Card("iceCream", "", true));
    
    }

    public Card drawCard()
    {
        Random rand = new Random();
        return deck.get(rand.nextInt(deck.size()));
    }

    public int findNextSpace(Card hand, int start)
    {
        int index = 0;
        if(hand.getIsSpecialCard())
        {
            for(int i = 0; i < spaces.size(); i++)
            {
                if(hand.getSquare1().equals(spaces.get(i)))
                {
                    index = i;
                    break;
                }
            }
        }
        else
        {
            for(int i = start; i < spaces.size(); i++)
            {
                if(hand.getSquare1().equals(spaces.get(i)))
                {
                    if(hand.getSquare2().equals(""))
                    {
                        for(int j = i; j < spaces.size(); j++)
                        {
                            if(hand.getSquare2().equals(spaces.get(j)))
                            {
                                index = j;
                                break;
                            }   
                        }
                    }
                    index = i;
                    break;
                }
                if(i == spaces.size() - 1)
                {
                    index = i;
                }
            }
        }
        return index;
    }
}