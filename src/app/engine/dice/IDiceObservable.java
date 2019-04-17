package app.engine.dice;

import app.engine.board.IBoardObserver;

public interface IDiceObservable {

    void addDiceObserver(IDiceObserver o);
    void removeDiceObserver(IDiceObserver o);
    void notifyDiceObservers();


}
