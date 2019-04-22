package app.engine.space;

import app.engine.agent.Player;
import app.engine.board.Board;

import java.util.ArrayList;
import java.util.List;

public abstract class Space implements ISpaceObservable {
    private List<Player> currentOccupants;
    private Board gameBoard;
    private String name;

    private List<ISpaceObserver> mySpaceObserverList;

    public Space(String name){
        currentOccupants = new ArrayList<>();
        mySpaceObserverList = new ArrayList<>();
        this.name = name;
    }

    /**
     * Function for space to perform action on player that
     * lands on it
     * @param occupant perform the action upon the palyer
     */
    public void onLand(Player occupant){
        addToCurrentOccupants(occupant);
        invokeAction(occupant);
    }
    protected abstract void invokeAction(Player occupant);

    public void initializeSpace(Board board){
        gameBoard = board;
    }

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
        return name;
    }

    public List<Player> getCurrentOccupants() {
        return currentOccupants;
    }

    public List<ISpaceObserver> getMySpaceObserverList(){
        return mySpaceObserverList;
    }
    @Override
    public void addSpaceObserver(ISpaceObserver o){
        mySpaceObserverList.add(o);
    }

    @Override
    public void removeSpaceObserver(ISpaceObserver o){
        mySpaceObserverList.remove(o);
    }

    @Override
    public void notifySpaceObservers(){
        for(ISpaceObserver spaceObserver : mySpaceObserverList){
            spaceObserver.spaceUpdate();
        }
    }

    public List<ISpaceObserver> getSpaceObservers() {
        return mySpaceObserverList;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return gameBoard;
    }
}