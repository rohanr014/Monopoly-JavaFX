//subclass for utilities and railroads
public interface SetProperty extends Property{

    //overridden from Engine.Property. This method updates the rents of the properties if one owner has multiple
    //of the same set
    @Override
    boolean ownershipChanged(Agent a);

}