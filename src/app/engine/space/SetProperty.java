package app.engine.space;

import app.engine.agent.Agent;
import app.engine.board.Board;

import java.util.ArrayList;
import java.util.List;

//subclass for utilities and railroads
public class SetProperty extends Property {
    private List<SetProperty> completeSet;
    private List<SetProperty> sameOwner;
    private double[] possibleRents;

    public SetProperty(double purchaseCost, double[] allRents, Board board) {
        super(purchaseCost, allRents[0], board);
        possibleRents = allRents;
        sameOwner = new ArrayList<>();
        completeSet =
    }

    public SetProperty(double purchaseCost) {
        super(purchaseCost)
    }

    public void fillCompleteSet(SetProperty sp1, SetProperty sp2, ...) {
        for (SetProperty sp : parameters){
            completeSet.add(sp);
        }
    }

    @Override
    protected boolean ownershipChanged(Agent a){
        super(p);
        updateSharedProperties();
        for (SetProperty sp : sameOwner) {
            sp.updatedSharedProperties();
        }
    }

    private boolean updateSharedProperties() {
        for (SetProperty sp : completeSet) {
            if (sp.getOwner() == getOwner && !sp.equals(this))
                sameOwner.add(sp);
        }
        return calculateRent(); //rent is recalculated whenever size of shared changes --> whenever the ownership of one of the properties in the set changes
        // alternate implementation: call calculateRent(shared.size());
    }

    // alternate implementation: public void calculateRent(int numSharedProperties)
    public boolean calculateRent() {
        check sameOwner.size(), do logic;
        setRent(updatedRent);
    }

    public boolean setRent(double newRent) {
        return super.setRent(newRent);
    }



}