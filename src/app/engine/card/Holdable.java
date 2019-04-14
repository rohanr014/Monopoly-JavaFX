package app.engine.card;

import app.engine.agent.Player;

public interface Holdable {
    void useCard(Player currentOccupant);
}
