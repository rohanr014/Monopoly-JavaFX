package app.engine.space;

import app.engine.agent.Player;

public class CommonSpace extends Space{
    private Space destinationSpace;
    private double amountMoney;


    public CommonSpace(Space space, double amount){
        destinationSpace = space;
        amountMoney = amount;
    }

    @Override
    protected void invokeAction(Player player) {
        if (destinationSpace!=null){
            getBoard().move(player, destinationSpace);
            if (getBoard().isJail(destinationSpace)){
                player.putInJail();
            }
        }
        if (amountMoney!=0){
            getBoard().getBank().giveMoney(player, amountMoney);
        }
    }

    public Space getDestinationSpace() {
        return destinationSpace;
    }

    public double getAmountMoney() {
        return amountMoney;
    }
}
