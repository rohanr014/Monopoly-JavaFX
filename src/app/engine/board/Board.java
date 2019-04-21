package app.engine.board;

import app.engine.Config.GameFileHandler;
import app.engine.dice.Dice;
import app.engine.Config.GameSetup;
import app.engine.agent.Agent;
import app.engine.agent.Bank;
import app.engine.agent.Player;
import app.engine.card.Card;
import app.engine.dice.IDiceObservable;
import app.engine.dice.IDiceObserver;
import app.engine.space.Space;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Collections;
import java.util.Collection;

public class Board implements IBoardObservable, IDiceObservable {

    private final ButtonPermissions buttonPermissions;
    private Queue<Card> communityChest;
    private Queue<Card> chanceCards;
    private List<Space> spaces;
    private Queue<Player> players;
    private ArrayList<Player> playersByTurn;
    private Player currentPlayer;
    private Bank bank;
    private List<Dice> gameDice;
    private int doublesCounter;
    private int[] lastRoll;
    private List<IDiceObserver> myDiceObserverList;
    private List<IBoardObserver> myObserverList;
    private Player winner = null;

    private boolean firstTurnTest = true;
    private final RulesInitializer rules;

    private List<Agent> myAgentList;

    public Board(String directory, String filename) throws IOException {
        this(GameFileHandler.getGamedata(directory, filename));
        myObserverList = new ArrayList<>();
        myAgentList = new ArrayList<>();
    }

    public Board(ResourceBundle propertyFile){
        GameSetup setup = new GameSetup(propertyFile, this);
        rules = setup.getRules();
        myObserverList = new ArrayList<>();
        myAgentList = new ArrayList<>();
        myDiceObserverList = new ArrayList<>();
        communityChest = setup.getCommunityChest();
        chanceCards = setup.getChanceCards();
        players = setup.getPlayers();
        playersByTurn = new ArrayList<>(players);
        spaces = Collections.unmodifiableList(setup.getSpaces());
        bank = setup.getBank();
        initializeSpaces();
        gameDice = setup.getDice();
        myAgentList.add(bank);
        myAgentList.addAll(playersByTurn);
        spaces.get(0).getCurrentOccupants().addAll(players);
        buttonPermissions = new ButtonPermissions(this);
        //System.out.println(players.poll().getName());
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
            bank.giveMoney(player, rules.getGoMoney());
        }
        destination.onLand(player);
        notifyBoardObservers(spaces.get(start), destination);
    }

    /**
     * Function to move player forward a specific number of
     * steps
     */

    public void move(Player player, int steps){
        var end = (player.getCurrentSpace() + steps)%40;
        move(player, spaces.get(end));
    }

    public void drawCard(Player player, Queue<Card> whichPile){
//        cards add themselves back to their piles
        var card = whichPile.poll();
//        setOriginPile() MUST be called before invokeAction()
        card.setOriginPile(whichPile);
        card.invokeAction(player);
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
                checkIfDoublesSendsToJail(player);
            }
//            if (!(firstTurnTest)) {
                move(player, getLastRollSum());
//            if (firstTurnTest) {
//                move(player, 7);
//                firstTurnTest = false;
//            }
        }
        notifyDiceObservers();
        //return lastRoll;
    }

    //    works for any number of die. Just checks if all die rolled are the same or not.
    public boolean isDoubles(int[] lastRoll) {
        for (int i = 0; i < lastRoll.length-1; i++){
            if (lastRoll[i]!=lastRoll[i+1]){
                return false;
            }
        }
        return true;
    }

    /////////////////////
    ///BELOW: JAIL METHODS
    /////////////////////

    public void payJailFee(Player player){
        player.giveMoney(bank, rules.getJailFee());
        player.leaveJail();
    }

    public boolean isJail(Space space) {
        return getSpaceIndex(space)==rules.getJailIndex();
    }

    private void handleJailRolls(Player player) {

        if (isDoubles(lastRoll)) {
            player.leaveJail();
            move(player, getLastRollSum());
        } else if (player.getNumTurnsInJail()>=rules.getMaxTurnsInJail()){
            payJailFee(player);
            move(player, getLastRollSum());
        }
    }

    private void checkIfDoublesSendsToJail(Player player) {
        if (doublesCounter==rules.getNumDoublesTilGoToJail()){
            player.goToJail();
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
        if (currentPlayer.isInJail() || start==rules.getGoIndex()){
            return false;
        }
        if (checkIfPass(start, spacePosition, rules.getGoIndex())){
            System.out.println("Passed go");
            return true;
        }
        return false;
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

    private int getSpaceIndex(Space space) {
        return spaces.indexOf(space);
    }

//    /seems like maybe should be in a dice class? Maybe a static dice helper class?
    public int getLastRollSum() {
        int sum = 0;
        for (int x: lastRoll){
            sum += x;
        }
        //return sum;
        return sum;
    }


    /////////////////////
    ///BELOW: OBSERVER METHODS FOR VIEW
    /////////////////////

    @Override
    public void addBoardObserver(IBoardObserver o) {
        this.myObserverList.add(o);

    }

    @Override
    public void removeBoardObserver(IBoardObserver o) {
        this.myObserverList.remove(o);

    }

    @Override
    public void notifyBoardObservers(){

    }

    public void notifyPurchase() {
        String purchaseNotification = currentPlayer.getName() + " bought " + spaces.get(currentPlayer.getCurrentSpace()).getName() + ".";
        for(IBoardObserver boardObserver : myObserverList){
            boardObserver.logPurchase(purchaseNotification);
        }
    }

    public void notifyBoardObservers(Space start, Space end) {
        for(IBoardObserver o : myObserverList){
            System.out.println("notify observers " + start.getName() + " " + end.getName());
            o.boardUpdate(start, end);
        }

    }


    public void addDiceObserver(IDiceObserver o){
        myDiceObserverList.add(o);
    }

    public void removeDiceObserver(IDiceObserver o){
        myDiceObserverList.remove(o);
    }

    public void notifyDiceObservers(){
        for(IDiceObserver diceObserver : myDiceObserverList){
            diceObserver.diceUpdate(lastRoll);
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

    public List<Player> getPlayersByTurn() {
        return playersByTurn;
    }

    public RulesInitializer getRules() {
        return rules;
    }

    public ButtonPermissions getButtonPermissions() {
        return buttonPermissions;
    }

    public List<Agent> getMyAgentList() {return myAgentList;}
}