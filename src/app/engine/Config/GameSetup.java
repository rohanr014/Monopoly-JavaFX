package app.engine.Config;

import app.engine.board.RulesInitializer;
import app.engine.card.*;
import app.engine.dice.Dice;
import app.engine.agent.Bank;
import app.engine.agent.InfiniteBank;
import app.engine.agent.Player;
import app.engine.board.Board;
import app.engine.space.*;

import java.io.IOException;
import java.util.*;

public class GameSetup {

    private static final String MODE_KEY = "prop_file";
    private static final String RULES_KEY = "rules_file";
    private static final String COMMUNITY_KEY = "communityCards";
    private static final String CHANCE_KEY = "chanceCards";

    private static final String COMMUNITY_CHEST_FILE_KEY = "communityCards";
    private static final String CHANCE_FILE_KEY = "chanceCards";

    private static final String SPACES_FILE_KEY = "spacesFile";
    private static final String SPACE = "space";

    private static final String COLOR_PROP_DESIGNATION = "CP";
    private static final String RAILROAD_DESIGNATION = "RR";
    private static final String UTILITY_DESIGNATION = "U";
    private static final String CHANCE_DESIGNATION = "CH";
    private static final String COMMUNITY_CHEST_DESIGNATION = "CC";
    private static final String MOVE_SPACE_DESIGNATION = "MOV";
    private static final String MOVE_NUMBER_DESIGNATION = "MOVN";
    private static final String MONEY_SPACE_DESIGNATION = "MON";

    private static final String FoundSpaceWithInvalidType = "Found space with invalid type: ";

    private static final String NAME_KEY = "name";
    private static final String SALE_PRICE_KEY = "salePrice";
    private static final String HOUSE_PRICE_KEY = "housePrice";
    private static final String HOTEL_PRICE_KEY = "hotelPrice";
    private static final String MORTGAGE_KEY = "mortgage";
    private static final String COLOR_KEY = "color";
    private static final String RENTS_KEY = "rents";
    private static final String MONEY_KEY = "money";


    private ResourceBundle highBundle;
    private ResourceBundle myBundle;
    private ResourceBundle rulesBundle;

    private Queue<Player> players;
    private List<Space> spaces;
    private Queue<Card> communityChest;
    private Queue<Card> chance;

    private Board myBoard;
    private RulesInitializer rules;

    public GameSetup(String directory, String filename, Board board) throws IOException {
        this(GameFileHandler.getGamedata(directory, filename), board);
    }

    public GameSetup(ResourceBundle bundle, Board b) {
        highBundle = bundle;
        myBundle = ResourceBundle.getBundle(highBundle.getString(MODE_KEY));
        rulesBundle = ResourceBundle.getBundle(highBundle.getString(RULES_KEY));
        myBoard = b;
        rules = new RulesInitializer(rulesBundle);

        communityChest = makePerkCards(COMMUNITY_KEY);
        chance = makePerkCards(CHANCE_KEY);
        players = new LinkedList<>();
        spaces = new ArrayList<>();

        createPlayers();
        createSpaces();

        // use communityCards and chanceCards as arguments for makePerkCards
        communityChest = makePerkCards(COMMUNITY_CHEST_FILE_KEY);
        chance = makePerkCards(CHANCE_FILE_KEY);
    }

    private String[] getSpaceKeys(ResourceBundle spacesBundle){
        int size = spacesBundle.keySet().size();

        String[] inOrder = new String[size];

        for(int i=0; i<size; i++){
            String currentString = SPACE + i;
            inOrder[i] = currentString;
        }

        return inOrder;
    }


