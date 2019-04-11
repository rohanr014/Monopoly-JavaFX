package app.engine.card;

import app.engine.agent.Player;

public abstract class ImmediateCard extends Card {

    @Override
    public abstract void invokeAction(Player currentOccupant);

    @Override
    public abstract void useCard(Player currentOccupant);

}
