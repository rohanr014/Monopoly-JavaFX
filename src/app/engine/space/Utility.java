package app.engine.space;

import app.engine.agent.Player;
import app.engine.board.Board;


public class Utility extends SetProperty {


    public Utility(String name, double purchaseCost, double mortgageValue) {
        super(name, purchaseCost, mortgageValue);
    }

    public Utility(String name, double purchaseCost, double mortgageValue, double[] allRentMultipliers) {
        super(name, purchaseCost, mortgageValue, allRentMultipliers);

    }

    public Utility(String name, double purchaseCost, double mortgageValue, double[] allRentMultipliers, String imageName) {
        this(name, purchaseCost, mortgageValue, allRentMultipliers);
        this.imageName = imageName;

    }

    @Override
    public double calculateRent() {
        var roll = getBoard().getLastRollSum();
        var mult = getPossibleRents()[getSharedSetSize()];
        var newRent = roll*mult;
        setRent(newRent);
        return newRent;
    }

}
