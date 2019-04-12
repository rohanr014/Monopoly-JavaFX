package app.views.spaces;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CommonSpaceView extends SpaceView{

    private Pane myRoot;

    public CommonSpaceView(String name){
        myName = name;

    }

    @Override
    public Pane initialize(){
        myRoot = new VBox();
        myRoot.getChildren().add(HBoxMaker.makeHBoxSpace(myName));
        myRoot.setStyle("-fx-background-color: CYAN");
       return myRoot;

    }

    public void spaceUpdate(){

    }
}
