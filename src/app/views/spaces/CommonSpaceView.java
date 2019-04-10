package app.views.spaces;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class CommonSpaceView extends SpaceView{

    private Pane myRoot;

    @Override
    public Pane initialize(){
        myRoot = new GridPane();
        myRoot.setStyle("-fx-background-color: beige;");

       return myRoot;

    }

    public void spaceUpdate(){

    }
}
