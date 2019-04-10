package app.engine.agent;

import app.engine.board.Board;

import java.util.Objects;

import java.util.List;

public abstract class Agent implements IAgentObservable{
    private double wallet;
    private Board board;
    private List<IAgentObserver> myObserverList;



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
        myObserverList.add(o);
    }

    @Override
    public void removeAgentObserver(IAgentObserver o) {
        myObserverList.add(o);
    }

    @Override
    public void notifyAgentObservers() {
        for(IAgentObserver o : myObserverList){
            o.agentUpdate(wallet, board);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Double.compare(agent.wallet, wallet) == 0;
    }
}