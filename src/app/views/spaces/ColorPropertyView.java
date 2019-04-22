package app.views.spaces;

import app.engine.space.ColorProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class ColorPropertyView extends PropertyView<ColorProperty> {
    private VBox myVBox;
    private Rectangle colorHeader;

    public ColorPropertyView(ColorProperty model) {
        super(model);
    }

    public String getMyName(){
        return myName;
    }

    @Override
    public void initialize() {
        colorHeader = new Rectangle();
        colorHeader.setFill(Color.valueOf(myModel.getMyColor()));

        myVBox = new VBox();
        myVBox.getChildren().add(colorHeader);

        myRoot = new StackPane();
        myRoot.getChildren().addAll(myVBox);


        myRoot.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> onClick());
    }
//    @Override
//    public Pane initializePane(Pane pane) {
//        colorHeader = new Rectangle();
//        colorHeader.setFill(Color.valueOf(myModel.getMyColor()));
//
//        myVBox = new VBox();
//        myVBox.getChildren().add(colorHeader);
//
//        pane = new StackPane();
//        pane.getChildren().addAll(myVBox);
//
//        pane.addEventHandler(MouseEvent.MOUSE_CLICKED,
//                e -> onClick());
//        return pane;
//    }

    @Override
    public void adjustSize() {
        colorHeader.setWidth(myRoot.getPrefWidth()-2);
        colorHeader.setHeight(myRoot.getPrefHeight()/5);
    }

    public Pane getMyRoot(){return myRoot;}


    private void onClick() {
        DetailColorProperty dcp = new DetailColorProperty(myModel);
        dcp.adjustSize();
    }
}
