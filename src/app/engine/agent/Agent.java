package app.engine.agent;

import app.engine.space.Property;

public abstract class Agent implements IAgentObservable{


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
    public boolean giveMoney(Agent a, double m){
        return false;
    }

    //methods required for observer pattern
    @Override
    public void addAgentObserver(IAgentObserver o) {

    }

    @Override
    public void removeAgentObserver(IAgentObserver o) {

    }

    @Override
    public void notifyAgentObservers() {

    }

}