package app.engine.board;

import app.engine.Config.GameFileHandler;
import app.engine.dice.Dice;
import app.engine.Config.GameSetup;
import app.engine.agent.Agent;
import app.engine.agent.Bank;
import app.engine.agent.Player;
import app.engine.card.Card;
import app.engine.space.Space;

import java.io.IOException;
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

    private List<IBoardObserver> myObserverList;
    //dice types?

    public Board(String directory, String filename) throws IOException {
        this(GameFileHandler.getGamedata(directory, filename));
    }

    public Board(ResourceBundle propertyFile){
        GameSetup setup = new GameSetup(propertyFile, this);
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

        if (player.isInJail()){
//            Prompt player to pay JAIL_FEE, use Get Out Of Jail Free Card, or Roll for doubles
//            if they fail to roll doubles and player.getNumTurnsInJail()<MAX_JAIL_TURNS (i.e. 3)
//            then they stay in jail. If player.getNumTurnsInJail()>=MAX_JAIL_TURNS, then they leave jail
//            and are forced to pay the JAIL_FEE
        }

        players.add(player);
    }


    public int[] rollDice(Player player){
        lastRoll = gameDice.get(0).rollAllDice();
        if (player.isInJail()) {
            handleJailRolls(player);
        } else {
            move(player, getLastRollSum());
        }
        return lastRoll;
    }

    private void handleJailRolls(Player player) {
        if (isDoubles(lastRoll)) {
            move(player, getLastRollSum());
        }
    }

//    works for any number of die. Just checks if all die rolled are the same or not.
    private boolean isDoubles(int[] lastRoll) {
        for (int i = 0; i < lastRoll.length-1; i++){
            if (lastRoll[i]!=lastRoll[i+1]){
                return false;
            }
        }
        return true;
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

    public boolean isJail(Space space) {
        return getSpaceIndex(space)==getJailIndex();
    }

    private int getSpaceIndex(Space space) {
        return spaces.indexOf(space);
    }

    public int getLastRollSum() {
        int sum = 0;
        for (int x: lastRoll){
            sum += x;
        }
        return sum;
    }



    /////////////////////
    ///BELOW: METHODS THAT PULL FROM PROPERTIES FILE
    /////////////////////

    private double getSellToBankModifier() {
        //GETS BANK MODIFIER FROM Properties file
        return -1;
    }

    private int getGoIndex() {
//        gets Index of go in spaces from properties file (default is 0)
        return -1;
    }

    private int getJailIndex() {
        return -1;
    }



    /////////////////////
    ///BELOW: OBSERVER METHODS FOR VIEW
    /////////////////////

    @Override
    public void addBoardObserver(IBoardObserver o) {
        myObserverList.add(o);

    }

    @Override
    public void removeBoardObserver(IBoardObserver o) {
        myObserverList.remove(o);

    }

    @Override
    public void notifyBoardObservers() {
        for(IBoardObserver o : myObserverList){
            o.boardUpdate();
        }

    }



    /////////////////////
    ///BELOW: BASIC GETTERS
    /////////////////////

    public Bank getBank() {
        return bank;
    }

    public int[] getLastRollArray() {
        return lastRoll;
    }

    public Queue<Player> getPlayers() {
        return players;
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public Collection<Card> getChanceCards() {
        return chanceCards;
    }

    public Collection<Card> getCommunityChest() {
        return communityChest;
    }

    public List<Dice> getGameDice() {
        return gameDice;
    }

    public int getDoublesCounter() {
        return doublesCounter;
    }




}