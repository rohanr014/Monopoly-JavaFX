package app.engine.space;

import app.engine.agent.Player;
import app.engine.board.Board;


public class Utility extends SetProperty {
    private String myName;

    public Utility(String name, double purchaseCost, double mortgageValue) {
        super(purchaseCost, mortgageValue);
        this.myName = name;
    }

    public Utility(String name, double purchaseCost, double mortgageValue, double[] allRentMultipliers) {
        super(purchaseCost, mortgageValue, allRentMultipliers);
        this.myName = name;
    }

    @Override
    public double calculateRent() {
        var roll = getBoard().getLastRollSum();
        var mult = getPossibleRents()[getSharedSetSize()];
        var newRent = roll*mult;
        setRent(newRent);
        return newRent;
    }

    public String getMyProtertyName() {
        return myName;
    }
}
