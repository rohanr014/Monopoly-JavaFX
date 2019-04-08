package app.engine.board;

import app.engine.agent.Agent;
import app.engine.agent.Bank;
import app.engine.agent.Player;
import app.engine.card.Card;
import app.engine.space.Space;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class Board implements IBoardObservable{
    private Collection<Card> communityChest;
    private Collection<Card> chanceCards;
    private Space[] spaces;
    private Collection<Player> players;
    private Bank bank;
    private int[] dice;

    //dice types?

    public Board(int numSpaces, int[] dice, Bank bank){
        communityChest = new Stack<>();
        chanceCards = new Stack<>();
        players = new ArrayList<>();
        spaces = new Space[numSpaces];
        this.dice = dice;
        this.bank = bank;
    }

    /**
     * Function to give the next player their turn
     */

    public void callNextPlayer(){

    }

    /**
     * Function to move player to specific space
     */

    public void move(Player p, Space s){

    }

    /**
     * Function to move player forward a specific number of
     * steps
     */

    public void move(Player p, int steps){

    }

    public Bank getBank() {
        return bank;
    }

    public boolean contains(Agent a) {
        if (a.equals(bank)) {
            return true;
        }
        for (Player p: players){
            if (a.equals(p)){
                return true;
            }
        }
        return false;
    }

    public boolean addPlayer(Player p){
        return players.add(p);
    }

    public boolean removePlayer(Player p){
        return players.remove(p);
    }

    //functions required for observer pattern
    @Override
    public void addObserver(IBoardObserver o) {

    }

    @Override
    public void removeObserver(IBoardObserver o) {

    }

    @Override
    public void notifyObservers() {

    }


    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public double getSellPrice(double purchaseCost) {
    }
}