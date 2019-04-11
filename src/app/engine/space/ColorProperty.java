package app.engine.space;

import app.engine.agent.Agent;
import app.engine.Asset;
import app.engine.board.Board;
import app.engine.agent.Player;

import java.util.Collection;

//subclass for Color Properties
public class ColorProperty extends SetProperty{
    private boolean monopoly;
    private int houses;
    private double houseCost;
    private double[] houseRents;
    private int hotels;
    private double hotelCost;
    private double[] hotelRents;


    public ColorProperty(String name, double purchaseCost, double mortgageValue, double[] allRents, double houseCost, double hotelCost) {
        super(name, purchaseCost, mortgageValue, allRents);
        //use Game Rules (ex: up to 4 houses per property, 1 hotel after 4 houses, etc. to take allRents and parse the correct sub-sets into houseRents[] and hotelRents[])

    }


    @Override
    public double calculateRent() {
        if (getSharedSet().size() == getCompleteSet().size() - 1){
            monopoly = true;
            setRent(getRent() * 2); // 2x rent is vanilla monopoly rule
        }

        return super.getRent();
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


    //    public boolean buildHouse() {
//        //check if house can be built, increment houses, rent = houseRents[houses], charge owner houseCost
//    }
//
//    public boolean buildHotel() {
//        //check if hotel can be built, increment hotel, rent = hotelRents[hotels], charge owner hotelCost
//    }
//
//    public int getHouses() {
//        return houses;
//    }
//
//    public int getHotel() {
//        return hotels;
//    }

}