package app.views.IViews;

import app.engine.board.Board;
import app.views.IViews.AgentView;
import app.views.IViews.IView;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AssetView implements IView {
    private Pane myRoot;
    private Board myBoard;

    public AssetView(Board board){
        myRoot = new Pane();
        myBoard = board;
        setRoot();
    }

    private void setRoot(){//need to be automated later but hardcoded for now
        var tempPane = new VBox();
        tempPane.setAlignment(Pos.CENTER);
        tempPane.getChildren().add(new AgentView("player one").getMyRoot());
        tempPane.getChildren().add(new AgentView("player two").getMyRoot());
        tempPane.getChildren().add(new AgentView("player three").getMyRoot());
        tempPane.getChildren().add(new AgentView("player four").getMyRoot());
        myRoot.getChildren().add(tempPane);
    }



    @Override
    public Pane getMyRoot() {
        return myRoot;
    }
}
