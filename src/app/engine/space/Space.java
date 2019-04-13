package app.engine.space;

import app.engine.agent.Player;
import app.engine.board.Board;

import java.util.ArrayList;
import java.util.List;

public abstract class Space implements ISpaceObservable {
    private List<Player> currentOccupants;
    private Board gameBoard;
    private String myName;

    public Space() {
        currentOccupants = new ArrayList<>();
    }
    public Space(String name){
        this();
        myName = name;
    }

    /**
     * Function for space to perform action on player that
     * lands on it
     * @param p player to perform the action upon
     */
    public void onLand(Player occupant){
        addToCurrentOccupants(occupant);
        invokeAction(occupant);
    }

    public void initializeSpace(Board board){
        gameBoard = board;
    }

    protected abstract void invokeAction(Player occupant);

    private void addToCurrentOccupants(Player occupant) {
        currentOccupants.add(occupant);
    }

    public void removeFromCurrentOccupants(Player occupant){
        currentOccupants.remove(occupant);
    }

    public boolean containsPlayer(Player player) {
        return (currentOccupants.contains(player));
    }

    public String getMyName() {
        return myName;
    }

    @Override
    public void addSpaceObserver(ISpaceObserver o){}

    @Override
    public void removeSpaceObserver(ISpaceObserver o){}

    @Override
    public void notifySpaceObservers(){};


}