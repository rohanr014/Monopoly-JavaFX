package app.engine.dice;

import app.engine.board.IBoardObserver;

import java.util.List;
import java.util.Random;

public class Dice  {
    private List<IDiceObserver> myObserverList;
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


    public void addDicedObserver(IDiceObserver o) {
        myObserverList.add(o);

    }


    public void removeDiceObserver(IDiceObserver o) {
        myObserverList.remove(o);

    }


    public void notifyDiceObservers() {
        for(IDiceObserver observer : myObserverList){
            observer.diceUpdate(rolls);
        }

    }
}
