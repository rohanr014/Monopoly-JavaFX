package app.engine.card;

import app.engine.agent.Player;
import app.engine.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class MoneyCardTest {
    private Board myBoard;

    private Card bankError;
    private Card doctorsFees;

    private Player myGuy;

    @BeforeEach
    private void init(){
        try {
            myBoard = new Board("test", "sampleGameSetup");
        } catch (IOException e) {
            e.printStackTrace();
        }

        myGuy = new Player("Jaiveer", "Vanilla/Pieces/Pieces/car.png", 69);
        ArrayList<Card> communityChest = (ArrayList<Card>) myBoard.getCommunityChest();

        bankError = communityChest.get(1);
        doctorsFees = communityChest.get(2);
    }

    @Test
    void invokeAction() {
        bankError.invokeAction(myGuy);
        assertEquals(269, myGuy.getWallet());

        doctorsFees.invokeAction(myGuy);
        assertEquals(219, myGuy.getWallet());
    }
}