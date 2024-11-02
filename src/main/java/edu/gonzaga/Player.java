package edu.gonzaga;

public class Player {
    Location loc;
    String character;
    Card hand;
    boolean winner = false;
    int space;
    public String getCharacter(){
        return character;
    }
    public Card getCard(){
        return hand;
    }
    public void setWinner(boolean win){
        winner = win;
    }
    public boolean getWinner(){
        return winner;
    }
    boolean checkWinner(){
        if(winner==true){
            return true;
        }
        else{
            return false;
        }
    }
    public void playTurn(Board board){
        drawCardPlayer(board);
        playerMoveSpaces(board);
    }
    public void drawCardPlayer(Board board){
        hand = board.drawCard();
    }
    void playerMoveSpaces(Board board){
        space = board.findNextSpace(hand, space);
        if(space==board.spaces.size()-1){
            winner = true;
        }
    }
    public Player(){

    }
}
