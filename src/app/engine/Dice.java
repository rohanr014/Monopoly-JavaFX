package app.engine;

import java.util.Random;

public class Dice {
    private int[] rolls;
    private int[] sidesPerDie;

    public Dice(int[] sides) {
        sidesPerDie = sides;
        rolls = new int[sides.length];
    }

    private int rollDie(int dieNumber) {
        Random r = new Random();
        return r.nextInt(sidesPerDie[dieNumber]) + 1;
    }

    public int[] rollAllDice() {
        for (int i = 0; i < sidesPerDie.length; i++) {
            rolls[i] = rollDie(i);
        }
        return rolls;
    }

}
