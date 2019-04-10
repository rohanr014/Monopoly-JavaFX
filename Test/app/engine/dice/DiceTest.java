package app.engine.dice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DiceTest {

    Dice d;

    @BeforeEach
    void setup() {
        d = new Dice(new int[]{6,6});
    }

    @Test
    void rollAllDice() {
        var list = new ArrayList<Integer>();

        int x = 100;
        while (x>0){
            var roll = d.rollAllDice();
            list.add(sumRoll(roll));
            x--;
        }

        for (int val: list){
            if (val < 2 || val > 12){
                assertTrue(false);
            }
        }
        assertTrue(true);
    }


    int sumRoll(int[] arr){
        int sum = 0;
        for (int x: arr){
            sum += x;
        }
        return sum;
    }
}