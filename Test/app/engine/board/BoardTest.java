package app.engine.board;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board b;
    private ResourceBundle myBundle;

    @BeforeAll
    void setup(){
        var s = "sampleGameSetup.properties";
        myBundle = ResourceBundle.getBundle("boardValues");
    }

    @Test
    void testDoublesForJail(){
        int expected = 3;
        String val = myBundle.getString("DoublesForJail");
        int actual = Integer.parseInt(val);
        assertEquals(expected, actual);
    }

    @Test
    void startTurn() {

    }

    @Test
    void endTurn() {
    }

    @Test
    void moveToSpace() {
    }

    @Test
    void moveForwardByNumberOfSteps() {
    }

    @Test
    void contains() {
    }

    @Test
    void removePlayer() {
    }

    @Test
    void getSellPrice() {
    }

    @Test
    void getLastRollSum() {
    }
}