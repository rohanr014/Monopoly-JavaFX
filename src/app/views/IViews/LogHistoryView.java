package app.views.IViews;

import app.engine.agent.IAgentObserver;
import app.engine.board.Board;
import app.engine.board.IBoardObserver;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class LogHistoryView implements IView {
    private static final int SCROLL_WIDTH = 650;
    private static final int SCROLL_HEIGHT = 100;


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
        myScrollPane.setPrefSize(SCROLL_WIDTH, SCROLL_HEIGHT);
        myRoot.getChildren().add(myScrollPane);
    }

    public void addMovementLog(String str){
        vbox.getChildren().add(new Text(str));
    }

    public void addTransactionLog(String tx) {
        vbox.getChildren().add(new Text(tx));
    }

    public Pane getMyRoot(){
        return myRoot;
    }


}
