package app.engine.space;

import app.engine.agent.Agent;
import app.engine.board.Board;

import java.util.*;

//subclass for utilities and railroads
public abstract class SetProperty extends Property {
    protected Set<SetProperty> completeSet;
    protected Set<SetProperty> sharedSet;
    protected double[] possibleRents;
    protected String imageName;



    public SetProperty(String name, double purchaseCost, double mortgageValue) {
        super(name, purchaseCost, mortgageValue);
        sharedSet = new HashSet<>();
        completeSet = new HashSet<>();
    }

    public SetProperty(String name, double purchaseCost, double mortgageValue, double[] allRents) {
        super(name, purchaseCost, mortgageValue);
        sharedSet = new HashSet<>();
        completeSet = new HashSet<>();
        possibleRents = allRents;
    }


    public SetProperty(String name, double purchaseCost, double mortgageValue, double buildCost) {
        super(name, purchaseCost, mortgageValue, buildCost);
        sharedSet = new HashSet<>();
        completeSet = new HashSet<>();
    }

    public SetProperty(String name, double purchaseCost, double mortgageValue, double[] allRents, double buildCost) {
        this(name, purchaseCost,  mortgageValue, buildCost);
        possibleRents = allRents;
        setRent(allRents[0]);
    }


    public void fillCompleteSet(SetProperty... totalSet) {
        for (SetProperty sp : totalSet){
            completeSet.add(sp);
        }
    }

    @Override
    public boolean ownershipSwitchedTo(Agent a){
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

    public String getImageName() {
        return imageName;
    }
}