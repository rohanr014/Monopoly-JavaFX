package app.engine.agent;

import app.engine.Asset;
import app.engine.card.HoldableCard;
import app.engine.space.ColorProperty;
import app.engine.space.Property;

import java.util.Collection;

public class Player extends Agent{
    private String name;
    private String pieceFile;
    private double wallet;
    private Collection< implements Asset>


    public Player(String n, String file, double initBalance){
        name = n;
        pieceFile = file;
        wallet = initBalance;
    }


    //player mortgages p, returns false if method fails
    boolean mortgage(Property p){
        return false;
    }

    //player unmortgages p, returns false if method fails
    boolean unmortgage(Property p){
        return false;
    }

    //player builds house on cp, returns false if method fails
    boolean buildHouse(ColorProperty cp){
        return false;
    }

    //player builds hotel on cp, returns false if method fails
    boolean buildHotel(ColorProperty cp){
        return false;
    }

    //player sells house on cp, returns false if method fails
    boolean sellHouse(ColorProperty cp){
        return false;
    }

    //player sells hotel on cp, returns false if method fails
    boolean sellHotel(ColorProperty cp){
        return false;
    }

    //player sells p to bank, returns false if method fails
    boolean sell(Property p){
        return false;
    }

    //player sells hc to bank, returns false if method fails
    boolean sell(HoldableCard hc){
        return false;
    }



}