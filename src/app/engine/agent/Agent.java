package app.engine.agent;

import app.engine.board.Board;
import app.engine.space.Property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import java.util.List;

public abstract class Agent implements IAgentObservable{
    private double wallet;
    private List<IAgentObserver> myObserverList;
    private String myName;
    private List<Property> properties;


    public Agent(double initBalance, String name) {
        wallet = initBalance;
        myName = name;
        myObserverList = new ArrayList<>();
        properties = new ArrayList<>();
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
        System.out.println(logAction);
        for(IAgentObserver o : myObserverList){
            o.agentUpdate(logAction);
        }
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Agent agent = (Agent) o;
//        return Double.compare(agent.wallet, wallet) == 0;
//    }

    public void addAllProperties(Collection<Property> propsToAdd) {
        properties.addAll(propsToAdd);
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public void removeProperty(Property property) {
        properties.remove(property);
    }

    public List<Property> getProperties() {
        return properties;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Agent)) {
            return false;
        }
        Agent a = (Agent) o;
        return (a.getName().equals(this.getName()) && Double.compare(a.wallet, wallet) == 0);
    }

//    @Override
//    public String toString() {
//        return getName();
//    }

}