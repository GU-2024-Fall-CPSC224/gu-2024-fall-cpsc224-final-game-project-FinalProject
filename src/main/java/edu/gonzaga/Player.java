package edu.gonzaga;

public class Player {
    Location loc;
    String character;
    Card hand;
    boolean winner = false;
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
    }
    public void drawCardPlayer(Board board){
        hand = board.drawCard();
    }
    void playerMove(){
        
    }
    public Player(){

    }
}
