package app.engine.agent;

import app.engine.board.Board;

import java.util.Objects;

import java.util.List;

public abstract class Agent implements IAgentObservable{
    private double wallet;
    private List<IAgentObserver> myObserverList;
    private int numHousesOwned;
    private int numHotelsOwned;


    public Agent(double initBalance) {
        wallet = initBalance;
    }


    /**
     * Function for agent to transfer money from themselves to another one
     *
     * @param amount amount to be transferred
     * @param agent agent to be transferred to
     * @return true if successful, false otherwise
     */
    public abstract boolean giveMoney(Agent agent, double amount);

    protected void addToWallet(double amount) {
        wallet+=amount;
    }

    public void setWallet(double amount){
        wallet=amount;
    }

    public double getWallet() {
        return wallet;
    }

    //methods required for observer pattern
    @Override
    public void addAgentObserver(IAgentObserver o) {
        myObserverList.add(o);
    }

    @Override
    public void removeAgentObserver(IAgentObserver o) {
        myObserverList.add(o);
    }

    @Override
    public void notifyAgentObservers() {
        for(IAgentObserver o : myObserverList){
            o.agentUpdate(wallet);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Double.compare(agent.wallet, wallet) == 0;
    }

    public boolean hasBuildings() {
        return (numHousesOwned>0||numHotelsOwned>0);
    }
}