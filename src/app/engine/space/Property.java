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
    private Board board;
    private boolean privateProperty;
    private boolean mortgaged;


    public Property(double purchaseCost, double mortgageValue, Board board) {
        this.purchaseCost = purchaseCost;
        this.mortgageValue = mortgageValue;
        this.board = board;
        owner = Board.getBank();
    }


    public Property(double purchaseCost, double mortgageValue, double rent, Board board) {
        this(purchaseCost, mortgageValue, board);
        this.rent = rent;
    }

    /**
     * Function to check if property bought by a player
     *
     * @param p player being checked
     * @return true if app.controller.Engine.Player p owns it, false otherwise
     */
    public boolean boughtBy(Player p) {
        p.giveMoney(owner, purchaseCost);
        privateProperty = true;
        return ownershipSwitchedTo(p);
    }


    /**
     * Function to mortgage property
     *
     * @return true if successful, false otherwise
     */
    public boolean mortgage() {
        mortgaged = true;
        rent = 0;
        return ownershipSwitchedTo(Board.getBank());
    }

    public boolean unmortgage() {
        mortgaged = false;
        rent = calculateRent();
        return ownershipSwitchedTo(Board.getBank());
    }


    /**
     * Function to sell property back to bank
     *
     * @return true if succesful, false otherwise
     */
    public boolean sellToBank(){
        return false;
    }


    protected boolean ownershipSwitchedTo(Agent a){
        if (board.contains(a)){
            owner = a;
            return true;
        }
        return false;
    }


    @Override
    public boolean onLand(Player p) {
        if (privateProperty && !p.equals(owner)){
            return chargeRent(p);
        }
    }

    public boolean isMortgaged(){
        return mortgaged;
    }




    private double calculateRent() {
    }

    public boolean chargeRent(Player p) {
        return p.giveMoney(owner, rent);
    }

    protected boolean setRent(double newRent){

    }

    public Agent getOwner() {
        return owner;
    }
}