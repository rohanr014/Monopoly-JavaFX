package app.engine;

//subclass for utilities and railroads
public class SetProperty extends Property{

    @Override
    protected boolean ownershipChanged(Agent a){
        return false;
    }

}