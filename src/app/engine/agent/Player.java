package app.engine.agent;

import app.engine.Asset;
import app.engine.board.Board;
import app.engine.card.HoldableCard;
import app.engine.space.ColorProperty;
import app.engine.space.Property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Player extends Bank{
    private String name;
    private String pieceFile;
    private List<Property> properties;
    private List<HoldableCard> cards;
    private int currentSpace;


    public Player(String n, String file, double initBalance, Board b){
        super(initBalance, b);
        name = n;
        pieceFile = file;
        properties = new ArrayList<>();
        cards = new ArrayList<>();
    }

    //player mortgages p, returns false if method fails
    public boolean mortgage(Property p){
        return p.mortgage();
    }

    //player unmortgages p, returns false if method fails
    public boolean unmortgage(Property p){
        return p.unmortgage();
    }

//    //player builds house on cp, returns false if method fails
//    boolean buildHouse(ColorProperty cp){
//        return false;
//    }
//
//    //player builds hotel on cp, returns false if method fails
//    boolean buildHotel(ColorProperty cp){
//        return false;
//    }
//
//    //player sells house on cp, returns false if method fails
//    boolean sellHouse(ColorProperty cp){
//        return false;
//    }
//
//    //player sells hotel on cp, returns false if method fails
//    boolean sellHotel(ColorProperty cp){
//        return false;
//    }

    //player sells p to bank, returns false if method fails
    boolean sell(Property p){
        return p.sellToBank();
    }

    //player sells hc to bank, returns false if method fails
    boolean sell(HoldableCard hc){
        return hc.sellToBank();
    }


    public int getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(int newSpace){
        currentSpace = newSpace;
    }
}