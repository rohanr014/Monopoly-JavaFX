package app.engine.space;

import app.engine.agent.Player;
import app.engine.board.Board;

import java.util.ArrayList;
import java.util.List;

public abstract class Space implements ISpaceObservable {
    private List<Player> currentOccupants;
    private Board gameBoard;

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

    public void initializeSpace(Board board){
        gameBoard = board;
    }



    private void addToCurrentOccupants(Player occupant) {
        currentOccupants.add(occupant);
    }

    public void removeFromCurrentOccupants(Player occupant){
        currentOccupants.remove(occupant);
    }

    @Override
    public void addSpaceObserver(ISpaceObserver o){}

    @Override
    public void removeSpaceObserver(ISpaceObserver o){}

    @Override
    public void notifySpaceObservers(){}

    abstract String getMyPropertyName();

    protected abstract void invokeAction(Player occupant);


}