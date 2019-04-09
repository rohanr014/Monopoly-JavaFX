package app.engine.board;

import app.Dice.Dice;
import app.engine.GameSetup.GameSetup;
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
        GameSetup setup = new GameSetup(propfile, this);

        communityChest = setup.getCommunityChest();
        chanceCards = setup.getChanceCards();
        players = setup.getPlayers();
        spaces = Collections.unmodifiableList(setup.getSpaces());
        initializeSpaces();
        gameDice = setup.getDice();
        bank = setup.getBank();
    }

    private void initializeSpaces() {
        for (Space space: spaces){
            space.initializeSpace(this);
        }
    }

    public void startTurn() {
        Player player = players.poll();

        lastRoll = gameDice.get(0).rollAllDice();
        move(player, getLastRollSum());

        players.add(player);

    }

    public void endTurn() {
        doublesCounter = 0;
        startTurn();
    }


    /**
     * Function to move player to specific space
     */

    public void move(Player player, Space destination) {
        var start = player.getCurrentSpace();
        int spacePosition = spaces.indexOf(destination);
        player.setCurrentSpace(spacePosition);
        spaces.get(start).removeFromCurrentOccupants(player);
        if (checkForGo(start, spacePosition)){
//            MAGIC VALUE
            bank.giveMoney(player, 200);
        }
        destination.onLand(player);
    }

    /**
     * Function to move player forward a specific number of
     * steps
     */

    public void move(Player player, int steps){
        var end = player.getCurrentSpace() + steps;
        move(player, spaces.get(end));
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

    public boolean contains(Agent agent) {
        if (agent.equals(bank)) {
            return true;
        }
        for (Player player: players){
            if (agent.equals(player)){
                return true;
            }
        }
        return false;
    }

    public boolean removePlayer(Player player){
        return players.remove(player);
    }


    public double getSellPrice(double purchaseCost) {
        return purchaseCost / getSellToBankModifier();
    }

    private double getSellToBankModifier() {
        //GETS BANK MODIFIER FROM Properties file
        return -1;
    }

    private int getGoIndex() {
//        gets Index of go in spaces from properties file (default is 0)
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