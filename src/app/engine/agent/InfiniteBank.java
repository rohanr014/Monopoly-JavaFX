package app.engine.agent;

public class InfiniteBank extends Bank {
    public InfiniteBank() {
        super(0);
    }

    @Override
    public boolean giveMoney(Agent a, double m){
        a.addToWallet(m);
        return true;

//        MORE LOGIC FOR BANKRUPTING
    }
}
