package app.views.IViews;


import app.engine.board.Board;
import app.views.IViews.BoardView;
import javafx.scene.layout.Pane;

public class VanillaBoardView extends BoardView {
    private Pane myRoot;
    private Board myBoard;

    public VanillaBoardView(Board board){
        myRoot = new Pane();
        myBoard = board;
    }


    @Override
    public Pane getMyRoot() {
        return myRoot;
    }
}
