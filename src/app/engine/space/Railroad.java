package app.engine.space;

import app.engine.board.Board;

public class Railroad extends SetProperty {
    public Railroad(double purchaseCost, double mortgageValue, Board board) {
        super(purchaseCost, mortgageValue, board);
    }

    public Railroad(double purchaseCost, double mortgageValue, Board board, double[] allRents) {
        super(purchaseCost, mortgageValue, board, allRents);
    }

    @Override
    public boolean calculateRent() {
        int numSharedProperties = sharedSet.size();
        double updatedRent = possibleRents[numSharedProperties];
        return setRent(updatedRent);
    }


}
