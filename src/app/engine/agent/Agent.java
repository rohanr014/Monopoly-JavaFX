package app.engine.agent;

import app.engine.board.Board;

import java.util.ArrayList;
import java.util.Objects;

import java.util.List;

public abstract class Agent implements IAgentObservable{
    private double wallet;
    private List<IAgentObserver> myObserverList;
    private int numHousesOwned;
    private int numHotelsOwned;
    private String myName;


    public Agent(double initBalance, String name) {
        wallet = initBalance;
        myName = name;
        myObserverList = new ArrayList<>();
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

    public String getName() { return myName; }

    //methods required for observer pattern
    @Override
    public void addAgentObserver(IAgentObserver o) {
        myObserverList.add(o);
    }

    @Override
    public void removeAgentObserver(IAgentObserver o) {
        myObserverList.remove(o);
    }


    @Override
    public void notifyAgentObservers(String logAction) {
        for(IAgentObserver o : myObserverList){
            o.agentUpdate(logAction);
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