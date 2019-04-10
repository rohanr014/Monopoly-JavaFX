package app.engine.agent;

import app.engine.board.Board;
import app.engine.space.Property;

import java.util.List;

public abstract class Agent implements IAgentObservable{
    private double wallet;
    private Board board;
    private List<IAgentObserver> myObserverList;



    public Agent(double initBalance, Board b) {
        wallet = initBalance;
        board = b;
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

    public Board getBoard() {
        return board;
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
            o.agentUpdate();
        }
    }

}