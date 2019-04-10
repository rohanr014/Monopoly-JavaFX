package app.views.IViews;


import app.engine.board.Board;
import javafx.scene.layout.Pane;


public class VanillaBoardView extends BoardView {
    private Pane myRoot;
    private Board myBoard;

    public VanillaBoardView(Board board){
        myRoot = new Pane();
        myRoot.setStyle("-fx-background-color: green;");
        myBoard = board;
    }




    @Override
    public Pane getMyRoot() {
        return myRoot;
    }
}
