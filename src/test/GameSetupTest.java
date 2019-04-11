package test;

import app.engine.agent.Player;
import app.engine.board.Board;
import app.engine.dice.Dice;
import app.engine.Config.GameSetup;
import app.engine.space.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
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

        System.out.println("Class is " + spaces.get(3).getClass());
        ColorProperty test = (ColorProperty) spaces.get(3);
        System.out.println("Name is " + test.getMyPropertyName());

        ColorProperty VirginiaAvenue = (ColorProperty) spaces.get(14);
        Railroad ShortLine = (Railroad) spaces.get(35);
        Utility WaterWorks = (Utility) spaces.get(28);

        assertEquals("Virginia Avenue", VirginiaAvenue.getMyPropertyName());
        assertEquals("Short Line", ShortLine.getName());
        assertEquals("Water Works", WaterWorks.getName());
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
}