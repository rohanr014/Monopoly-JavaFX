package app.engine.space;

import app.engine.agent.Agent;
import app.engine.Asset;
import app.engine.agent.Bank;
import app.engine.board.Board;
import app.engine.agent.Player;

public class Property extends Space implements Asset {
    private double purchaseCost;
    private double rent;
    private double mortgageValue;
    private Agent owner;
    private Board board;
    private boolean ownedByPlayer;
    private boolean mortgaged;
    private double unmortgageValue;


    //mostly for utilities
    public Property(double purchasePrice, double mortgageVal) {
        purchaseCost = purchasePrice;
        mortgageValue = mortgageVal;
        unmortgageValue = mortgageValue*board.getUnmortgageMultiplier();
    }

    //for every other property
    public Property(double purchaseCost, double mortgageValue, double propertyRent) {
        this(purchaseCost, mortgageValue);
        rent = propertyRent;
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
        p.giveMoney(owner, purchaseCost);

        ownedByPlayer = true;
        return ownershipSwitchedTo(p);
    }


    /**
     * Function to mortgage property
     *
     * @return true if successful, false otherwise
     */
    public boolean mortgage() {
        boolean success = board.getBank().giveMoney(owner, mortgageValue);
        if (success) {
            mortgaged = true;
            rent = 0;
        }
        return success;
    }

    public boolean unmortgage() {
        boolean success = owner.giveMoney(board.getBank(), mortgageValue*board.getUnmortgageMultiplier());
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
    public boolean sellToBank(){
        Bank bank = board.getBank();
        return bank.giveMoney(owner, board.getSellPrice(purchaseCost)) && ownershipSwitchedTo(bank);
    }


    protected boolean ownershipSwitchedTo(Agent a){
        if (board.contains(a)){
            owner = a;
            return true;
        }
        return false;
    }

    @Override
    protected void invokeAction(Player p) {
        if (!ownedByPlayer){
            //trigger prompt for player to buy
        }
        else if (ownedByPlayer && !p.equals(owner)){
            chargeRent(p);
        }
    }

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

    public Board getBoard() {
        return board;
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

}