package Engine;

public class Property extends Space implements Asset{

    /**
     * Function to check if property bought by a player
     *
     * @param p player being checked
     * @return true if Engine.Player p owns it, false otherwise
     */
    public boolean boughtBy(Player p){
        return false;
    }


    /**
     * Function to mortgage property
     *
     * @return true if successful, false otherwise
     */
    public boolean mortgage(){
        return false;
    }


    /**
     * Function to sell property back to bank
     *
     * @return true if succesful, false otherwise
     */
    public boolean sellToBank(){
        return false;
    }


    protected boolean ownershipChanged(Agent a){
        return false;
    }


    @Override
    public boolean onLand(Player p) {
        return false;
    }
}