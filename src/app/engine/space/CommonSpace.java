package app.engine.space;

import app.engine.agent.Player;

public class CommonSpace extends Space{
    private Space destinationSpace;
    private double amountMoney;

    String myName;

    public CommonSpace(String name){

        super(name);
    }

    @Override
    protected void invokeAction(Player player) {
        if (destinationSpace!=null){
            getBoard().move(player, destinationSpace);
            if (getBoard().isJail(destinationSpace)){
                player.goToJail();
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

    @Override
    public String getMyPropertyName(){
        return myName;
    }
}
