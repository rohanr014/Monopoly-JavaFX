package app.engine.space;

import app.engine.board.Board;

public class Railroad extends SetProperty {

    public Railroad(String name, double purchaseCost, double mortgageValue, double[] allRents, String imageName){
        super(name, purchaseCost, mortgageValue, allRents);
        this.imageName = imageName;
    }


    public Railroad(String name, double purchaseCost, double mortgageValue, double buildCost) {
        super(name, purchaseCost, mortgageValue, buildCost);

    }

    public Railroad(String name, double purchaseCost, double mortgageValue, double[] allRents, double buildCost) {
        super(name, purchaseCost, mortgageValue, allRents, buildCost);
    }

    public Railroad(String name, double purchaseCost, double mortgageValue, double[] allRents, String imageName, double buildCost) {
        this(name, purchaseCost, mortgageValue, allRents, buildCost);
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
