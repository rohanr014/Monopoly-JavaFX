package app.engine.space;

import app.engine.board.Board;

public class Railroad extends SetProperty {

    public Railroad(String name, double purchaseCost, double mortgageValue) {
        super(name, purchaseCost, mortgageValue);
    }

    public Railroad(String name, double purchaseCost, double mortgageValue, double[] allRents) {

        super(name, purchaseCost, mortgageValue, allRents);
    }

    public Railroad(String name, double purchaseCost, double mortgageValue, double[] allRents, String imageName) {
        this(name, purchaseCost, mortgageValue, allRents);
        this.imageName = imageName;
    }

    @Override
    public double calculateRent() {
        int numSharedProperties = sharedSet.size();
        double updatedRent = possibleRents[numSharedProperties];
        setRent(updatedRent);
        return updatedRent;
    }

}
