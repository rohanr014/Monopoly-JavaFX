import app.engine.agent.Player;
import app.engine.board.Board;
import app.engine.card.*;
import app.engine.dice.Dice;
import app.engine.Config.GameSetup;
import app.engine.space.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class GameSetupTest {
    ResourceBundle testBundle;
    Board myBoard;
    GameSetup test;

    @BeforeEach
    private void init() throws IOException {
        String setupFile = "sampleGameSetup";
        myBoard = new Board("test", setupFile);
        test = new GameSetup("test", setupFile, myBoard);
    }


    @Test
    void getPlayers() {
        Queue<Player> testPlayers = test.getPlayers();

        Player[] playersArray = testPlayers.toArray(new Player[6]);
        String[] playerNames = new String[playersArray.length];

        for (int i = 0; i < playersArray.length; i++) {
            playerNames[i] = playersArray[i].getName();
        }

        Arrays.sort(playerNames);

        String player1 = "Ed";
        String player2 = "Jacob";
        String player3 = "Jaiveer";
        String player4 = "Jaylyn";
        String player5 = "Rohan";
        String player6 = "Will";

        String[] expected = {player1, player2, player3, player4, player5, player6};

        assertArrayEquals(playerNames, expected);
        assertEquals(1500.0, playersArray[0].getWallet());
    }

    @Test
    void getSpaces() {
        List<Space> spaces = test.getSpaces();

        ColorProperty VirginiaAvenue = (ColorProperty) spaces.get(14);
        Railroad ShortLine = (Railroad) spaces.get(35);
        Utility WaterWorks = (Utility) spaces.get(28);

        assertEquals("Short Line", ShortLine.getName());
        assertEquals("Water Works", WaterWorks.getName());

        Color testColor = Color.getColor("pink");
        assertEquals("Virginia Avenue", VirginiaAvenue.getName());
        assertEquals("pink", VirginiaAvenue.getMyColor());

    }

    @Test
    void getDice() {
        List<Dice> testDice = test.getDice();

        assertEquals(testDice.size(), 1);

        int[] rolled = testDice.get(0).rollAllDice();

        assertEquals(rolled.length, 2);

        int firstRolled = rolled[0];
        int secondRolled = rolled[1];

        assertTrue(1<=firstRolled && firstRolled<=6 && 1<=secondRolled && secondRolled<=6);

    }

    @Test
    void getPerkCards(){
        ArrayList<Card> testChest = (ArrayList<Card>)test.getCommunityChest();

        ArrayList<String> chestNames = new ArrayList<String>();

        for(Card card:testChest){
            chestNames.add(card.getDescription());
        }

        Collections.sort(chestNames);

        ArrayList<Card> testChance = (ArrayList<Card>) test.getChanceCards();
        ArrayList<String> chanceNames = new ArrayList<String>();

        for(Card card:testChance){
            chanceNames.add(card.getDescription());
        }

        Collections.sort(chanceNames);

        // check parsing of description
        assertEquals("Advance to \"Go\". Collect $200", chestNames.get(0));
        assertEquals("Advance to Illinois Ave. If you pass Go, collect $200.", chanceNames.get(1));

        // check to make sure correct type constructed
        assertEquals(testChest.get(1).getClass(), MoneyCard.class);
        assertEquals(testChest.get(4).getClass(), HoldableCard.class);
        assertEquals(testChance.get(1).getClass(), MoveSpaceCard.class);
        assertEquals(testChance.get(7).getClass(), MoveNumberCard.class);

    }
}