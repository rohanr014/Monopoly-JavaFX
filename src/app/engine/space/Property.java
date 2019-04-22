package app.engine.space;

import app.engine.agent.Agent;
import app.engine.Asset;
import app.engine.board.Board;
import app.engine.agent.Player;

public class Property extends Space implements Asset {
    private double purchaseCost;
    private double rent;
    private double mortgageValue;
    private Agent owner;
    private boolean ownedByPlayer;
    private boolean mortgaged;
    private double unmortgageValue;
    private int houses;
    private int hotels;
    private double buildCost;


    public Property(String name, double purchaseCost, double mortgageValue) {
        super(name);
        this.purchaseCost = purchaseCost;
        this.mortgageValue = mortgageValue;
    }


    //mostly for utilities

    public Property(String name, double purchaseCost, double mortgageValue, double buildCost) {
        super(name);
        this.purchaseCost = purchaseCost;
        this.mortgageValue = mortgageValue;
        this.buildCost = buildCost;
    }

    //for every other property
    public Property(String name, double purchaseCost, double mortgageValue, double rent, double buildCost) {
        this(name, purchaseCost, mortgageValue, buildCost);
        this.rent = rent;
    }

    @Override
    public void initializeSpace(Board b){
        super.initializeSpace(b);
        owner = b.getBank();
    }
    /**
     * Function to check if property bought by a player
     *
     * @param p player being checked
     * @return true if app.controller.Engine.Player p owns it, false otherwise
     */
    public boolean boughtBy(Player p) {
        //should properties handle money transactions?
        //System.print
        if (p.giveMoney(owner, purchaseCost)) {
            ownedByPlayer = true;
            getBoard().notifyPurchase();
            return ownershipSwitchedTo(p);
        }
        return false;
    }


    /**
     * Function to mortgage property
     *
     * @return true if successful, false otherwise
     */
    public boolean mortgage() {
        boolean success = getBoard().getBank().giveMoney(owner, mortgageValue);
        if (success) {
            mortgaged = true;
            rent = 0;
        }
        return success;
    }

    public boolean unmortgage() {
        boolean success = owner.giveMoney(getBoard().getBank(), mortgageValue*getBoard().getRules().getUnmortgageMultiplier());
        if (success) {
            mortgaged = false;
            rent = calculateRent();
        }
        return success;
    }

    protected double calculateRent() {
        return getRent();
    }


    /**
     * Function to sell property back to bank
     *
     * @return true if succesful, false otherwise
     */
    public boolean sellToBank(Player player) {
        return getBoard().getBank().giveMoney(player, getValue()) && ownershipSwitchedTo(getBoard().getBank());
    }
    
    public double getValue() {
        return getPurchaseCost() / getBoard().getRules().getSellToBankMultiplier();
    }


    public boolean ownershipSwitchedTo(Agent a){
        if (getBoard().contains(a)){
            owner.removeProperty(this);
            a.addProperty(this);
            owner = a;
            return true;

        }
        return false;
    }

    @Override
    protected void invokeAction(Player p) {
        if (!ownedByPlayer){
            notifySpaceObservers();
        }
        else if (ownedByPlayer && !p.equals(owner)){
            chargeRent(p);
        }
    }

    @Override
    public void notifySpaceObservers(){
        for(ISpaceObserver spaceObserver : getMySpaceObserverList()){
            spaceObserver.offerPopUp();
            spaceObserver.spaceUpdate();
        }
    }

//    public void notifyPurchase() {
//        String purchaseNotification = getOwner().getName() + " bought " + getName() + ".";
//        for(ISpaceObserver spaceObserver : getMySpaceObserverList()){
//            spaceObserver.logPurchase(purchaseNotification);
//        }
//    }

    protected double getRent() {
        return rent;
    }

    public boolean chargeRent(Player p) {
        return p.giveMoney(owner, getRent());
    }

    protected boolean setRent(double newRent){
        if (newRent<0){
            return false;
        }

        rent = newRent;
        return true;
    }

    public Agent getOwner() {
        return owner;
    }

    public boolean isMortgaged(){
        return mortgaged;
    }

    public double getMortgageValue() {
        return mortgageValue;
    }

    public double getUnmortgageValue() {
        return unmortgageValue;
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public int getHouses() {
        return houses;
    }

    public int getHotels() {
        return hotels;
    }

    public void setHouses(int amount) {houses=amount;}

    public void setHotels(int amount) {hotels=amount;}

    public double getBuildCost() {
        return buildCost;
    }

    @Deprecated
    public double getHotelCost() {
        return buildCost;
    }

    @Deprecated
    public double getHouseCost() {
        return buildCost;
    }

    public boolean hasBuildings() {
        return (getHouses() > 0 || getHotels() >0);
    }

//    @Override
//    public boolean sellToBank(Player player) {
//        return false;
//    }
//
//    @Override
//    public double getValue() {
//        return 0;
//    }
}