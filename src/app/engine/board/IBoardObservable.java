package app.engine.board;

public interface IBoardObservable {
    void addBoardObserver(IBoardObserver o);
    void removeBoardObserver(IBoardObserver o);
    void notifyBoardObservers();

}
