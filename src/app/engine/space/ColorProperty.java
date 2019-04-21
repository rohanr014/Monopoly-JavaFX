package app.engine.space;

//subclass for Color Properties
public class ColorProperty extends SetProperty{
    private boolean monopoly;
    private double[] developmentRents;
    private String name;
    private String myColor;

    public ColorProperty(String name, double purchaseCost, double mortgageValue, double[] allRents, double buildCost,String colorString) {
        super(name, purchaseCost, mortgageValue, allRents, buildCost);
        developmentRents = allRents;
        //use Game Rules (ex: up to 4 houses per property, 1 hotel after 4 houses, etc. to take allRents and parse the correct sub-sets into houseRents[] and hotelRents[])
        this.name = name;
        this.myColor = colorString;
    }

    public ColorProperty(String name, double purchaseCost, double mortgageValue, double[] allRents, double buildCost, String colorString, String imageName) {
        this(name, purchaseCost, mortgageValue, allRents, buildCost, colorString);
        this.imageName = imageName;
    }

        @Override
    public double calculateRent() {
        if (monopoly) {
            if (getHotels() == 0) {
                if (getHouses() == 0) {
                    setRent(getRent() * 2);
                }
                else {
                    setRent(developmentRents[getHouses()]);
                }
            }
            else {
                setRent(developmentRents[developmentRents.length - 1]);
            }
        }
        return getRent();
    }

    @Override
    public boolean mortgage() {
        if (getHotels() == 0 && getHouses() == 0 ){
            return super.mortgage();
        } else {
//            throw sell houses
        }
        return false;
    }



    public boolean buildHouse() {
        if (getHouses() == 4 || (!(monopoly))) {
            return false;
        }
        int minHouses = 0;
        for (SetProperty sp : sharedSet) {
            if (sp instanceof ColorProperty) {
                ColorProperty cp = (ColorProperty) sp;
                if (cp.getHouses() > this.getHouses() + 1 || cp.getHouses() < this.getHouses() - 1) {
                    return false;
                }
                if (cp.getHouses() < minHouses) {
                    minHouses = cp.getHouses();
                }
            }
        }
        if (this.getHouses() != minHouses) {return false;}
        if (getOwner().giveMoney(getBoard().getBank(), getHouseCost())) {
            setHouses(getHouses()+1);
            calculateRent();
            return true;
        }
        return false;
    }

    public boolean buildHotel() {
        if (this.getHouses() != 4) {
            return false;
        }
        for (SetProperty sp : sharedSet) {
            if (sp instanceof ColorProperty) {
                ColorProperty cp = (ColorProperty) sp;
                if (cp.getHouses() < this.getHouses() && cp.getHotels() == 0) {
                    return false;
                }
            }
        }
        if (getOwner().giveMoney(getBoard().getBank(), getHotelCost())) {
            setHotels(getHotels()+1);
            calculateRent();
            return true;
        }
        return false;
    }

    public boolean sellHouse() {
        if (getHouses() == 0) {
            return false;
        }
        else if (getBoard().getBank().giveMoney(getOwner(), getHouseCost())) {
            setHouses(getHouses()-1);
            calculateRent();
            return true;
        }
        return false;
    }

    public boolean sellHotel() {
        if (getHotels() == 0) {
            return false;
        }
        else if (getBoard().getBank().giveMoney(getOwner(), getHotelCost())) {
            setHotels(getHotels()-1);
            calculateRent();
            return true;
        }
        return false;
    }



    public String getMyColor() {
        return myColor;
    }

    //public int getMyRent() { return this.}
    public double[] getAllRent() { return super.getPossibleRents();}

}