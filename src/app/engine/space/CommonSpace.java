package app.engine.space;

import app.engine.agent.Player;

import java.util.List;

public class CommonSpace extends Space{

    private Space destinationSpace;
    private final int stepsToMove;
    private double amountMoney;

    public CommonSpace(String name, Space space, int steps, double money){
        super(name);
        destinationSpace = space;
        stepsToMove = steps;
        amountMoney = money;
    }

    public CommonSpace(String name, String destinationName){
        super(name);
        destinationSpace = findSpace(destinationName);
        stepsToMove = 0;
        amountMoney = 0;
    }

    public CommonSpace(String name, double money) {
        this(name, null, 0, money);
    }

    public CommonSpace(String name, Space space) {
        this(name, space, 0, 0);
    }

    public CommonSpace(String name, int steps) {
        this(name, null, steps, 0);
    }


    @Override
    protected void invokeAction(Player player) {
        if (destinationSpace!=null){
            getBoard().move(player, destinationSpace);
            if (getBoard().isJail(destinationSpace)){
                player.goToJail();
            }
        } else if (stepsToMove!=0){
            getBoard().move(player, stepsToMove);
        }
        if (amountMoney!=0){
            getBoard().getBank().giveMoney(player, amountMoney);
        }
    }

    private Space findSpace(String spaceName){
        List<Space> spaces = getBoard().getSpaces();

        for(Space space:spaces){
            if(space.getName().equalsIgnoreCase(spaceName)){
                return space;
            }
        }
        return null;
    }

    public Space getDestinationSpace() {
        return destinationSpace;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public int getStepsToMove() {
        return stepsToMove;
    }
}
