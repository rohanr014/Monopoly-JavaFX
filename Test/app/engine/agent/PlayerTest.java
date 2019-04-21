package app.engine.agent;

import app.engine.board.Board;
import app.engine.card.Holdable;
import app.engine.card.HoldableCard;
import app.engine.space.Property;
import app.engine.space.Railroad;
import app.engine.space.Space;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player p1;
    private Board board;
    private Property prop;

    @BeforeEach
    void setup() throws Exception{
        board = new Board("/Vanilla", "Game1");
        p1 = board.getPlayers().peek();
        prop = (Property) board.getSpaces().get(1);
        prop.ownershipSwitchedTo(p1);
    }

    @Test
    void testMortgage() {
        p1.mortgage(prop);
        assertTrue(prop.isMortgaged());
        assertTrue(prop.getOwner().equals(p1));
        assertTrue(p1.getWallet()==1530);
    }

    @Test
    void testUnmortgage() {
        p1.mortgage(prop);
        p1.unmortgage(prop);
        assertTrue(!prop.isMortgaged());
        assertTrue(prop.getOwner().equals(p1));
        assertTrue(p1.getWallet()==1497);
    }

//    @Test
//    void testBuildHouse() {
//    }
//
//    @Test
//    void testBuildHotel() {
//    }
//
//    @Test
//    void testSellHouse() {
//    }
//
//    @Test
//    void testSellHotel() {
//    }

    @Test
    void testSellProperty() {
        p1.sell(prop);
        assertTrue(prop.getOwner().equals(board.getBank()));
        assertTrue(p1.getWallet()==1500+prop.getValue());
    }

    @Test
    void testSellCard() throws Exception{
        var cards = board.getChanceCards();
        String[] arr = {"leaveJail"};
        HoldableCard hc = new HoldableCard("Jail", board, arr);
        hc.invokeAction(p1);
        hc.setOriginPile((Queue) cards);
        p1.sell(hc);

        assertTrue(cards.contains(hc));
        assertTrue(p1.getWallet()==1500+board.getRules().getHoldableCardSellValue());
    }
}