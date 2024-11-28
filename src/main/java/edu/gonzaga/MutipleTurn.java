package edu.gonzaga;

import javax.swing.*;
import java.util.ArrayList;

public class MutipleTurn {
    private Cards card;
    private int playTurn;
    private ArrayList<JLabel> riverCards;
    private ArrayList<Player> players;
    private SingleRound singleRound;
    private int flippedCardsCount;



    public MutipleTurn(Cards cards, ArrayList<Player> players, SingleRound singleRound, ArrayList<JLabel> riverCards){
        this.card = cards;
        this.players = players;
        this.singleRound = singleRound;
        this.riverCards = riverCards;
        this.flippedCardsCount = 0; // 初始化翻开的卡牌数量
        this.playTurn = 0;
    }

    // next turn
    public void nextTurn() {
        // check if everyone already put the chips in the pot
        if(!singleRound.checkRaiseChips()){
            return;
        }

        switch(playTurn) {
            case 0:
                secondTurn(3);
                break;
            case 1:
                flipNextRiverCard();
                break;
            case 2:
                flipNextRiverCard();
                break;
            case 3:
                break;
            default:
                System.out.println("No more turns.");
        }

        playTurn++;
    }

    // second turn, player will flip 3 cards
    private void secondTurn(int count) {
        for (int i = 0; i < count && flippedCardsCount < riverCards.size(); i++) {
            flipNextRiverCard();
        }
    }

    //flip next card
    public void flipNextRiverCard() {
        if(flippedCardsCount < riverCards.size()) {
            ArrayList<Object> drawCard = card.drawTheCard();
            if(drawCard != null){
                ImageIcon cardImage = card.getCardImage(drawCard);
                riverCards.get(flippedCardsCount).setIcon(cardImage);
                flippedCardsCount++;
            }else{
                System.out.println("No more cards to flip.");
            }
        }
    }

    public boolean checkPlayerFinish(){
        for (Player player : players) {
            // Check if the player is inactive or has no chips left
            if (player.isActive() && player.getChips() > 0) {
                return false; // At least one player is still active
            }
        }
        return true; // All players are inactive or out of chips
    }
}