    private void createSpaces(){
        String spacesFile = myBundle.getString(SPACES_FILE_KEY);
        ResourceBundle spacesBundle = ResourceBundle.getBundle(spacesFile);
        String[] spacesKeys = getSpaceKeys(spacesBundle);

        for(String currentKey: spacesKeys){
            String[] currentValue = spacesBundle.getString(currentKey).split(",");

            Space currentSpace = null;

            if(currentValue[1].equalsIgnoreCase(COLOR_PROP_DESIGNATION)){
                currentSpace = makeCP(currentValue[2]);
            }
            else if(currentValue[1].equalsIgnoreCase(RAILROAD_DESIGNATION)){
                currentSpace = makeRR(currentValue[2], true);
            }
            else if(currentValue[1].equalsIgnoreCase(UTILITY_DESIGNATION)){
                currentSpace = makeRR(currentValue[2], false);
            }

            else if(currentValue[1].equalsIgnoreCase(CHANCE_DESIGNATION)){
                currentSpace = new CardSpace(currentValue[1], chance);
            }
            else if(currentValue[1].equalsIgnoreCase(COMMUNITY_CHEST_DESIGNATION)){
                currentSpace = new CardSpace(currentValue[1], communityChest);
            }
            else if(currentValue[1].equalsIgnoreCase(MOVE_SPACE_DESIGNATION)){
                currentSpace = makeMoveSpace(currentValue[2]);
            }
            else if(currentValue[1].equalsIgnoreCase(MONEY_SPACE_DESIGNATION)){
                currentSpace = makeMoney(currentValue[2]);
            }

            if(currentSpace == null){
                System.out.println(FoundSpaceWithInvalidType + currentValue[0]);
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

        String name = cpBundle.getString(NAME_KEY);

        double purchaseCost = Double.parseDouble(cpBundle.getString(SALE_PRICE_KEY));
        double housePrice = Double.parseDouble(cpBundle.getString(HOUSE_PRICE_KEY));
        double hotelPrice = Double.parseDouble(cpBundle.getString(HOTEL_PRICE_KEY));
        double mortgageValue = Double.parseDouble(cpBundle.getString(MORTGAGE_KEY));
        String colorString = cpBundle.getString(COLOR_KEY);

        String[] rentStrings = cpBundle.getString(RENTS_KEY).split(",");
        //System.out.println(name);

        return new ColorProperty(name, purchaseCost, mortgageValue, stringsToDoubles(rentStrings), housePrice, hotelPrice, colorString);
    }



    private Space makeRR(String propFile, boolean isRailroad){
        ResourceBundle currentBundle = ResourceBundle.getBundle(propFile);


        double purchaseCost = Double.parseDouble(currentBundle.getString(SALE_PRICE_KEY));
        double mortgageValue = Double.parseDouble(currentBundle.getString(MORTGAGE_KEY));
        String[] rentStrings = currentBundle.getString(RENTS_KEY).split(",");

        String fullName = currentBundle.getString(NAME_KEY);
        String[] fullNameSplit = fullName.split(",");

        String name = null;
        String imageName = null;

        if(fullNameSplit.length > 1){
            name = fullNameSplit[0];
            imageName = fullNameSplit[1];

        }
        else{
            name = fullName;
        }

        // IS THERE A BETTER WAY TO DO THIS?

        if(imageName == null){
            if(isRailroad) {
                return new Railroad(name, purchaseCost, mortgageValue, stringsToDoubles(rentStrings));
            }
            else{
                return new Utility(name, purchaseCost, mortgageValue, stringsToDoubles(rentStrings));
            }
        }

        else{
            if(isRailroad) {
                return new Railroad(name, purchaseCost, mortgageValue, stringsToDoubles(rentStrings), imageName);
            }
            else{
                return new Utility(name, purchaseCost, mortgageValue, stringsToDoubles(rentStrings), imageName);
            }
        }
    }

    private Space makeMoney(String propFile){
        ResourceBundle moneyBundle = ResourceBundle.getBundle(propFile);

        double moneyGiven = Double.parseDouble(moneyBundle.getString(MONEY_KEY));

        String fullName = moneyBundle.getString(NAME_KEY);
        String[] fullNameSplit = fullName.split(",");

        if(fullNameSplit.length > 1){
            String name = fullNameSplit[0];
            String imageName = fullNameSplit[1];
            return new CommonSpace(name, moneyGiven, imageName);
        }
        else{
            return new CommonSpace(fullName, moneyGiven);
        }
    }

    private Space makeMoveSpace(String propFile){
        ResourceBundle moveBundle = ResourceBundle.getBundle(propFile);

        String destinationName = moveBundle.getString("destination");

        String fullName = moveBundle.getString(NAME_KEY);
        String[] fullNameSplit = fullName.split(",");

        if(fullNameSplit.length > 1){
            String name = fullNameSplit[0];
            String imageName = fullNameSplit[1];
            return new CommonSpace(name, destinationName, imageName);
        }
        else{
            return new CommonSpace(fullName, destinationName);
        }

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

    private Queue<Card> makePerkCards(String keyName){
        Queue<Card> toBeReturned = new LinkedList<>();

        ResourceBundle chestBundle = ResourceBundle.getBundle(myBundle.getString(keyName));

        // order not really necessary here, so sticking with enumeration data structure and directly adding
        // to ArrayList

        for(String key:chestBundle.keySet()){

            Card tempCard;

            String[] valueSplit = chestBundle.getString(key).split(">");
            String description = valueSplit[0];

            if(valueSplit[1].equalsIgnoreCase(MONEY_SPACE_DESIGNATION)){
                String amount = valueSplit[2];

                tempCard = new MoneyCard(description, myBoard, Double.parseDouble(amount));
                toBeReturned.add(tempCard);
            }

            else if(valueSplit[1].equalsIgnoreCase(MOVE_SPACE_DESIGNATION)){
                tempCard = new MoveSpaceCard(description, myBoard, valueSplit[2]);
                toBeReturned.add(tempCard);
            }

            else if(valueSplit[1].equalsIgnoreCase(MOVE_NUMBER_DESIGNATION)){
                tempCard = new MoveNumberCard(description, myBoard, Integer.parseInt(valueSplit[2]));
                toBeReturned.add(tempCard);
            }

            else if(valueSplit[1].equalsIgnoreCase("HOLD")){
                try {
                    tempCard = new HoldableCard(description, myBoard, Arrays.copyOfRange(valueSplit, 2, valueSplit.length));
                    toBeReturned.add(tempCard);

                } catch (Exception e) {
                    System.out.println("Unable to add card with description " + description + ". Check declaration in properties file");
                    e.printStackTrace();
                }
            }
        }

        return toBeReturned;
    }


    public Queue<Card> getCommunityChest() {
        return communityChest;
    }

    public Queue<Card> getChanceCards() {
        return chance;
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
            return new Bank(Double.parseDouble(bankString), "bank");
        }
    }

    public RulesInitializer getRules() {
        return rules;
    }
}