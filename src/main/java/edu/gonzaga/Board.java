package edu.gonzaga;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//test
public class Board extends JPanel {
    ArrayList <Card> deck;
    ArrayList <String> spaces;
    ArrayList <Player> players;
    ArrayList <Card> potentialCards;
    ArrayList <String> playerNames;
    List<Point> candyPath = new ArrayList<>();

    public Board() {
        potentialCards = new ArrayList<Card>();
        initializePotentialCards();
        deck = new ArrayList<Card>();
        makeNewDeck(deck);
        spaces = new ArrayList<String>();
        initiateSpaces();
        candyPath = candyPath();
        players = new ArrayList<Player>();
        playerNames = new ArrayList<String>();
        initializePlayerNames();

        repaint();
    }

    public ArrayList <String> getPlayerNames()
    {
        return playerNames;
    }

    public void addPlayers(ArrayList <Player> newPlayers)
    {
        for(int i = 0; i < newPlayers.size(); i++)
        {
            players.add(newPlayers.get(i));
        }
    }
    
    public void initializePlayerNames()
    {
        playerNames.add("Mally Mallo");
        playerNames.add("Twirly Girl");
        playerNames.add("Cutie Cone");
        playerNames.add("Giggly Gumdrop");
    }

    public void removePlayerName(String name)
    {
        playerNames.remove(name);
    }

    public void removePlayerNameIndex(int index)
    {
        playerNames.remove(index);
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
        colors.add("orange");

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

        potentialCards.add(new Card("orange", "", false));
        potentialCards.add(new Card("orange", "orange", false));

        potentialCards.add(new Card("gingerbread", "", true));
        potentialCards.add(new Card("peanut", "", true));
        potentialCards.add(new Card("gumdrop", "", true));
        potentialCards.add(new Card("lollipop", "", true));
        potentialCards.add(new Card("candyCane", "", true));
        potentialCards.add(new Card("iceCream", "", true));
    
    }

