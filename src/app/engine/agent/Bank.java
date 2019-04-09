package app.engine.agent;

import app.engine.board.Board;

public class Bank extends Agent{

    public Bank(double initBalance, Board b) {
        super(initBalance, b);
    }

    @Override
    public boolean giveMoney(Agent a, double m){
        if (getWallet()<m)
            return false;
        a.addToWallet(m);
        setWallet(getWallet()-m);
        return true;
//        MORE LOGIC FOR BANKRUPTING
    }
}
