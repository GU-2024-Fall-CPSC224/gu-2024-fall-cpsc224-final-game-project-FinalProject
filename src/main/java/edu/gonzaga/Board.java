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
        candyPath.add(new Point(173,644));
        candyPath.add(new Point(206,644));
        candyPath.add(new Point(241,630));
        candyPath.add(new Point(256,608));
        candyPath.add(new Point(273,575));
        candyPath.add(new Point(297,552));
        candyPath.add(new Point(329,541));
        candyPath.add(new Point(362,535));
        candyPath.add(new Point(399,544));
        candyPath.add(new Point(431,557));
        candyPath.add(new Point(460,574));
        candyPath.add(new Point(482,595));
        candyPath.add(new Point(509,624));
        candyPath.add(new Point(538,642));
        candyPath.add(new Point(568,652));
        candyPath.add(new Point(603,656));
        candyPath.add(new Point(644,654));
        candyPath.add(new Point(676,649));
        candyPath.add(new Point(704,635));
        candyPath.add(new Point(716,603));
        candyPath.add(new Point(700,576));
        candyPath.add(new Point(671,564));
        candyPath.add(new Point(631,560));
        candyPath.add(new Point(600,560));
        candyPath.add(new Point(558,560));
        candyPath.add(new Point(528,558));
        candyPath.add(new Point(491,545));
        candyPath.add(new Point(473,520));
        candyPath.add(new Point(467,488));
        candyPath.add(new Point(493,464));
        candyPath.add(new Point(528,461));
        candyPath.add(new Point(565,474));
        candyPath.add(new Point(595,493));
        candyPath.add(new Point(629,507));
        candyPath.add(new Point(654,519));
        candyPath.add(new Point(688,526));
        candyPath.add(new Point(728,528));
        candyPath.add(new Point(760,507));
        candyPath.add(new Point(776,480));
        candyPath.add(new Point(776,445));
        candyPath.add(new Point(764,414));
        candyPath.add(new Point(738,393));
        candyPath.add(new Point(702,398));
        candyPath.add(new Point(668,410));
        candyPath.add(new Point(632,423));
        candyPath.add(new Point(597,416));
        candyPath.add(new Point(566,402));
        candyPath.add(new Point(535,382));
        candyPath.add(new Point(504,363));
        candyPath.add(new Point(469,350));
        candyPath.add(new Point(437,347));
        candyPath.add(new Point(396,349));
        candyPath.add(new Point(366,361));
        candyPath.add(new Point(340,382));
        candyPath.add(new Point(310,406));
        candyPath.add(new Point(290,433));
        candyPath.add(new Point(269,464));
        candyPath.add(new Point(246,488));
        candyPath.add(new Point(218,506));
        candyPath.add(new Point(187,515));
        candyPath.add(new Point(155,517));
        candyPath.add(new Point(122,511));
        candyPath.add(new Point(89,500));
        candyPath.add(new Point(62,479));
        candyPath.add(new Point(46,450));
        candyPath.add(new Point(46,419));
        candyPath.add(new Point(62,390));
        candyPath.add(new Point(94,377));
        candyPath.add(new Point(124,382));
        candyPath.add(new Point(159,389));
        candyPath.add(new Point(188,399));
        candyPath.add(new Point(226,402));
        candyPath.add(new Point(255,391));
        candyPath.add(new Point(265,357));
        candyPath.add(new Point(239,329));
        candyPath.add(new Point(211,315));
        candyPath.add(new Point(186,284));
        candyPath.add(new Point(184,256));
        candyPath.add(new Point(210,231));
        candyPath.add(new Point(243,221));
        candyPath.add(new Point(276,223));
        candyPath.add(new Point(312,230));
        candyPath.add(new Point(347,247));
        candyPath.add(new Point(380,264));
        candyPath.add(new Point(409,281));
        candyPath.add(new Point(444,296));
        candyPath.add(new Point(477,310));
        candyPath.add(new Point(510,320));
        candyPath.add(new Point(544,327));
        candyPath.add(new Point(585,336));
        candyPath.add(new Point(619,341));
        candyPath.add(new Point(654,343));
        candyPath.add(new Point(688,340));
        candyPath.add(new Point(724,331));
        candyPath.add(new Point(756,318));
        candyPath.add(new Point(780,290));
        candyPath.add(new Point(787,259));
        candyPath.add(new Point(779,225));
        candyPath.add(new Point(749,202));
        candyPath.add(new Point(719,181));
        candyPath.add(new Point(706,149));
        candyPath.add(new Point(692,117));
        candyPath.add(new Point(657,103));
        candyPath.add(new Point(625,114));
        candyPath.add(new Point(599,136));
        candyPath.add(new Point(582,171));
        candyPath.add(new Point(567,204));
        candyPath.add(new Point(547,233));
        candyPath.add(new Point(515,252));
        candyPath.add(new Point(482,254));
        candyPath.add(new Point(449,246));
        candyPath.add(new Point(415,227));
        candyPath.add(new Point(386,209));
        candyPath.add(new Point(355,189));
        candyPath.add(new Point(322,179));
        candyPath.add(new Point(289,176));
        candyPath.add(new Point(254,176));
        candyPath.add(new Point(215,183));
        candyPath.add(new Point(184,187));
        candyPath.add(new Point(150,190));
        candyPath.add(new Point(117,181));
        candyPath.add(new Point(89,161));
        candyPath.add(new Point(73,135));
        candyPath.add(new Point(69,104));
        candyPath.add(new Point(79,72));
        candyPath.add(new Point(106,46));
        candyPath.add(new Point(138,34));
        candyPath.add(new Point(171,33));
        candyPath.add(new Point(205,42));
        candyPath.add(new Point(231,56));
        candyPath.add(new Point(261,75));
        candyPath.add(new Point(289,94));
        candyPath.add(new Point(318,110));
        candyPath.add(new Point(354,125));
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