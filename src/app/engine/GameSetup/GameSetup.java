package app.engine.GameSetup;

import app.Dice.Dice;
import app.engine.agent.Bank;
import app.engine.agent.InfiniteBank;
import app.engine.agent.Player;
import app.engine.board.Board;
import app.engine.card.Card;
import app.engine.space.*;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Queue;

public class GameSetup {

    private ResourceBundle myBundle;
    private ResourceBundle rulesBundle;

    private Queue<Player> players;
    private ArrayList<Space> spaces;

    private String gamePropFile;
    private String rulesPropFile;

    private Board myBoard;

    public GameSetup(String propFile, Board b) {
        myBundle = ResourceBundle.getBundle(propFile);
        rulesBundle = ResourceBundle.getBundle(myBundle.getString("rules_file"));
        myBoard = b;
        createPlayers();

    }

    private void createSpaces(){
        String spacesFile = myBundle.getString("spacesFile");
        ResourceBundle spacesBundle = ResourceBundle.getBundle(spacesFile);
        Enumeration<String> spaces = spacesBundle.getKeys();

        while(spaces.hasMoreElements()){
            String currentKey = spaces.nextElement();
            String[] currentValue = spacesBundle.getString(currentKey).split(",");

            Space currentSpace;

            if(currentValue[1].equalsIgnoreCase("CP")){
                currentSpace = makeCP(currentValue[2]);
            }
            else if(currentValue[1].equalsIgnoreCase("RR")){
                currentSpace = makeRR(currentValue[2], true);
            }
            else if(currentValue[1].equalsIgnoreCase("U")){
                currentSpace = makeRR(currentValue[2], false);
            }
            else if(currentValue[1].equalsIgnoreCase("CH")){
                currentSpace = makeMoney(currentValue[2]);
            }
            else if(currentValue[1].equalsIgnoreCase("CC")){
                currentSpace = makeMoney(currentValue[2]);
            }
            else if(currentValue[1].equalsIgnoreCase("MOV")){
                currentSpace = makeMoney(currentValue[2]);
            }
            else{
                currentSpace = makeMoney(currentValue[2]);
            }

        }

    }

    // TODO: ADD NAMES TO CONSTRUCTORS, FINISH WRITING PROPERTIES FILES FOR EACH SPACE, WRITE BANK AND DICE(?)

    private double[] stringsToDoubles(String[] strings){
        double[] toReturn = new double[strings.length];

        for(int i=0; i<strings.length; i++){
            toReturn[i] = Double.parseDouble(strings[i]);
        }

        return toReturn;
    }

    private Space makeCP(String propFile){
        ResourceBundle cpBundle = ResourceBundle.getBundle(propFile);

        double purchaseCost = Double.parseDouble(cpBundle.getString("salePrice"));
        double housePrice = Double.parseDouble(cpBundle.getString("housePrice"));
        double hotelPrice = Double.parseDouble(cpBundle.getString("hotelPrice"));
        double mortgageValue = Double.parseDouble(cpBundle.getString("mortgage"));

        String[] rentStrings = cpBundle.getString("rents").split(",");

        return new ColorProperty(purchaseCost, mortgageValue, stringsToDoubles(rentStrings), housePrice, hotelPrice);
    }

    private Space makeRR(String propFile, boolean isRailroad){
        ResourceBundle currentBundle = ResourceBundle.getBundle(propFile);

        double purchaseCost = Double.parseDouble(currentBundle.getString("salePrice"));
        double mortgageValue = Double.parseDouble(currentBundle.getString("mortgage"));
        String[] rentStrings = currentBundle.getString("rents").split(",");

        if(isRailroad) {
            return new Railroad(purchaseCost, mortgageValue, stringsToDoubles(rentStrings));
        }
        else{
            return new Utility(purchaseCost, mortgageValue, stringsToDoubles(rentStrings));
        }
    }

    private Space makeMoney(String propFile){
        ResourceBundle moneyBundle = ResourceBundle.getBundle(propFile);
        double moneyGiven = Double.parseDouble(moneyBundle.getString("money"));

        return new CommonSpace();
    }


    private void createPlayers () {
        double startingBalance = Double.parseDouble(rulesBundle.getString("startingBalance"));

        Enumeration<String> keys = myBundle.getKeys();

        while (keys.hasMoreElements()) {
            String nextElement = keys.nextElement();
            if (nextElement.startsWith("player")) {
                String value = myBundle.getString(nextElement);

                // create new player, associate piece with that player
                Player currentPlayer = new Player(value.split(",")[0], value.split(",")[1], startingBalance, myBoard);
                players.add(currentPlayer);

            }
        }
    }

    public Collection<Card> getCommunityChest() {
        return null;
    }

    public Collection<Card> getChanceCards() {
        return null;
    }

    public Queue<Player> getPlayers() {

        return players;
    }


    public List<Space> getSpaces () {
        return spaces;

    }

    public List<Dice> getDice () {
        String[] diceString = rulesBundle.getString("dice").split(",");

        int[] diceNumbers = new int[diceString.length];

        for(int i=0; i<diceString.length; i++){
            diceNumbers[i] = Integer.parseInt(diceString[i]);
        }

//        TEMP FIX
        var d = new Dice(diceNumbers);
        var list = new ArrayList<Dice>();
        list.add(d);
//        TEMP FIX

        return list;
    }

    public Bank getBank () {
        String bankString = myBundle.getString("bankBalance");

        if(bankString.equals("infinite")){
            return new InfiniteBank(myBoard);
        }

        else{
            return new Bank(Double.parseDouble(bankString), myBoard);
        }

    }

}

