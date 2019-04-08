package app.engine.space;

import app.engine.agent.Player;

public abstract class Space{

    public Space(){

    }

    /**
     * Function for space to perform action on player that
     * lands on it
     * @param p player to perform the action upon
     */
    public abstract boolean onLand(Player p);

}