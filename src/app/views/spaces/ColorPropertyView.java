package app.views.spaces;

import app.engine.space.ColorProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorPropertyView extends SpaceView{

    private ColorProperty myColorProperty;
    //private double myRent;
    private String myName;


    private Pane myRoot;


    public ColorPropertyView(String name) {
        //myColorProperty = colorProperty;
        myName = name;
        //myRent = rent;
        myRoot = new VBox();
    }

    public String getMyName(){
        return myName;
    }



    @Override
    public Pane initialize() {
        myRoot.getChildren().add(HBoxMaker.makeHBoxSpace(myName, Color.ORANGE));
        //myRoot.getChildren().add(HBoxMaker.makeHBoxSpace("Rent", myRent));

        //myRoot.setStyle("-fx-background-color: beige;");

        return myRoot;

    }

    @Override
    public void spaceUpdate() {

    }
}
