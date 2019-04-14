package app.views.IViews;

import app.engine.board.Board;
import app.engine.board.IBoardObserver;
import app.views.IViews.IView;

abstract class BoardView implements IBoardObserver, IView {


    public abstract void boardUpdate(Board board);


}
