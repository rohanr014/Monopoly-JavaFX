package app.engine;

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