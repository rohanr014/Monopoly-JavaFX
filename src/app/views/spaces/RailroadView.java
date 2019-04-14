package app.views.spaces;

import app.engine.space.Railroad;
import javafx.geometry.VPos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RailroadView extends SpaceView<Railroad> {
    public RailroadView(Railroad model){
        super(model.getName(), model);
    }

    @Override
    public void initialize() {
        myRoot = new Pane();
        myRoot.setStyle("-fx-background-color: PINK");
    }

    @Override
    public void adjustSize() {
    }

    @Override
    public void spaceUpdate() {

    }
}
