package app.views.spaces;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

abstract class CommonSpaceView extends SpaceView{

    private Pane myRoot;

    @Override
    public Pane initialize(){
        myRoot = new VBox();
        myRoot.setStyle("-fx-background-color: beige;");

       return myRoot;

    }

    public void spaceUpdate(){

    }
}
