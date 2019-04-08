package app.engine;

public class Dice {
    private int[] rolls;
    private int[] sidesPerDie;

    public Dice(int[] sides) {
        sidesPerDie = sides;
        rolls = new int[sides.length];
    }

    public int rollDie(int dieNumber) {
        return random number from 1 to sidesPerDie[dieNumber]
    }

    public int rollDice() {
        loop: rolls[i] = rollDie(sidesPerDie[i]) from i=0 to i=rolls.size
        return the sum of the elements in rolls
    }

    public int[] getIndividualRolls() {return rolls;}
}
