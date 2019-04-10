package app.engine.agent;

import app.engine.board.Board;

import java.util.Objects;

public abstract class Agent implements IAgentObservable{
 private double wallet;

    public Agent(double initBalance) {
        wallet = initBalance;
    }


    /**
     * Function for agent to transfer money from themselves to another one
     *
     * @param m amount to be transferred
     * @param a agent to be transferred to
     * @return true if successful, false otherwise
     */
    public abstract boolean giveMoney(Agent a, double m);

    protected void addToWallet(double m) {
        wallet+=m;
    }

    public void setWallet(double m){
        wallet=m;
    }
    public double getWallet() {
        return wallet;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Double.compare(agent.wallet, wallet) == 0;
    }
}