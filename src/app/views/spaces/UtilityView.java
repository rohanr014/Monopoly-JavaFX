package app.views.spaces;

import app.engine.space.Utility;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class UtilityView extends SpaceView<Utility> {
    private List<ImageView> myPlayerViews;

    public UtilityView(Utility model) {
        super(model.getName(), model);
        myPlayerViews = new ArrayList<>();
        //myPlayerViews.addAll(gamePieceViews);
    }

    @Override
    public void initialize() {
        myRoot = new Pane();
        myRoot.setStyle("-fx-background-color: SKYBLUE");
    }

    @Override
    public void adjustSize() {
    }

}
