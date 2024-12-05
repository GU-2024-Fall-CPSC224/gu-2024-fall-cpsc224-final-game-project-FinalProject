package edu.gonzaga;

import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import org.dyn4j.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Geometry and Shape creation
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//Dyn4j Physics imports
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.joint.FrictionJoint;
import org.dyn4j.dynamics.joint.RevoluteJoint;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Interval;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Ray;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.DetectFilter;
import org.dyn4j.world.World;
import org.dyn4j.world.result.RaycastResult;
import org.dyn4j.collision.AxisAlignedBounds;
import org.dyn4j.collision.Bounds;
import org.dyn4j.dynamics.contact.ContactConstraint;
import org.dyn4j.dynamics.contact.SolvedContact;
import org.dyn4j.dynamics.joint.DistanceJoint;
import org.dyn4j.dynamics.joint.Joint;
import org.dyn4j.dynamics.joint.PinJoint;
import org.dyn4j.geometry.AABB;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;
import org.dyn4j.world.WorldCollisionData;



public class ToPlay {
    String name;
    String color;
    public ArrayList <Tank> array_Tank = new ArrayList<>(); 

    public ToPlay() {
        this.name = "Unidentified User";
        this.color = "Red";
    }

    private JFrame frame = new JFrame(); // the frame that opens when the program is run
    // panels in the border layout:
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel centerNorthPanel = new JPanel();
    private JPanel centerSouthPanel = new JPanel();
    private JPanel instructionsPanel = new JPanel();
    // the label that goes at the bottom:
    private JLabel title = new JLabel("TANKS");
    private JLabel names = new JLabel("By Christain Carrington, Abby Fewel, and Ayden Humphries");
    // buttons that go in the middle, in panel 5:
    private JButton start = new JButton("Start");
    private JButton howToPlay = new JButton("How to Play");
    JButton red = new JButton();
    JButton orange = new JButton();
    JButton yellow = new JButton();
    JButton green = new JButton();
    JButton blue = new JButton();
    JButton pink = new JButton();
    // image that also goes in the middle, in panel 4:
    private JLabel tankPicture = new JLabel();
    private JLabel howToPlayFrameIcon = new JLabel();
    private JFrame instructionsFrame = new JFrame();

    JTextField player1NameTextField = new JTextField();
    JTextField player2NameTextField = new JTextField();
    String player1name = "Unidentifiable Player";
    String player2name = "Unidentifiable Player";
    String player1Color = " ";
    String player2Color = " ";

