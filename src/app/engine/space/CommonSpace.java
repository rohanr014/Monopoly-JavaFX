package app.engine.space;

import app.engine.agent.Player;

import java.util.List;

public class CommonSpace extends Space{

    private final int stepsToMove;
    private double amountMoney;
    private String destinationSpaceName;

    private String imageName;

    public CommonSpace(String name, String destinationName, int steps, double money){
        super(name);
        destinationSpaceName = destinationName;
        stepsToMove = steps;
        amountMoney = money;
    }

    public CommonSpace(String name, String destinationName, String imageName){
        this(name, destinationName);
        this.imageName = imageName;
    }

    public CommonSpace(String name, double money, String imageName){
        this(name, money);
        this.imageName = imageName;
    }

    public CommonSpace(String name, int steps, String imageName){
        this(name, steps);
        this.imageName = imageName;
    }

//    space moves player to another space with the name destinationName
    public CommonSpace(String name, String destinationName){ this(name, destinationName, 0, 0); }

//    space gives/takes player money
    public CommonSpace(String name, double money) {
        this(name, null, 0, money);
    }

//    space moves player a certain number of steps
    public CommonSpace(String name, int steps) {
        this(name, null, steps, 0);
    }


    @Override
    protected void invokeAction(Player player) {
        if (destinationSpaceName!=null){
            var destinationSpace = findSpace(destinationSpaceName);
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

    public String getDestinationSpaceName() {
        return destinationSpaceName;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public int getStepsToMove() {
        return stepsToMove;
    }
    public String getImageName() { return imageName; }

}
