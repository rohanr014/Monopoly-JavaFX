package app.engine.agent;

import app.engine.board.Board;

public interface IAgentObserver {
    void agentUpdate(double wallet, Board board);
}
