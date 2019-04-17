package app.views.spaces;

import app.engine.space.Railroad;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class RailroadView extends SpaceView<Railroad> {
    private List<ImageView> myPlayerViews;

    public RailroadView(Railroad model){
        super(model.getName(), model);
        myPlayerViews = new ArrayList<>();
        //myPlayerViews.addAll(gamePieceViews);
    }

    @Override
    public void initialize() {
        myRoot = new StackPane();
        myRoot.setStyle("-fx-background-color: PINK");
    }

    @Override
    public void adjustSize() {
    }

}
