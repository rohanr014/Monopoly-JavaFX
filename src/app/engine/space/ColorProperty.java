package app.engine.space;

import app.engine.agent.Agent;
import app.engine.Asset;
import app.engine.board.Board;
import app.engine.agent.Player;

import java.awt.*;
import java.util.Collection;

//subclass for Color Properties
public class ColorProperty extends SetProperty{
    private boolean monopoly;
    private int houses;
    private double housePrice;
    private int hotels;
    private double hotelPrice;
    private double[] developmentRents;
    private String name;
    private String myColor;

    public ColorProperty(String name, double purchaseCost, double mortgageValue, double[] allRents, double houseCost, double hotelCost,String colorString) {
        super(name, purchaseCost, mortgageValue, allRents);
        housePrice = houseCost;
        hotelPrice = hotelCost;
        developmentRents = allRents;
        //use Game Rules (ex: up to 4 houses per property, 1 hotel after 4 houses, etc. to take allRents and parse the correct sub-sets into houseRents[] and hotelRents[])
        this.name = name;
        this.myColor = colorString;
    }

    @Override
    public double calculateRent() {
        if (monopoly) {
            if (hotels == 0) {
                if (houses == 0) {
                    setRent(getRent() * 2);
                }
                else {
                    setRent(developmentRents[houses]);
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
        if (hotels == 0 && houses == 0 ){
            return super.mortgage();
        } else {
//            throw sell houses
        }
        return false;
    }

    public double getHotelCost() {
        return hotelPrice;
    }

    public boolean buildHouse() {
        if (houses == 4 || (!(monopoly))) {
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
        if (getOwner().giveMoney(getBoard().getBank(), housePrice)) {
            houses++;
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
        if (getOwner().giveMoney(getBoard().getBank(), hotelPrice)) {
            hotels++;
            calculateRent();
            return true;
        }
        return false;
    }

    public boolean sellHouse() {
        if (getHouses() == 0) {
            return false;
        }
        else if (getBoard().getBank().giveMoney(getOwner(), housePrice)) {
            houses--;
            calculateRent();
            return true;
        }
        return false;
    }

    public boolean sellHotel() {
        if (getHotels() == 0) {
            return false;
        }
        else if (getBoard().getBank().giveMoney(getOwner(), hotelPrice)) {
            hotels--;
            calculateRent();
            return true;
        }
        return false;
    }

    public int getHouses() {
        return houses;
    }

    public int getHotels() {
        return hotels;
    }

    public String getMyColor() {
        return myColor;
    }

    //public int getMyRent() { return this.}
    public double[] getAllRent() { return super.getPossibleRents();}

}