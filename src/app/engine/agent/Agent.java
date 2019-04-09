package app.engine.agent;

import app.engine.board.Board;
import app.engine.space.Property;

public abstract class Agent implements IAgentObservable{
 private double wallet;
    private Board board;


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
    public void addObserver(IAgentObserver o) {

    }

    @Override
    public void removeObserver(IAgentObserver o) {

    }

    @Override
    public void notifyObservers() {

    }

}