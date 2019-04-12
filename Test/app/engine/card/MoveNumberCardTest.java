package app.engine.card;

import app.engine.agent.Player;
import app.engine.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MoveNumberCardTest {

    private Board myBoard;

    private Card moveBack3;

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

        moveBack3 = chance.get(7);


    }

    @Test
    void invokeAction() {
        myBoard.move(myGuy, 3);
        moveBack3.invokeAction(myGuy);
        assertEquals(0, myGuy.getCurrentSpace());
    }
}