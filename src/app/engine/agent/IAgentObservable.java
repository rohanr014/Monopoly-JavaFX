package app.engine.agent;

public interface IAgentObservable {
    void addAgentObserver(IAgentObserver o);
    void removeAgentObserver(IAgentObserver o);
    void notifyAgentObservers();
}
