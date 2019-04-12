package app.views.spaces;

import app.engine.space.ColorProperty;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorPropertyView extends SpaceView{

    private ColorProperty myColorProperty;
    //private double myRent;


    private Pane myRoot;
    private VBox myVBox;


    public ColorPropertyView(String name) {
        //myColorProperty = colorProperty;
        myName = name;

        myRoot = new StackPane();
        myVBox = new VBox();
        myRoot.getChildren().addAll(new Rectangle(30, 60,Color.WHITE),myVBox);
        initialize();

    }

    public String getMyName(){
        return myName;
    }



    @Override
    public Pane initialize() {
        HBox temp = HBoxMaker.makeHBoxSpace(myName, Color.ORANGE);
        temp.setSpacing(20);
        temp.resize(30,60);

        myRoot.getChildren().add(temp);
        myRoot.getChildren().add(HBoxMaker.makeHBoxSpace("hi", 60.00));
        myRoot.getChildren().add(HBoxMaker.makeHBoxSpace("Ay", "yo"));
        myRoot.setMinHeight(50);
        myRoot.setMaxHeight(50);
        myRoot.setMinWidth(20);
        myRoot.setMaxWidth(20);
        return myRoot;

    }

    public Pane getMyRoot(){return myRoot;}

    @Override
    public void spaceUpdate() {

    }
}