    /**
     * This method formats the start screen that has an image of a tank, a title,
     * two buttons, and author names.
     */
    public void formattingIntroScreen() {
        setUpButtonListeners(); // to make Start and How to Play buttons listen
        // formats the frame:
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // defaults settings
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        northPanel.setBackground(Color.GREEN); // colors
        centerPanel.setBackground(Color.lightGray);
        southPanel.setBackground(Color.white);
        northPanel.setPreferredSize(new Dimension(100, 70)); // dimensions of panels
        centerPanel.setPreferredSize(new Dimension(100, 100));
        southPanel.setPreferredSize(new Dimension(100, 50));
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);
        title.setFont(new Font("Algerian", Font.BOLD, 50));
        northPanel.add(title);
        southPanel.add(names);
        centerPanel.add(centerNorthPanel, BorderLayout.NORTH); // putting border layout in center panel
        centerPanel.add(centerSouthPanel, BorderLayout.SOUTH);
        ImageIcon tankStartScreenIcon = new ImageIcon("tank_intro_screen.jpg");
        tankPicture.setIcon(tankStartScreenIcon);
        centerNorthPanel.add(tankPicture);
        centerSouthPanel.add(start);
        centerSouthPanel.add(howToPlay);
    }

    public void formattingStartScreenPlayerText(JPanel startingPanelCenter) {
        // frame settings
        JFrame startingFrame = new JFrame();
        startingFrame.setLayout(new BorderLayout());
        startingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // making panels
        JPanel startingPanelNorth = new JPanel();
        JPanel startingPanelSouth = new JPanel();
        JPanel playerPanel = new JPanel();
        // making labels and text fields
        JLabel startLabel = new JLabel("Player Settings");
        JLabel player1NameLabel = new JLabel("Player #1 Name: ");
        JLabel enterNameInstructionsLabel = new JLabel("Enter names and press 'Enter'.");
        JLabel player2NameLabel = new JLabel("Player #2 Name: ");
        player1NameTextField.setText("Player 1       ");
        player2NameTextField.setText("Player 2       ");
        startLabel.setFont(new Font("Algerian", Font.BOLD, 50));
        startingPanelNorth.add(startLabel); // add the label to the north panel
        startingFrame.add(startingPanelNorth, BorderLayout.NORTH);
        startingFrame.add(startingPanelSouth, BorderLayout.SOUTH);
        JButton continueButton = new JButton("Continue");
        startingPanelSouth.add(continueButton);
        // set up the border layout in the center panel
        startingPanelCenter.setLayout(new BorderLayout());
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        enterNameInstructionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel nameInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nameInputPanel.add(player1NameLabel);
        nameInputPanel.add(player1NameTextField);
        nameInputPanel.add(player2NameLabel);
        nameInputPanel.add(player2NameTextField);
        playerPanel.add(enterNameInstructionsLabel);
        playerPanel.add(nameInputPanel);
        startingPanelCenter.add(playerPanel, BorderLayout.NORTH);
        // now set up the panel!
        startingFrame.setSize(500, 280);
        startingFrame.add(startingPanelCenter, BorderLayout.CENTER);
        startingFrame.add(startingPanelSouth, BorderLayout.SOUTH);
        startingFrame.setVisible(true);
    }

    public void formattingStartScreenPlayerColor(JPanel startingPanelCenter) {
        red.setBackground(Color.red);
        orange.setBackground(Color.orange);
        yellow.setBackground(Color.yellow);
        green.setBackground(Color.green);
        blue.setBackground(Color.blue);
        pink.setBackground(Color.pink);
        JLabel player1Color = new JLabel("Player #1 Color: ");
        JLabel player2Color = new JLabel("Player #2 Color: ");
        JPanel player1ColorPanel = new JPanel();
        JPanel player2ColorPanel = new JPanel();
        player1ColorPanel.add(player1Color);
        player1ColorPanel.add(red);
        player1ColorPanel.add(yellow);
        player1ColorPanel.add(blue);
        player2ColorPanel.add(player2Color);
        player2ColorPanel.add(orange);
        player2ColorPanel.add(green);
        player2ColorPanel.add(pink);
        startingPanelCenter.add(player1ColorPanel, BorderLayout.WEST);
        startingPanelCenter.add(player2ColorPanel, BorderLayout.EAST);
    }

    public String getPlayer1Name() { // called from MainGame
        return player1name;
    }

    public void setPlayer1Name(String player1name) { // called from MainGame
        this.player1name = player1name;
    }

    public String getPlayer2Name() { // called from MainGame
        return player2name;
    }

    public void setPlayer2Name(String player2name) { // called from MainGame
        this.player2name = player2name;
    }

    public String getPlayer1Color() { // called from MainGame
        System.out.println(player1Color);
        return player1Color;
    }

    public void setPlayer1Color(String player1Color) { // called from MainGame
        this.player1Color = player1Color;
        System.out.println(player1Color);
    }

    public String getPlayer2Color() { // called from MainGame
        System.out.println(player2Color);
        return player2Color;
    }

    public void setPlayer2Color(String player2Color) { // called from MainGame
        this.player2Color = player2Color;
        System.out.println(player2Color);
    }

    /**
     * This method starts listening to the buttons to notice when they are clicked
     * and take proper action / call methods.
     */
    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == howToPlay) {
                    ImageIcon howToPlayIcon = new ImageIcon("how_to_play.png"); // new icon with instructions
                    howToPlayFrameIcon.setIcon(howToPlayIcon); // set the icon for this label
                    instructionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    instructionsFrame.setSize(820, 800);
                    instructionsFrame.setLayout(new BorderLayout()); // Use a simple layout
                    instructionsPanel.setLayout(new BorderLayout()); // Let the image fill the panel
                    instructionsPanel.add(howToPlayFrameIcon, BorderLayout.CENTER); // Add the icon to the center
                    instructionsFrame.add(instructionsPanel, BorderLayout.CENTER); // Add the panel to the frame
                    instructionsFrame.setVisible(true);
                } else if (actionEvent.getSource() == start) {
                    JPanel startingPanelCenter = new JPanel();
                    formattingStartScreenPlayerText(startingPanelCenter);
                    formattingStartScreenPlayerColor(startingPanelCenter);
                }
            }
        };
        ActionListener textListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == player1NameTextField) {
                    setPlayer1Name(player1NameTextField.getText());
                    System.out.println(getPlayer1Name());
                } else if (actionEvent.getSource() == player2NameTextField) {
                    setPlayer2Name(player2NameTextField.getText());
                    System.out.println(getPlayer2Name());
                }
            }
        };
        ActionListener colorButtonsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == red) {
                    setPlayer1Color("0xf33d3d, red");
                } else if (actionEvent.getSource() == orange) {
                    setPlayer2Color("0xef8a3d, orange");
                } else if (actionEvent.getSource() == yellow) {
                    setPlayer1Color("0xefd83d, yellow");
                } else if (actionEvent.getSource() == green) {
                    setPlayer2Color("0x2bcc2e, green");
                } else if (actionEvent.getSource() == blue) {
                    setPlayer1Color("0x3b89de, blue");
                } else if (actionEvent.getSource() == pink) {
                    setPlayer2Color("0xed7ef6, pink");
                }
                System.out.println(getPlayer1Name() + "'s color is now set to " +
                        getPlayer1Color());
                System.out.println(getPlayer2Name() + "'s color is now set to " +
                        getPlayer2Color());
                System.out.println("Done with the color action listener method");
            }
        };
        start.addActionListener(buttonListener);
        howToPlay.addActionListener(buttonListener);
        player1NameTextField.addActionListener(textListener);
        player2NameTextField.addActionListener(textListener);
        red.addActionListener(colorButtonsListener);
        orange.addActionListener(colorButtonsListener);
        yellow.addActionListener(colorButtonsListener);
        green.addActionListener(colorButtonsListener);
        blue.addActionListener(colorButtonsListener);
        pink.addActionListener(colorButtonsListener);
    }

    // when adding action listener for continue, set the names again in case users
    // do not press 'Enter'
    public void createWorld(){
        World world = new World(); 
        world.setGravity(Vector2.create(0.0, -9.8));
        array_Tank.add(new Tank(10,10, 100, "Red", 100));
        array_Tank.add(new Tank(10, 10, 100, "Green", 500)); 
        
    }

}
