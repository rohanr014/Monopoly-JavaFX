package app.engine.space;

import app.engine.board.Board;

public class Railroad extends SetProperty {
    private String name;

    public Railroad(String name, double purchaseCost, double mortgageValue) {
        super(purchaseCost, mortgageValue);
        this.name = name;

    }

    public Railroad(String name, double purchaseCost, double mortgageValue, double[] allRents) {
        super(purchaseCost, mortgageValue, allRents);
        this.name = name;
    }

    @Override
    public double calculateRent() {
        int numSharedProperties = sharedSet.size();
        double updatedRent = possibleRents[numSharedProperties];
        setRent(updatedRent);
        return updatedRent;
    }


}
