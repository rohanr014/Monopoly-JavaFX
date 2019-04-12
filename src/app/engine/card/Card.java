package app.engine.card;

import app.engine.agent.Player;
import app.engine.board.Board;

public abstract class Card{

    private String description;
    private Board board;

    public Card(String desc, Board b){
        this.description = desc;
        this.board = b;
    }

    /**
     * Function for card to invoke correct action on player
     */

    public abstract void invokeAction(Player currentOccupant);

    /**
     * Function for player to use card (like get out of jail free)
     */

    public abstract void useCard(Player currentOccupant);

    public String getDescription() {
        return description;
    }

    public Board getBoard() {
        return board;
    }
}