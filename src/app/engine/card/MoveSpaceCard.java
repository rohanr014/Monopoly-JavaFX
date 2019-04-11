package app.engine.card;

import app.engine.agent.Player;
import app.engine.space.Space;

public class MoveSpaceCard extends ImmediateCard {
    private String destinationName;

    public MoveSpaceCard(String spaceName){
        this.destinationName = spaceName;
    }

    @Override
    public void invokeAction(Player currentOccupant) {
        // move player to destination
    }

    @Override
    public void useCard(Player currentOccupant) {
        // nothing?
    }
}
