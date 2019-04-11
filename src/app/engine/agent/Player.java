package app.engine.agent;

import app.engine.card.HoldableCard;
import app.engine.space.ColorProperty;
import app.engine.space.Property;

import java.util.ArrayList;
import java.util.List;

public class Player extends Bank{
    private String name;
    private String pieceFile;
    private List<Property> properties;
    private List<HoldableCard> cards;
    private int currentSpace = 0;
    private boolean inJail = false;
    private int numTurnsInJail = 0;


    public Player(String playerName, String file, double initBalance){
        super(initBalance);
        name = playerName;
        pieceFile = file;
        properties = new ArrayList<>();
        cards = new ArrayList<>();
    }

    //player mortgages p, returns false if method fails
    public boolean mortgage(Property property){
        return property.mortgage();
    }

    //player unmortgages p, returns false if method fails
    public boolean unmortgage(Property property){
        return property.unmortgage();
    }

    //player builds house on cp, returns false if method fails
    boolean buildHouse(ColorProperty colorProp){
        return false;
    }

    //player builds hotel on cp, returns false if method fails
    boolean buildHotel(ColorProperty colorProp){
        return false;
    }

    //player sells house on cp, returns false if method fails
    boolean sellHouse(ColorProperty colorProp){
        return false;
    }

    //player sells hotel on cp, returns false if method fails
    boolean sellHotel(ColorProperty colorProp){
        return false;
    }

    //player sells p to bank, returns false if method fails
    boolean sell(Property property){
        return property.sellToBank();
    }

    //player sells hc to bank, returns false if method fails
    boolean sell(HoldableCard holdableCard){
        return holdableCard.sellToBank();
    }


    public int getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(int newSpace){
        currentSpace = newSpace;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public List<HoldableCard> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public String getPieceFile() {
        return pieceFile;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setIsInJail(boolean b) {
        inJail = b;
        numTurnsInJail = 0;
    }

    public void addToTurnsInJail(){
        numTurnsInJail++;
    }

    public int getNumTurnsInJail() {
        return numTurnsInJail;
    }
}