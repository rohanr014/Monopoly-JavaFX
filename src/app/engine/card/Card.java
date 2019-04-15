package app.engine.card;

import app.engine.agent.Player;
import app.engine.board.Board;

import java.util.Queue;

public abstract class Card{
    private String description;
    private Board board;
    private Queue<Card> originPile;

    public Card(String desc, Board b){
        this.description = desc;
        this.board = b;
    }

    /**
     * Function for card to invoke correct action on player
     */

    public abstract void invokeAction(Player currentOccupant);

    public String getDescription() {
        return description;
    }

    public Board getBoard() {
        return board;
    }

    public void setOriginPile(Queue<Card> pile){
        originPile = pile;
    }

    protected boolean putSelfBackInPile(){
        return originPile.add(this);
    }
}