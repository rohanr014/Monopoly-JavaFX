package app.engine.agent;

import app.engine.board.Board;

public class Bank extends Agent{

    public Bank(double initBalance, String name) {
        super(initBalance, name);
    }

    @Override
    public boolean giveMoney(Agent agent, double m){
        if (getWallet()< m) {
//            prob should throw something here? better than boolean?
            return false;
        }
        agent.addToWallet(m);
        setWallet(getWallet()-m);
        notifyAgentObservers(getName() + " paid " + agent.getName() + " $ " + m + ".");
        return true;
//        MORE LOGIC FOR BANKRUPTING
    }

}
