package app.engine;

import app.engine.agent.Bank;
import app.engine.agent.Player;
import app.engine.card.Card;
import app.engine.space.Space;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Queue;

public class GameSetup {

    private ResourceBundle myBundle;
    private ResourceBundle rulesBundle;

    private Queue<Player> players;
    private String gamePropFile;
    private String rulesPropFile;

    public GameSetup(String propFile) {
        myBundle = ResourceBundle.getBundle(propFile);
        rulesBundle = ResourceBundle.getBundle(myBundle.getString("rules_file"));

        createPlayers();

    }


    public Collection<Card> getCommunityChest() {
    }

    public Collection<Card> getChanceCards() {

    }

    public Queue<Player> getPlayers() {
        return players;
    }

    public Space[] getSpaces() {


    }

    public Collection<Dice> getDice() {

    }

    public Bank getBank() {
    }

    private void createPlayers(){
        double startingBalance = Double.parseDouble(rulesBundle.getString("startingBalance"));

        Enumeration<String> keys = myBundle.getKeys();

        while(keys.hasMoreElements()){
            String nextElement = keys.nextElement();
            if(nextElement.startsWith("player")){
                String value = myBundle.getString(nextElement);

                // create new player, associate piece with that player
                Player currentPlayer = new Player(value.split(",")[0], value.split(",")[1], startingBalance);
                players.add(currentPlayer);

            }
        }

    }
}
