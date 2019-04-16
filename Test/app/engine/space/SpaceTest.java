package app.engine.space;

import app.engine.agent.Player;
import app.engine.board.Board;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {

    Board testBoard;
    Player testPlayer;
    Space testSpace;

    @BeforeEach
    private void setup() {
        testBoard = new Board("sampleGameSetup");
        testPlayer = testBoard.getPlayers().peek();
        testSpace = testBoard.getSpaces().get(2);
        testSpace.onLand(testPlayer);
    }

    @Test
    void onLand() {
        assertTrue(testSpace.containsPlayer(testPlayer));
    }

    @Test
    void initializeSpace() {

    }

    @Test
    void removeFromCurrentOccupants() {

    }
}