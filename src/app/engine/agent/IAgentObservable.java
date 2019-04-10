package app.engine.agent;

import app.engine.board.Board;

public interface IAgentObservable {
    void addAgentObserver(IAgentObserver o);
    void removeAgentObserver(IAgentObserver o);
    void notifyAgentObservers();
}
