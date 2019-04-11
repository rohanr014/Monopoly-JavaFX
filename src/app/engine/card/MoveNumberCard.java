package app.engine.card;

import app.engine.agent.Player;

public class MoveNumberCard extends ImmediateCard {
    private int spaces;

    public MoveNumberCard(int spaces){
        this.spaces = spaces;
    }

    @Override
    public void invokeAction(Player currentOccupant) {
        // move spaces...?
    }

    @Override
    public void useCard(Player currentOccupant) {
        // nothing here I think...
    }
}
