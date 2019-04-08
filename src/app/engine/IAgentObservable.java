package app.engine;

public interface IAgentObservable {
    public abstract void addObserver(IAgentObserver o);
    public abstract void removeObserver(IAgentObserver o);
    public abstract void notifyObservers();
}
