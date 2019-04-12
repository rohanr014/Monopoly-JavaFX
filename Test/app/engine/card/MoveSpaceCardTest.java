package app.engine.card;

import app.engine.agent.Player;
import app.engine.board.Board;
import app.engine.space.Space;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoveSpaceCardTest {

    private Board myBoard;

    private Card moveIllinoisAve;

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

        moveIllinoisAve = chance.get(1);
        System.out.println(moveIllinoisAve.getDescription());


    }

    @Test
    void invokeAction() {
        moveIllinoisAve.invokeAction(myGuy);

        assertEquals(24, myGuy.getCurrentSpace());
    }
}