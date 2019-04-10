package app.views.IViews;

import app.engine.board.Board;
import app.views.IViews.IView;
import javafx.scene.layout.Pane;

public class LogHistoryView implements IView {//to be completed in sprint 2
    private Pane myRoot;
    private Board myBoard;

    public LogHistoryView(Board board){
        myBoard = board;
        myRoot = new Pane();

    }

    @Override
    public Pane getMyRoot(){
        return myRoot;
    }
}
