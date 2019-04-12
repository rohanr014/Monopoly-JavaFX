package app.engine.card;

import app.engine.agent.Player;
import app.engine.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HoldableCardTest {

    private Board myBoard;
    private Card jailFree;
    private Player myGuy;

    @BeforeEach
    private void init(){
        try {
            myBoard = new Board("test", "sampleGameSetup");
        } catch (IOException e) {
            e.printStackTrace();
        }

        myGuy = new Player("Jaiveer", "Vanilla/Pieces/car.png", 69);
        ArrayList<Card> chance = (ArrayList<Card>) myBoard.getChanceCards();

        jailFree = chance.get(6);

    }

    @Test
    void useCard() {
        myGuy.goToJail();

        jailFree.useCard(myGuy);
        assertFalse(myGuy.isInJail());
    }


    @Test
    void dummyFunction(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        String[] fullSplit = "This is just a test.>HOLD>dummy>int>3>int>4".split(">");
        String desc = fullSplit[0];
        String[] funcWithArgs = Arrays.copyOfRange(fullSplit, 2, fullSplit.length);

        try {
            HoldableCard dummyCard = new HoldableCard(desc, myBoard, funcWithArgs);
            dummyCard.useCard(myGuy);
            assertEquals("Dummy worked and printed out 3 along with 4\n", outContent.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.setOut(System.out);
        System.setErr(System.err);
    }
}