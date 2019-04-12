package app.engine.Config;

import app.engine.card.*;
import app.engine.dice.Dice;
import app.engine.agent.Bank;
import app.engine.agent.InfiniteBank;
import app.engine.agent.Player;
import app.engine.board.Board;
import app.engine.space.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class GameSetup {

    private static final String MODE_KEY = "prop_file";
    private static final String RULES_KEY = "rules_file";
    private static final String COMMUNITY_KEY = "communityCards";
    private static final String CHANCE_KEY = "chanceCards";

    private ResourceBundle highBundle;
    private ResourceBundle myBundle;
    private ResourceBundle rulesBundle;

    private Queue<Player> players;
    private List<Space> spaces;
    private List<Card> communityChest;
    private List<Card> chance;

    private String gamePropFile;
    private String rulesPropFile;

    private Board myBoard;

    public GameSetup(String directory, String filename, Board board) throws IOException {
        this(GameFileHandler.getGamedata(directory, filename), board);
    }

    public GameSetup(ResourceBundle bundle, Board b) {
        highBundle = bundle;
        myBundle = ResourceBundle.getBundle(highBundle.getString(MODE_KEY));
        rulesBundle = ResourceBundle.getBundle(highBundle.getString(RULES_KEY));
        myBoard = b;

        players = new LinkedList<Player>();
        spaces = new ArrayList<Space>();
        communityChest = makePerkCards(COMMUNITY_KEY);
        chance = makePerkCards(CHANCE_KEY);

        createPlayers();
        createSpaces();

        // use communityCards and chanceCards as arguments for makePerkCards

    }

    private String[] getSpaceKeys(ResourceBundle spacesBundle){
        int size = spacesBundle.keySet().size();

        String[] inOrder = new String[size];

        for(int i=0; i<size; i++){
            String currentString = "space" + i;
            inOrder[i] = currentString;
        }

        return inOrder;
    }


    private void createSpaces(){
        String spacesFile = myBundle.getString("spacesFile");
        ResourceBundle spacesBundle = ResourceBundle.getBundle(spacesFile);
        String[] spacesKeys = getSpaceKeys(spacesBundle);

        for(String currentKey: spacesKeys){

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
//            else if(currentValue[1].equalsIgnoreCase("CH")){
//                do stuff
//            }
//            else if(currentValue[1].equalsIgnoreCase("CC")){
//                do stuff
//            }
//            else if(currentValue[1].equalsIgnoreCase("MOV")){
//                move!
//            }
            else{
                currentSpace = new CommonSpace(currentValue[0]);

            }

            spaces.add(currentSpace);

        }

    }

    private double[] stringsToDoubles(String[] strings){
        double[] toReturn = new double[strings.length];

        for(int i=0; i<strings.length; i++){
            toReturn[i] = Double.parseDouble(strings[i]);
        }

        return toReturn;
    }

    private Space makeCP(String propFile){
        ResourceBundle cpBundle = ResourceBundle.getBundle(propFile);

        String name = cpBundle.getString("name");

        double purchaseCost = Double.parseDouble(cpBundle.getString("salePrice"));
        double housePrice = Double.parseDouble(cpBundle.getString("housePrice"));
        double hotelPrice = Double.parseDouble(cpBundle.getString("hotelPrice"));
        double mortgageValue = Double.parseDouble(cpBundle.getString("mortgage"));

        String[] rentStrings = cpBundle.getString("rents").split(",");
        //System.out.println(name);

        return new ColorProperty(name, purchaseCost, mortgageValue, stringsToDoubles(rentStrings), housePrice, hotelPrice);
    }

    private Space makeRR(String propFile, boolean isRailroad){
        ResourceBundle currentBundle = ResourceBundle.getBundle(propFile);

        String name = currentBundle.getString("name");
        double purchaseCost = Double.parseDouble(currentBundle.getString("salePrice"));
        double mortgageValue = Double.parseDouble(currentBundle.getString("mortgage"));
        String[] rentStrings = currentBundle.getString("rents").split(",");

        if(isRailroad) {
            return new Railroad(name, purchaseCost, mortgageValue, stringsToDoubles(rentStrings));
        }
        else{
            return new Utility(name, purchaseCost, mortgageValue, stringsToDoubles(rentStrings));
        }
    }

    private Space makeMoney(String propFile){
        ResourceBundle moneyBundle = ResourceBundle.getBundle(propFile);

        String name = moneyBundle.getString("name");
        double moneyGiven = Double.parseDouble(moneyBundle.getString("money"));


        return new CommonSpace(name);

    }


    private void createPlayers () {
        double startingBalance = Double.parseDouble(rulesBundle.getString("startingBalance"));

        Enumeration<String> keys = highBundle.getKeys();

        while (keys.hasMoreElements()) {
            String nextElement = keys.nextElement();
            if (nextElement.startsWith("player")) {
                String value = highBundle.getString(nextElement);

                // create new player, associate piece with that player
                Player currentPlayer = new Player(value.split(",")[0], value.split(",")[1], startingBalance);
                players.add(currentPlayer);

            }
        }
    }

    private ArrayList<Card> makePerkCards(String keyName){
        ArrayList<Card> toBeReturned = new ArrayList<Card>();

        ResourceBundle chestBundle = ResourceBundle.getBundle(myBundle.getString(keyName));

        // order not really necessary here, so sticking with enumeration data structure and directly adding
        // to ArrayList

        for(String key:chestBundle.keySet()){

            Card tempCard;

            String[] valueSplit = chestBundle.getString(key).split(",");
            String description = valueSplit[0];

            if(valueSplit[1].equalsIgnoreCase("MON")){
                String amount = valueSplit[2];

                tempCard = new MoneyCard(description, myBoard, Double.parseDouble(amount));
                toBeReturned.add(tempCard);
            }

            else if(valueSplit[1].equalsIgnoreCase("MOV")){
                tempCard = new MoveSpaceCard(description, myBoard, valueSplit[2]);
                toBeReturned.add(tempCard);
            }

            else if(valueSplit[1].equalsIgnoreCase("MOVN")){
                tempCard = new MoveNumberCard(description, myBoard, Integer.parseInt(valueSplit[2]));
            }

            else if(valueSplit[1].equalsIgnoreCase("HOLD")){
                tempCard = new HoldableCard(description, myBoard);
                toBeReturned.add(tempCard);
            }
        }
        return toBeReturned;
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
        String bankString = rulesBundle.getString("bankBalance");

        if(bankString.equals("infinite")){
            return new InfiniteBank();
        }

        else{
            return new Bank(Double.parseDouble(bankString));
        }
    }
}