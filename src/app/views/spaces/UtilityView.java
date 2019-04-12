package app.views.spaces;

import app.engine.space.Utility;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UtilityView extends SpaceView {
    public UtilityView(String name){
        myName = name;
    }

    @Override
    public Pane initialize() {
        var result = new VBox();
        var text = new Text(myName);
        result.getChildren().add(text);
        result.setStyle("-fx-background-color: SKYBLUE");
        return result;
    }

    @Override
    public void spaceUpdate() {

    }
}
