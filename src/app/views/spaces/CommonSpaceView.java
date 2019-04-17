package app.views.spaces;

import app.engine.space.CommonSpace;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CommonSpaceView extends SpaceView<CommonSpace> {
    public CommonSpaceView(CommonSpace model) {
        super(model.getName(), model);
    }

    @Override
    public void initialize(){
        myRoot = new Pane();
        myRoot.setStyle("-fx-background-color: white");

    }

    @Override
    public void adjustSize() {

    }
}
