package app.views.IViews;


import app.engine.board.Board;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
public class MainView{
    private BorderPane myRoot;
    private Scene myScene;
    private Board myBoard;


    public MainView(Board board){
        myBoard = board;
        myRoot = new BorderPane();
        setComponents();
        myScene = new Scene(myRoot, 1100,800);
    }

    private void setComponents(){
        myRoot.setCenter(new VanillaBoardView(myBoard).getMyRoot());
        myRoot.setRight(new AssetView(myBoard).getMyRoot());
        myRoot.setTop(new ControlView(myBoard).getMyRoot());
        myRoot.setBottom(new LogHistoryView(myBoard).getMyRoot());
    }




    public Scene getMyScene(){
        return myScene;
    }
}
