package app.engine.board;

import app.engine.Config.GameFileHandler;
import app.engine.dice.Dice;
import app.engine.Config.GameSetup;
import app.engine.agent.Agent;
import app.engine.agent.Bank;
import app.engine.agent.Player;
import app.engine.card.Card;
import app.engine.space.Property;
import app.engine.space.Space;

import java.io.IOException;
import java.util.*;

public class Board implements IBoardObservable{
    private Collection<Card> communityChest;
    private Collection<Card> chanceCards;
    private List<Space> spaces;
    private Queue<Player> players;
    private Player currentPlayer;
    private Bank bank;
    private List<Dice> gameDice;
    private int doublesCounter;
    private int[] lastRoll;

    private List<IBoardObserver> myObserverList;
    private Player winner = null;

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
        System.out.println(players.poll().getName());
    }

    private void initializeSpaces() {
        for (Space space: spaces) {
            space.initializeSpace(this);
        }
    }

    public Player startTurn() {
        currentPlayer = players.poll();
        players.add(currentPlayer);
        return currentPlayer;
    }

    public void endTurn() {
        doublesCounter = 0;
        lastRoll = null;
        handleBankruptcy(currentPlayer);
        checkWin();
        startTurn();
    }

    /**
     * Function to move player to specific space
     */

    public void move(Player player, Space destination) {
        if (player.isInJail()){
            return;
        }
        var start = player.getCurrentSpace();
        int spacePosition = spaces.indexOf(destination);
        player.setCurrentSpace(spacePosition);
        spaces.get(start).removeFromCurrentOccupants(player);
        if (checkForGo(start, spacePosition)){
//            MAGIC VALUE
            bank.giveMoney(player, getGoMoney());
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

    /////////////////////
    ///BELOW: DICE-RELATED METHODS
    /////////////////////

    public void rollDice(Player player){
        lastRoll = gameDice.get(0).rollAllDice();

        if (player.isInJail()) {
            handleJailRolls(player);
        } else {
            if (isDoubles(lastRoll)){
                doublesCounter++;
            }
            move(player, getLastRollSum());
        }
        notifyBoardObservers();
        //return lastRoll;
    }


    //    works for any number of die. Just checks if all die rolled are the same or not.
    private boolean isDoubles(int[] lastRoll) {
        for (int i = 0; i < lastRoll.length-1; i++){
            if (lastRoll[i]!=lastRoll[i+1]){
                return false;
            }
        }
//        MAGIC VALUE
        if (doublesCounter==getNumDoublesTilGoToJail()){
            currentPlayer.goToJail();
        }
        return true;
    }

    /////////////////////
    ///BELOW: JAIL METHODS
    /////////////////////

    public void payJailFee(Player player){
        player.giveMoney(bank, getJailFee());
        player.leaveJail();
    }

    private void handleJailRolls(Player player) {

        if (isDoubles(lastRoll)) {
            player.leaveJail();
            move(player, getLastRollSum());
        } else if (player.getNumTurnsInJail()>=getMaxTurnsInJail()){
            payJailFee(player);
            move(player, getLastRollSum());
        }
    }

    /////////////////////
    ///BELOW: BANKRUPTCY/WIN-CONDITION METHODS
    /////////////////////

    private boolean handleBankruptcy(Player player) {
        if (player.getWallet()<0){
            removePlayer(player);
            return true;
        }
        return false;
    }

    private boolean checkWin() {
        if (players.size()==1){
            winner = players.poll();
            return true;
        }
        return false;
    }

    /////////////////////
    ///BELOW: VARIOUS USEFUL METHODS
    /////////////////////

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

    private boolean removePlayer(Player player){
        return players.remove(player);
    }

    //prob could be refactored into Property?
    public double getSellPrice(double purchaseCost) {
        return purchaseCost / getSellToBankMultiplier();
    }

    public boolean isJail(Space space) {
        return getSpaceIndex(space)==getJailIndex();
    }

    private int getSpaceIndex(Space space) {
        return spaces.indexOf(space);
    }

//    /seems like maybe should be in a dice class? Maybe a static dice helper class?
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

    private int getNumDoublesTilGoToJail() {
        return 3;
    }

    private double getGoMoney() {
//        REPLACE WITH PROP FILE VALUES
        return 200;
    }

    private double getSellToBankMultiplier() {
        //GETS BANK MODIFIER FROM Properties file
        return .5;
    }

    private int getGoIndex() {
//        gets Index of go in spaces from properties file (default is 0)
        return 0;
    }

    private int getJailIndex() {
        return 10;
    }

    public double getUnmortgageMultiplier() {
        return 1.1;
    }

    private double getJailFee() { return 50;
    }

    private int getMaxTurnsInJail() {
        return 3;
    }

    /////////////////////
    ///BELOW: CanDoXXX() METHODS, for Controller in determining whether certain buttons are pressable
    /////////////////////


    public boolean canEndTurn(Player player){
        return (lastRoll != null);
    }

    //    This could be a Player method, but some of the other CanDoXX() methods can't be in player so for now I'm keeping them together
    public boolean canSell(Player player){
        return (!player.getProperties().isEmpty() || !player.getCards().isEmpty() || player.hasBuildings());
    }

    public boolean canMortgage(Player player){
        if (player.getProperties().isEmpty()){
            return false;
        }
        for (Property prop: player.getProperties()){
            if (!prop.isMortgaged()){
                return true;
            }
        }
        return false;
    }

    public boolean canUnmortgage(Player player){
        if (player.getProperties().isEmpty()){
            return false;
        }
        for (Property prop: player.getProperties()){
            if (prop.isMortgaged() && player.getWallet()>=prop.getUnmortgageValue()){
                return true;
            }
        }
        return false;
    }

    public boolean canRoll(Player player){
        if (lastRoll == null){
            return true;
        }
//        MAGIC VALUE
        return (doublesCounter>0 && doublesCounter < 3 && isDoubles(lastRoll));
    }


    public boolean canBuy(Player player, Property prop){
        return (player.getWallet()>=prop.getPurchaseCost());
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }
}