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
    public void playTurn(){}
    public Card drawCard(){}
    void playerMove(){}
    public Player(){

    }
}
