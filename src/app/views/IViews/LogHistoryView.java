package app.views.IViews;

import app.engine.board.Board;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class LogHistoryView implements IView {

    private Pane myRoot;
    private ScrollPane myScrollPane;
    private Board myBoard;
    private VBox vbox;
    private Scene myScene;


    public LogHistoryView(Board board){
        myRoot = new Pane();
        myBoard = board;
        myScrollPane = new ScrollPane();
        vbox = new VBox();
        myScrollPane.setContent(vbox);
        myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        myScrollPane.setPrefSize(800, 100);
        myRoot.getChildren().add(myScrollPane);
    }



    public Pane getMyRoot(){
        return myRoot;
    }


}
