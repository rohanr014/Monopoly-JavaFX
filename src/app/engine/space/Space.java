package app.engine.space;

import app.engine.agent.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Space{
    private List<Player> currentOccupants;

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

    protected abstract void invokeAction(Player p);

    private void addToCurrentOccupants(Player p) {
        currentOccupants.add(p);
    }

    public void removeFromCurrentOccupants(Player p){
        currentOccupants.remove(p);
    }


}