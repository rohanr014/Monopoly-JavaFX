package app.engine;

import app.engine.agent.Bank;
import app.engine.agent.Player;
import app.engine.card.Card;
import app.engine.space.Space;

import java.util.Collection;
import java.util.List;
import java.util.Queue;

public class GameSetup {
    public GameSetup(String propfile) {
    }

    public Collection<Card> getCommunityChest() {
        return null;
    }

    public Collection<Card> getChanceCards() {
        return null;
    }

    public Queue<Player> getPlayers() {
        return null;
    }

    public List<Space> getSpaces() {
        return null;
    }

    public List<Dice> getDice() {
        return null;
    }

    public Bank getBank() {
        return null;
    }
}
