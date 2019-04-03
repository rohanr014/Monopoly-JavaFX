public interface Property{

    /**
     * Function to check if property bought by a player
     *
     * @param p player being checked
     * @return true if Player p owns it, false otherwise
     */
    public boolean boughtBy(Player p);


    /**
     * Function to mortgage property
     *
     * @return true if successful, false otherwise
     */
    public boolean mortgage();


    /**
     * Function to sell property back to bank
     *
     * @return true if succesful, false otherwise
     */
    public boolean sellToBank();


}