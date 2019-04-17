package app.engine.agent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfiniteBankTest {

    Bank b1, b2;

    @BeforeEach
    void setup(){
        b1 = new InfiniteBank();
        b2 = new Bank(100, "infinite bank");
    }

    @Test
    void testGiveMoney() {
        b1.giveMoney(b2, 1000);
        var expected = 1100;
        var actual = b2.getWallet();
        assertEquals(expected, actual);
    }
}