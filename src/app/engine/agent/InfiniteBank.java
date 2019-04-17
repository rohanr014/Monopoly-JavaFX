package app.engine.agent;

public class InfiniteBank extends Bank {
    public InfiniteBank() {
        super(0, "infinite bank");
    }

    @Override
    public boolean giveMoney(Agent agent, double amount){
        agent.addToWallet(amount);
        return true;

//        MORE LOGIC FOR BANKRUPTING
    }
}
