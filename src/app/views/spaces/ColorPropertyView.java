package app.views.spaces;

import app.engine.space.ColorProperty;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorPropertyView extends SpaceView<ColorProperty> {
    private VBox myVBox;
    private Rectangle colorHeader;

    public ColorPropertyView(ColorProperty model) {
        super(model.getName(), model);
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

        myRoot.hoverProperty().addListener(e->onHover());
    }

    @Override
    public void adjustSize() {
        colorHeader.setWidth(myRoot.getPrefWidth()-2);
        colorHeader.setHeight(myRoot.getPrefHeight()/5);
    }

    public Pane getMyRoot(){return myRoot;}

    @Override
    public void spaceUpdate() {

    }

    private void onHover() {
        DetailColorProperty dcp = new DetailColorProperty(myModel);
        dcp.adjustSize();

    }
}
