package app.engine.board;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board b;
    private ResourceBundle myBundle;

    @BeforeEach
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
    void goMoney(){
        Double expected = 200.0;
        String val = myBundle.getString("GoMoney");
        Double actual = Double.parseDouble(val);
        assertEquals(expected, actual);
    }

    @Test
    void saleToBankMultiplier(){
        Double expected = .5;
        String val = myBundle.getString("SaleToBankMultiplier");
        Double actual = Double.parseDouble(val);
        assertEquals(expected, actual);
    }

    @Test
    void unmortgageMultiplier(){
        Double expected = 1.1;
        String val = myBundle.getString("UnmortgageMultiplier");
        Double actual = Double.parseDouble(val);
        assertEquals(expected, actual);
    }

    @Test
    void hoadableCardSaleValue(){
        Double expected = 30.0;
        String val = myBundle.getString("HoadableCardSaleValue");
        Double actual = Double.parseDouble(val);
        assertEquals(expected, actual);
    }

    @Test
    void GetOutOfFee(){
        Double expected = 50.0;
        String val = myBundle.getString("GetOutOfJailFee");
        Double actual = Double.parseDouble(val);
        assertEquals(expected, actual);
    }

    @Test
    void maxTurnsInJail(){
        int expected = 3;
        String val = myBundle.getString("MaxTurnsInJail");
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