package app.engine;

public interface ISpaceObservable {
    void addSpaceObserver(ISpaceObserver o);
    void removeSpaceObserver(ISpaceObserver o);
    void notifySpaceObservers();
}
