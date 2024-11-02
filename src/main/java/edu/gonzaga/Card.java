package edu.gonzaga;

public class Card {
    String square1;
    String square2;
    boolean specialCard;
    public Card(String square1, String square2, boolean specialCard){
        this.square1 = square1;
        this.square2 = square2;
        this.specialCard = specialCard;
        
    }
   public String getSquare1(){
    return square1;
   }
   public String getSquare2(){
    return square2;
   }
   public boolean getIsSpeicalCard(){
    return specialCard;
   }
   public void setSquare1(String square){
    square1 = square;
   }
   public void setSquare2(String square){
    square2 = square;
   }
}
