package app.engine.space;

import app.engine.board.Board;

public class Railroad extends SetProperty {

    public Railroad(String name, double purchaseCost, double mortgageValue, double houseCost, double hotelCost) {
        super(name, purchaseCost, mortgageValue, houseCost, hotelCost);
    }

    public Railroad(String name, double purchaseCost, double mortgageValue, double[] allRents, double houseCost, double hotelCost) {

        super(name, purchaseCost, mortgageValue, allRents, houseCost, hotelCost);
    }

    public Railroad(String name, double purchaseCost, double mortgageValue, double[] allRents, String imageName, double houseCost, double hotelCost) {
        this(name, purchaseCost, mortgageValue, allRents, houseCost, hotelCost);
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
