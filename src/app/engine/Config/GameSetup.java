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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class GameSetup {

    private static final String MODE_KEY = "prop_file";
    private static final String RULES_KEY = "rules_file";
    private static final String COMMUNITY_KEY = "communityCards";
    private static final String CHANCE_KEY = "chanceCards";

    private static final String PERK_KEY = "perkCards";

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


    private Map<String,Queue<Card>> perkCards;

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


        players = new LinkedList<>();
        spaces = new ArrayList<>();

        perkCards = makePerkCards();
        createPlayers();
        createSpaces();

        // use communityCards and chanceCards as arguments for makePerkCards

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


    private void createSpaces() {
        String spacesFile = myBundle.getString(SPACES_FILE_KEY);
        ResourceBundle spacesBundle = ResourceBundle.getBundle(spacesFile);
        String[] spacesKeys = getSpaceKeys(spacesBundle);

        SpaceMaker currentSpaceMaker = new SpaceMaker(perkCards);

        for (String currentKey : spacesKeys) {
            String[] currentValue = spacesBundle.getString(currentKey).split(",");

            Space currentSpace = null;

            String funcName = currentValue[1];

            Class spaceMakerClass = currentSpaceMaker.getClass();

            try {
                Method funcToCall = spaceMakerClass.getDeclaredMethod(funcName, String.class);
                currentSpace = (Space) funcToCall.invoke(currentSpaceMaker, currentValue[2]);

                spaces.add(currentSpace);

            } catch (NoSuchMethodException e) {
                System.out.println("Couldn't find method " + funcName + " for space " + currentValue[0]);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println("Invocation target exception - see funcName in prop file for key " + currentKey + " with func name " + funcName + "with arg " + currentValue[2]);
                e.printStackTrace();
            }
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

    private Map<String, Queue<Card>> makePerkCards(){
        Map<String, Queue<Card>> toBeReturned = new HashMap<>();

        ResourceBundle cardsBundle = ResourceBundle.getBundle(myBundle.getString(PERK_KEY));

        for(String key:cardsBundle.keySet()){
            String[] valueSplit = cardsBundle.getString(key).split(">");

            String description = valueSplit[0];
            String pileName = valueSplit[1];

            Card tempCard = null;

            if(valueSplit[2].equalsIgnoreCase(MONEY_SPACE_DESIGNATION)){
                String amount = valueSplit[3];
                tempCard = new MoneyCard(description, myBoard, Double.parseDouble(amount));
            }

            else if(valueSplit[2].equalsIgnoreCase(MOVE_SPACE_DESIGNATION)){
                tempCard = new MoveSpaceCard(description, myBoard, valueSplit[3]);
            }

            else if(valueSplit[2].equalsIgnoreCase(MOVE_NUMBER_DESIGNATION)){
                tempCard = new MoveNumberCard(description, myBoard, Integer.parseInt(valueSplit[3]));
            }

            else if(valueSplit[2].equalsIgnoreCase("HOLD")){
                try {
                    tempCard = new HoldableCard(description, myBoard, Arrays.copyOfRange(valueSplit, 3, valueSplit.length));

                } catch (Exception e) {
                    System.out.println("Unable to add card with description " + description + ". Check declaration in properties file");
                    e.printStackTrace();
                }
            }

            if(tempCard == null){
                throw new NullPointerException("Detected invalid card declaration for card at key " + key);
            }

            if(!toBeReturned.containsKey(pileName)){
                toBeReturned.put(pileName, new LinkedList<Card>());
            }

            toBeReturned.get(pileName).add(tempCard);
        }
        return toBeReturned;
    }


    public Map<String, Queue<Card>> getPerkCards() {
        return perkCards;
    }

    @Deprecated
    public Queue<Card> getCommunityChest() {
        // return communityChest;
        return null;
    }

    @Deprecated
    public Queue<Card> getChanceCards() {
        // return chance;
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
            return new Bank(Double.parseDouble(bankString), "bank");
        }
    }

    public RulesInitializer getRules() {
        return rules;
    }
}