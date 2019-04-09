package app.engine.space;

import app.engine.agent.Player;
import app.engine.board.Board;

public class Utility extends SetProperty {
    public Utility(double purchaseCost, double mortgageValue) {
        super(purchaseCost, mortgageValue);
    }

    public Utility(double purchaseCost, double mortgageValue, double[] allRentMultipliers) {
        super(purchaseCost, mortgageValue, allRentMultipliers);
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
