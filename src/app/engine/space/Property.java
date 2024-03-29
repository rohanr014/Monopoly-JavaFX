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
    private double housePrice;
    private int hotels;
    private double hotelPrice;


    //mostly for utilities

    public Property(String name, double purchaseCost, double mortgageValue, double houseCost, double hotelCost) {
        super(name);
        this.purchaseCost = purchaseCost;
        this.mortgageValue = mortgageValue;
        housePrice = houseCost;
        hotelPrice = hotelCost;

    }

    //for every other property
    public Property(String name, double purchaseCost, double mortgageValue, double rent, double houseCost, double hotelCost) {
        this(name, purchaseCost, mortgageValue, houseCost, hotelCost);
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
            System.out.println(p.getWallet() + ": " + getRent());
            chargeRent(p);
            System.out.println(p.getWallet());
        }
    }

    @Override
    public void notifySpaceObservers(){
        System.out.println(getMySpaceObserverList().size());
        for(ISpaceObserver spaceObserver : getMySpaceObserverList()){
            spaceObserver.offerPopUp();
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

    public double getHotelCost() {
        return hotelPrice;
    }

    public double getHouseCost() {
        return housePrice;
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