package app.engine.agent;

import app.engine.board.Board;
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
    private int currentSpace;
    private String logAction;


    public Player(String playerName, String file, double initBalance, Board board){
        super(initBalance, board);
        name = playerName;
        pieceFile = file;
        properties = new ArrayList<>();
        cards = new ArrayList<>();
        currentSpace = 0;
    }

    //player mortgages p, returns false if method fails
    public boolean mortgage(Property property){
        if (property.mortgage()) {
            logAction = name + " mortgaged " + property.getMyName() + ".";
            return true;
        }
        return false;
    }

    //player unmortgages p, returns false if method fails
    public boolean unmortgage(Property property){
        if (property.unmortgage()) {
            logAction = name + " unmortgaged " + property.getMyName() + ".";
        }
        return false;
    }

    //player builds house on cp, returns false if method fails
    boolean buildHouse(ColorProperty colorProp){
        if (colorProp.buildHouse()) {
            logAction = name + " built a house on " + colorProp.getMyName() + ".";
        }
        return false;
    }

    //player builds hotel on cp, returns false if method fails
    boolean buildHotel(ColorProperty colorProp){
        if (colorProp.buildHotel()) {
            logAction = name + " built a hotel on " + colorProp.getMyName() + ".";
        }
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
}