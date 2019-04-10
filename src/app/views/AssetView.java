package app.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AssetView implements IView {
    private Pane myRoot;

    public AssetView(){
        myRoot = new Pane();
        setRoot();
    }

    private void setRoot(){
        var tempPane = new VBox();
        tempPane.setAlignment(Pos.CENTER);
        tempPane.getChildren().add(new AgentView().getMyRoot());
        myRoot.getChildren().add(tempPane);
    }



    @Override
    public Pane getMyRoot() {
        return myRoot;
    }
}
