public interface Agent{

    /**
     * Function for agent/player to acquire a property
     *
     * @param p property to be bought
     * @return true if succesful, false otherwise
     */
    boolean buy(Property p);


    /**
     * Function for agent to transfer money from themselves to another one
     *
     * @param m amount to be transferred
     * @param a agent to be transferred to
     * @return true if successful, false otherwise
     */
    boolean giveMoneyTo(double m, Agent a);

}