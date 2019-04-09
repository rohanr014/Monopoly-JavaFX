package app.engine.board;

import app.engine.Dice;
import app.engine.GameSetup;
import app.engine.agent.Agent;
import app.engine.agent.Bank;
import app.engine.agent.Player;
import app.engine.card.Card;
import app.engine.space.Space;

import java.util.*;

public class Board implements IBoardObservable{
    private Collection<Card> communityChest;
    private Collection<Card> chanceCards;
    private List<Space> spaces;
    private Queue<Player> players;
    private Bank bank;
    private List<Dice> gameDice;
    private int doublesCounter;
    private int[] lastRoll;

    //dice types?

    public Board(String propfile){
        GameSetup setup = new GameSetup(propfile);

        communityChest = setup.getCommunityChest();
        chanceCards = setup.getChanceCards();
        players = setup.getPlayers();
        spaces = Collections.unmodifiableList(setup.getSpaces());
        initializeSpaces();
        gameDice = setup.getDice();
        bank = setup.getBank();
    }

    private void initializeSpaces() {
        for (Space s: spaces){
            s.initializeSpace(this);
        }
    }

    public void startTurn(){
        Player p = players.poll();

        lastRoll = gameDice.get(0).rollAllDice();
        move(p, getLastRollSum());

        players.add(p);

    }

    public void endTurn(){


        doublesCounter = 0;



        startTurn();
    }


    /**
     * Function to move player to specific space
     */

    public void move(Player p, Space s){
        var start = p.getCurrentSpace();
        int spacePosition = spaces.indexOf(s);
        p.setCurrentSpace(spacePosition);
        spaces.get(start).removeFromCurrentOccupants(p);
        if (checkForGo(start, spacePosition)){
//            MAGIC VALUE
            bank.giveMoney(p, 200);
        }
        s.onLand(p);

    }

    /**
     * Function to move player forward a specific number of
     * steps
     */

    public void move(Player p, int steps){
        var end = p.getCurrentSpace() + steps;
        move(p, spaces.get(end));
    }

    private boolean checkForGo(int start, int spacePosition) {
        return checkIfPass(start, spacePosition, getGoIndex());
    }

    private boolean checkIfPass(int start, int end, int targetSpaceIndex) {
        return ((targetSpaceIndex <= end && targetSpaceIndex > start) || (start > end));
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

    public boolean removePlayer(Player p){
        return players.remove(p);
    }


    public double getSellPrice(double purchaseCost) {
        return purchaseCost / getSellToBankModifier();
    }

    private double getSellToBankModifier() {
        //GETS BANK MODIFIER FROM Properties file
        return -1;
    }

    private int getGoIndex() {
        return -1;
    }

    public int[] getLastRollArray() {
        return lastRoll;
    }

    public int getLastRollSum() {
        int sum = 0;
        for (int x: lastRoll){
            sum += x;
        }
        return sum;
    }

    @Override
    public void addBoardObserver(IBoardObserver o) {

    }

    @Override
    public void removeBoardObserver(IBoardObserver o) {

    }

    @Override
    public void notifyBoardObservers() {

    }
}