package Engine;

public abstract class Agent{


    public Agent(){

    }

    /**
     * Function for agent/player to acquire a property
     *
     * @param p property to be bought
     * @return true if succesful, false otherwise
     */
    public boolean buy(Property p){
        return false;
    }


    /**
     * Function for agent to transfer money from themselves to another one
     *
     * @param m amount to be transferred
     * @param a agent to be transferred to
     * @return true if successful, false otherwise
     */
    public boolean giveMoneyTo(double m, Agent a){
        return false;
    }

}