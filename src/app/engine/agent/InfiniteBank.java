package app.engine.agent;

import app.engine.board.Board;

public class InfiniteBank extends Bank {
    public InfiniteBank(Board b) {
        super(0, b);
    }

    @Override
    public boolean giveMoney(Agent a, double m){
        a.addToWallet(m);
        return true;

//        MORE LOGIC FOR BANKRUPTING
    }
}
