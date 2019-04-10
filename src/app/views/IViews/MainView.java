package app.views.IViews;


import app.engine.board.Board;
import app.views.IViews.AssetView;
import app.views.IViews.ControlView;
import app.views.IViews.LogHistoryView;
import app.views.IViews.VanillaBoardView;
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
        myScene = new Scene(myRoot, 1000,1000);
    }

    private void setComponents(){
        myRoot.setCenter(new VanillaBoardView(myBoard).getMyRoot());
        myRoot.setRight(new AssetView(myBoard).getMyRoot());
        myRoot.setTop(new ControlView().getMyRoot());
        myRoot.setBottom(new LogHistoryView().getMyRoot());
    }




    public Scene getMyScene(){
        return myScene;
    }
}
