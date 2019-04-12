package app.engine.card;

import app.engine.agent.Player;
import app.engine.board.Board;

import java.util.List;

public class MoveNumberCard extends Card {
    private int spaces;
    private Board b;
    private String description;

    public MoveNumberCard(String desc, Board b, int spaces){
        super(desc, b);
        this.spaces = spaces;
    }

    @Override
    public void invokeAction(Player currentOccupant) {
        Board board = getBoard();
        board.move(currentOccupant, spaces);
    }

    @Override
    public void useCard(Player currentOccupant) {
        // nothing here I think...
    }
}
