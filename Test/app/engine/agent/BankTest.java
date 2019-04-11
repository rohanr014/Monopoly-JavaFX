package app.engine.agent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    Bank b1, b2;

    @BeforeEach
    void setup(){
        b1 = new Bank(100);
        b2 = new Bank(50);
    }

    @Test
    void testGiveMoneySuccess() {
        b1.giveMoney(b2, 100);
        var expected = 150;
        var actual = b2.getWallet();
        assertEquals(expected, actual);
    }

    @Test
    void testGiveMoneyFail() {
        b2.giveMoney(b1, 100);
        var expected = 150;
        var actual = b1.getWallet();
        assertEquals(expected, actual);

    }
}