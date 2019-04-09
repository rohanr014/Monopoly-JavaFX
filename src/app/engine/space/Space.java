package app.engine.space;

import app.engine.agent.Player;
import app.engine.board.Board;

import java.util.ArrayList;
import java.util.List;

public abstract class Space{
    private List<Player> currentOccupants;
    private Board board;

    public Space(){
        currentOccupants = new ArrayList<>();
    }

    /**
     * Function for space to perform action on player that
     * lands on it
     * @param p player to perform the action upon
     */
    public void onLand(Player p){
        addToCurrentOccupants(p);
        invokeAction(p);
    }

    public void initializeSpace(Board b){
        board = b;
    }

    protected abstract void invokeAction(Player p);

    private void addToCurrentOccupants(Player p) {
        currentOccupants.add(p);
    }

    public void removeFromCurrentOccupants(Player p){
        currentOccupants.remove(p);
    }


}