    public List<Point> candyPath(){
        candyPath.add(new Point(190, 695));
        candyPath.add(new Point(210, 695));
        candyPath.add(new Point(230, 690));
        candyPath.add(new Point(240, 680));
        candyPath.add(new Point(250, 660));
        candyPath.add(new Point(280, 650));
        candyPath.add(new Point(300, 640));
        candyPath.add(new Point(320, 640));
        candyPath.add(new Point(330, 640));
        candyPath.add(new Point(340, 650));
        candyPath.add(new Point(360, 650));
        candyPath.add(new Point(400, 675));
        candyPath.add(new Point(410, 690));
        candyPath.add(new Point(425, 690));
        candyPath.add(new Point(450, 695));
        candyPath.add(new Point(460, 695));
        candyPath.add(new Point(520, 50));
        candyPath.add(new Point(620, 795));
        candyPath.add(new Point(640, 695));
        candyPath.add(new Point(660, 690));
        candyPath.add(new Point(670, 630));
        candyPath.add(new Point(650, 600));
        candyPath.add(new Point(640, 600));
        candyPath.add(new Point(610, 600));
        candyPath.add(new Point(600, 590));
        candyPath.add(new Point(590, 590));
        candyPath.add(new Point(570, 570));
        candyPath.add(new Point(550, 560));
        candyPath.add(new Point(490, 530));
        candyPath.add(new Point(490, 500));
        candyPath.add(new Point(540, 480));
        candyPath.add(new Point(580, 490));
        candyPath.add(new Point(600, 500));
        candyPath.add(new Point(620, 520));
        candyPath.add(new Point(710, 500));
        candyPath.add(new Point(780, 500));
        candyPath.add(new Point(800, 490));
        candyPath.add(new Point(800, 470));
        candyPath.add(new Point(790, 420));
        candyPath.add(new Point(760, 400));
        candyPath.add(new Point(720, 400));
        candyPath.add(new Point(660, 400));
        candyPath.add(new Point(620, 400));
        candyPath.add(new Point(600, 400));
        candyPath.add(new Point(580, 400));
        candyPath.add(new Point(550, 380));
        candyPath.add(new Point(500, 460));
        candyPath.add(new Point(460, 350));
        candyPath.add(new Point(410, 330));
        candyPath.add(new Point(400, 350));
        candyPath.add(new Point(380, 380));
        candyPath.add(new Point(350, 390));
        candyPath.add(new Point(310, 400));
        candyPath.add(new Point(300, 410));
        candyPath.add(new Point(280, 480));
        candyPath.add(new Point(220, 480));
        candyPath.add(new Point(210, 500));
        candyPath.add(new Point(190, 510));
        candyPath.add(new Point(180, 510));
        candyPath.add(new Point(120, 510));
        candyPath.add(new Point(100, 500));
        candyPath.add(new Point(80, 490));
        candyPath.add(new Point(50, 450));
        candyPath.add(new Point(50, 410));
        candyPath.add(new Point(70, 380));
        candyPath.add(new Point(100, 380));
        candyPath.add(new Point(115, 390));
        candyPath.add(new Point(175, 390));
        candyPath.add(new Point(200, 390));
        candyPath.add(new Point(220, 400));
        candyPath.add(new Point(240, 400));
        candyPath.add(new Point(250, 390));
        candyPath.add(new Point(250, 380));
        candyPath.add(new Point(250, 370));
        //stopped here...
        candyPath.add(new Point(0, 150));
        candyPath.add(new Point(50, 50));
        candyPath.add(new Point(100, 50));
        candyPath.add(new Point(150, 50));
        candyPath.add(new Point(150, 100));
        candyPath.add(new Point(150, 150));
        candyPath.add(new Point(50, 50));
        candyPath.add(new Point(100, 50));
        candyPath.add(new Point(150, 50));
        candyPath.add(new Point(150, 100));
        candyPath.add(new Point(150, 150));
        candyPath.add(new Point(50, 50));
        candyPath.add(new Point(100, 50));
        candyPath.add(new Point(150, 50));
        candyPath.add(new Point(150, 100));
        candyPath.add(new Point(150, 150));
        candyPath.add(new Point(50, 50));
        candyPath.add(new Point(100, 50));
        candyPath.add(new Point(150, 50));
        candyPath.add(new Point(150, 100));
        candyPath.add(new Point(150, 150));
        candyPath.add(new Point(50, 50));
        candyPath.add(new Point(100, 50));
        candyPath.add(new Point(150, 50));
        candyPath.add(new Point(150, 100));
        candyPath.add(new Point(150, 150));
        candyPath.add(new Point(50, 50));
        candyPath.add(new Point(100, 50));
        candyPath.add(new Point(150, 50));
        candyPath.add(new Point(150, 100));
        candyPath.add(new Point(150, 150));
        candyPath.add(new Point(50, 50));
        candyPath.add(new Point(100, 50));
        candyPath.add(new Point(150, 50));
        candyPath.add(new Point(150, 100));
        candyPath.add(new Point(150, 150));
        candyPath.add(new Point(50, 50));
        candyPath.add(new Point(100, 50));
        candyPath.add(new Point(150, 50));
        candyPath.add(new Point(150, 100));
        candyPath.add(new Point(150, 150));
        candyPath.add(new Point(50, 50));
        candyPath.add(new Point(100, 50));
        candyPath.add(new Point(150, 50));
        candyPath.add(new Point(150, 100));
        candyPath.add(new Point(150, 150));
        candyPath.add(new Point(50, 50));
        candyPath.add(new Point(100, 50));
        candyPath.add(new Point(150, 50));
        candyPath.add(new Point(150, 100));
        candyPath.add(new Point(150, 150));
        candyPath.add(new Point(50, 50));
        candyPath.add(new Point(100, 50));
        candyPath.add(new Point(150, 50));
        candyPath.add(new Point(150, 100));
        candyPath.add(new Point(150, 150));
        candyPath.add(new Point(50, 50));
        candyPath.add(new Point(100, 50));
        candyPath.add(new Point(150, 50));
        candyPath.add(new Point(150, 100));
        candyPath.add(new Point(150, 150));
        candyPath.add(new Point(50, 50));
        candyPath.add(new Point(100, 50));
        candyPath.add(new Point(150, 50));
        candyPath.add(new Point(150, 100));
        candyPath.add(new Point(150, 150));
        return candyPath;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Drawing board...");

        g.setColor(Color.BLUE);
        for (Point space : candyPath) {
            g.fillOval(space.x - 5, space.y - 5, 10, 10);
        }
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

    public void initializeSpaces()
    {
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("gingerbread");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("candyCane");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("gumdrop");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("peanut");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("lollipop");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("icecream");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("purple");
        spaces.add("yellow");
        spaces.add("blue");
        spaces.add("orange");
        spaces.add("green");
        spaces.add("red");
        spaces.add("any");
    }


//    JFrame frame = new JFrame("Candy Land Game Board");
//        frame.add(board);
//
//        frame.setSize(1000, 700);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
    
}