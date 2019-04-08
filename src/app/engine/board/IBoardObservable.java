package app.engine.board;

public interface IBoardObservable {
    public abstract void addObserver(IBoardObserver o);
    public abstract void removeObserver(IBoardObserver o);
    public abstract void notifyObservers();

}
