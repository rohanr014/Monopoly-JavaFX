package app.engine.dice;

import app.engine.board.IBoardObserver;

public interface IDiceObservable {

    void addDicedObserver(IDiceObserver o);
    void removeDiceObserver(IDiceObserver o);
    void notifyDiceObservers();


}
