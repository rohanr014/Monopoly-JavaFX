package app.engine.space;

import app.engine.agent.Agent;
import app.engine.board.Board;

import java.util.*;

//subclass for utilities and railroads
public abstract class SetProperty extends Property {
    protected Set<SetProperty> completeSet;
    protected Set<SetProperty> sharedSet;
    protected double[] possibleRents;

    public SetProperty(double purchaseCost, double mortgageValue) {
        super(purchaseCost, mortgageValue);
        sharedSet = new HashSet<>();
        completeSet = new HashSet<>();
    }

    public SetProperty(double purchaseCost, double mortgageValue, double[] allRents) {
        this(purchaseCost,  mortgageValue);
        possibleRents = allRents;
    }

    public void fillCompleteSet(SetProperty... totalSet) {
        for (SetProperty sp : totalSet){
            completeSet.add(sp);
        }
    }

    @Override
    protected boolean ownershipSwitchedTo(Agent a){
        if (!super.ownershipSwitchedTo(a)){
            return false;
        }
        updateSharedProperties();
        for (SetProperty sp : sharedSet) {
            sp.updateSharedProperties();
        }
        return true;
    }

    private void updateSharedProperties() {
        for (SetProperty sp : completeSet) {
            if (sp.getOwner() == getOwner() && !sp.equals(this))
                sharedSet.add(sp);
            else {
                sharedSet.remove(sp);
            }
        }
        super.calculateRent(); //rent is recalculated whenever size of shared changes --> whenever the ownership of one of the properties in the set changes
        // alternate implementation: call calculateRent(shared.size());
    }

    public boolean setRent(double newRent) {
        return super.setRent(newRent);
    }


    public Collection<SetProperty> getSharedSet() {
        return sharedSet;
    }

    public Collection<SetProperty> getCompleteSet() {
        return completeSet;
    }

    public double[] getPossibleRents() {
        return possibleRents;
    }

    public int getSharedSetSize(){
        return sharedSet.size();
    }


}