package app.engine.agent;

import app.engine.card.Card;
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

//    MAGIC VALUES
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
        if (colorProp.sellHouse()) {
            logAction = name + " sold a house on " + colorProp.getMyName() + ".";
        }
        return false;
    }

    //player sells hotel on cp, returns false if method fails
    boolean sellHotel(ColorProperty colorProp){
        if (colorProp.sellHotel()) {
            logAction = name + " sold a hotel on " + colorProp.getMyName() + ".";
        }
        return false;
    }

    //player sells p to bank, returns false if method fails
    boolean sell(Property property){
        return property.sellToBank(this);
    }

    //player sells hc to bank, returns false if method fails
    boolean sell(HoldableCard holdableCard){
        return holdableCard.sellToBank(this);
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

    private void setIsInJail(boolean b) {
        inJail = b;
        numTurnsInJail = 0;
    }

    public void addNumTurnsInJail(){
        numTurnsInJail++;
    }

    public int getNumTurnsInJail() {
        return numTurnsInJail;
    }



    public void goToJail() {
        setIsInJail(true);
    }

    public void dummy(int argument, int argument2){
        System.out.println("Dummy worked and printed out " + argument + " along with " + argument2);
    }

    public void leaveJail() {
        setIsInJail(false);
    }

    public HoldableCard findGetOutOfJailCard() {
        for (HoldableCard card: cards){
            if (card.getFuncName().equals("leaveJail")){
                return card;
            }
        }
        return null;
    }

    public boolean useGetOutOfJailCard(){
        var card = findGetOutOfJailCard();
        if (card == null) {
            return false;
        }
        card.useCard(this);
        removeCard(card);
        return true;
    }

    public void addCard(HoldableCard holdableCard) {
        cards.add(holdableCard);
    }

    public void removeCard(HoldableCard holdableCard) {
        cards.remove(holdableCard);
    }
}