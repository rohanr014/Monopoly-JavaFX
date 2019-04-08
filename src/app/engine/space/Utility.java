package app.engine.space;

import app.engine.agent.Player;
import app.engine.board.Board;

public class Utility extends SetProperty {
    public Utility(double purchaseCost, double mortgageValue, Board board) {
        super(purchaseCost, mortgageValue, board);
    }

    public Utility(double purchaseCost, double mortgageValue, Board board, double[] allRents) {
        super(purchaseCost, mortgageValue, board, allRents);
    }

    @Override
    public boolean calculateRent() {


        return setRent(updatedRent);
    }

    @Override
    public double getRent(){

    }
}
