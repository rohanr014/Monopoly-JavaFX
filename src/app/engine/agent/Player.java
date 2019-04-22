package app.engine.agent;

import app.engine.card.Card;
import app.engine.card.HoldableCard;
import app.engine.space.ColorProperty;
import app.engine.space.Property;

import java.util.ArrayList;
import java.util.List;

public class Player extends Bank{
    private String pieceFile;

    private List<HoldableCard> cards;
    private int currentSpace;
    private String logAction;

//    MAGIC VALUES
    private boolean inJail = false;
    private int numTurnsInJail = 0;


    public Player(String playerName, String file, double initBalance){
        super(initBalance, playerName);
        pieceFile = file + ".png";
        cards = new ArrayList<>();
    }

    //player mortgages p, returns false if method fails
    public boolean mortgage(Property property){
        if (property.mortgage()) {
            notifyAgentObservers(getName() + " mortgaged " + property.getMyName() + ".");
            return true;
        }
        return false;
    }

    //player unmortgages p, returns false if method fails
    public boolean unmortgage(Property property){
        if (property.unmortgage()) {
            notifyAgentObservers(getName() + " unmortgaged " + property.getMyName() + ".");
        }
        return false;
    }

    //player builds house on cp, returns false if method fails
    boolean buildHouse(ColorProperty colorProp){
        if (colorProp.buildHouse()) {
            notifyAgentObservers(getName() + " built a house on " + colorProp.getMyName() + ".");
        }
        return false;
    }

    //player builds hotel on cp, returns false if method fails
    boolean buildHotel(ColorProperty colorProp){
        if (colorProp.buildHotel()) {
            notifyAgentObservers(getName() + " built a hotel on " + colorProp.getMyName() + ".");
        }
        return false;
    }

    //player sells house on cp, returns false if method fails
    boolean sellHouse(ColorProperty colorProp){
        if (colorProp.sellHouse()) {
            notifyAgentObservers(getName() + " sold a house on " + colorProp.getMyName() + ".");
        }
        return false;
    }

    //player sells hotel on cp, returns false if method fails
    boolean sellHotel(ColorProperty colorProp){
        if (colorProp.sellHotel()) {
            notifyAgentObservers(getName() + " sold a hotel on " + colorProp.getMyName() + ".");
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

    public List<HoldableCard> getCards() {
        return cards;
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
        notifyAgentObservers(getName() + " is now in Jail.");
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

//    @Override
//    public boolean giveMoney(Agent agent, double m){
//        if (getWallet()<m) {
////            prob should throw something here? better than boolean?
//            return false;
//        }
//        if (agent == null) {
//            System.out.println("bruh");
//        }
//        agent.addToWallet(m);
//        setWallet(getWallet() - m);
//        return true;
////        MORE LOGIC FOR BANKRUPTING
//    }

    public void addCard(HoldableCard holdableCard) {
        cards.add(holdableCard);
    }

    public void removeCard(HoldableCard holdableCard) {
        cards.remove(holdableCard);
    }

    public boolean hasBuildings() {
        for (Property p: getProperties()){
            if (p.hasBuildings()){
                return true;
            }
        }
        return false;
    }
}