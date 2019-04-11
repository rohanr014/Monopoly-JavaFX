package app.engine.card;

import app.engine.Asset;
import app.engine.agent.Player;
import app.engine.board.Board;

public class HoldableCard extends Card implements Asset {

    public HoldableCard(String desc, Board b){
        super(desc, b);
    }

    @Override
    public void invokeAction(Player currentOccupant) {

    }

    @Override
    public void useCard(Player currentOccupant) {

    }

    public boolean sellToBank() {
        return false;
    }
}
