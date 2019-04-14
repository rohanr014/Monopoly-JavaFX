package app.views.spaces;

import app.engine.space.Utility;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UtilityView extends SpaceView<Utility> {
    public UtilityView(Utility model) {
        super(model.getName(), model);
    }

    @Override
    public void initialize() {
        myRoot = new Pane();
        myRoot.setStyle("-fx-background-color: SKYBLUE");
    }

    @Override
    public void adjustSize() {
    }

    @Override
    public void spaceUpdate() {

    }
}
