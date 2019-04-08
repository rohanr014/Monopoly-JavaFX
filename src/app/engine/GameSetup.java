package app.engine;

import app.engine.agent.Bank;
import app.engine.agent.Player;
import app.engine.card.Card;
import app.engine.space.Space;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class GameSetup {

    private ResourceBundle myBundle;
    private ArrayList<String[]> players;
    private String gamePropFile;
    private String rulesPropFile;

    public GameSetup(String propFile) {
        myBundle = ResourceBundle.getBundle(propFile);


    }


    public Collection<Card> getCommunityChest() {
    }

    public Collection<Card> getChanceCards() {
    }

    public Collection<Player> getPlayers() {
    }

    public Space[] getSpaces() {
    }

    public Collection<Dice> getDice() {
    }

    public Bank getBank() {
    }

    private void createPlayers(){
        Enumeration<String> keys = myBundle.getKeys();

        while(keys.hasMoreElements()){
            String nextElement = keys.nextElement();
            if(nextElement.startsWith("player")){
                // create new player, associate piece with that player


            }
        }

    }
}
