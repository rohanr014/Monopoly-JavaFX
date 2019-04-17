package app.engine.board;

import app.engine.space.Space;

public interface IBoardObserver {
    void boardUpdate();

    void boardUpdate(Space start, Space end);
}
