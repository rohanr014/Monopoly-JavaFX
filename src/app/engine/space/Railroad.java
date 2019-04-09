package app.engine.space;

import app.engine.board.Board;

public class Railroad extends SetProperty {
    public Railroad(double purchaseCost, double mortgageValue) {
        super(purchaseCost, mortgageValue);
    }

    public Railroad(double purchaseCost, double mortgageValue, double[] allRents) {
        super(purchaseCost, mortgageValue, allRents);
    }

    @Override
    public double calculateRent() {
        int numSharedProperties = sharedSet.size();
        double updatedRent = possibleRents[numSharedProperties];
        setRent(updatedRent);
        return updatedRent;
    }


}
