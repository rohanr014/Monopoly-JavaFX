package app.engine.space;

import app.engine.agent.Agent;
import app.engine.board.Board;

import java.util.*;

//subclass for utilities and railroads
public abstract class SetProperty extends Property {
    protected Set<SetProperty> completeSet;
    protected Set<SetProperty> sharedSet;
    protected double[] possibleRents;

    public SetProperty(double purchaseCost, double mortgageValue, Board board) {
        super(purchaseCost, mortgageValue, board);
        sharedSet = new HashSet<>();
        completeSet = new HashSet<>();
    }

    public SetProperty(double purchaseCost, double mortgageValue, Board board, double[] allRents) {
        this(purchaseCost,  mortgageValue, board);
        possibleRents = allRents;
    }

    public void fillCompleteSet(SetProperty... totalSet) {
        for (SetProperty sp : totalSet){
            completeSet.add(sp);
        }
    }

    @Override
    protected boolean ownershipSwitchedTo(Agent a){
        super.ownershipSwitchedTo(a);
        updateSharedProperties();
        for (SetProperty sp : sharedSet) {
            sp.updateSharedProperties();
        }
    }

    private boolean updateSharedProperties() {
        for (SetProperty sp : completeSet) {
            if (sp.getOwner() == getOwner() && !sp.equals(this))
                sharedSet.add(sp);
            else {
                sharedSet.remove(sp);
            }
        }
        return calculateRent(); //rent is recalculated whenever size of shared changes --> whenever the ownership of one of the properties in the set changes
        // alternate implementation: call calculateRent(shared.size());
    }

    // alternate implementation: public void calculateRent(int numSharedProperties)
    public abstract boolean calculateRent();

    public boolean setRent(double newRent) {
        return super.setRent(newRent);
    }


    public Collection<SetProperty> getSharedSet() {
        return sharedSet;
    }

    public Collection<SetProperty> getCompleteSet() {
        return completeSet;
    }

}