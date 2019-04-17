package app.views.IViews;


import app.engine.board.Board;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainView{
    private BorderPane myRoot;
    private Scene myScene;
    private Board myBoard;



    private VanillaBoardView myVanillaBoardView;
    private AssetView myAssetView;
    private ControlView myControlView;
    private LogHistoryView myLogHistoryView;


    public MainView(Board board){
        myBoard = board;
        myRoot = new BorderPane();
        myLogHistoryView = new LogHistoryView(board);
        myVanillaBoardView = new VanillaBoardView(board, myLogHistoryView);
        //myVanillaBoardView.initialSetting();
        myAssetView = new AssetView(board);
        myControlView = new ControlView(board);

        setComponents();
        myScene = new Scene(myRoot, 1100,800);
        myScene.getStylesheets().add(getClass().getResource("/vanilla.css").toExternalForm());

    }

    private void setComponents(){

        myRoot.setCenter(myVanillaBoardView.getMyRoot());
        myRoot.setRight(myAssetView.getMyRoot());
        myRoot.setTop(myControlView.getMyRoot());
        myRoot.setBottom(myLogHistoryView.getMyRoot());
    }

    public VanillaBoardView getMyVanillaBoardView() {
        return myVanillaBoardView;
    }

    public AssetView getMyAssetView() {
        return myAssetView;
    }

    public ControlView getMyControlView(){
        return myControlView;
    }

    public LogHistoryView getMyLogHistoryView(){
        return myLogHistoryView;
    }




    public Scene getMyScene(){
        return myScene;
    }
}
