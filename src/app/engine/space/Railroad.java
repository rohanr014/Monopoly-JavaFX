package app.engine.space;

import app.engine.board.Board;

public class Railroad extends SetProperty {
    private String myName;

    public Railroad(String name, double purchaseCost, double mortgageValue) {
        super(purchaseCost, mortgageValue);
        this.myName = name;

    }

    public Railroad(String name, double purchaseCost, double mortgageValue, double[] allRents) {
        super(purchaseCost, mortgageValue, allRents);
        this.myName = name;
    }

    @Override
    public double calculateRent() {
        int numSharedProperties = sharedSet.size();
        double updatedRent = possibleRents[numSharedProperties];
        setRent(updatedRent);
        return updatedRent;
    }

    public String getMyPropertyName() {
        return myName;
    }
}
