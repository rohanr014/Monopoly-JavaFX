package app.engine.board;

import app.engine.agent.Agent;
import app.engine.agent.Bank;
import app.engine.agent.Player;
import app.engine.card.Card;
import app.engine.space.Space;

import java.util.Collection;

public class Board implements IBoardObservable{
    private Bank bank;
    private Collection<Card> communityChest;
    private Collection<Card> chanceCards;
    private Collection<Space> spaces;
    private Collection<Player> players;
    //dice types?

    public Board(){

    }

    public Bank getBank() {
        return bank;
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

    public boolean contains(Agent a) {
    }
}