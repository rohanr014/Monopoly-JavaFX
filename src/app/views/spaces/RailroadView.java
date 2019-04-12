package app.views.spaces;

import javafx.geometry.VPos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RailroadView extends SpaceView {


    public RailroadView(String name){
        myName = name;

    }

    @Override
    public Pane initialize() {
        var result = new VBox();
        var text = new Text(myName);
        result.getChildren().add(text);
        text.setFill(Color.WHITE);
        text.setTextOrigin(VPos.CENTER);
        result.setStyle("-fx-background-color: PINK");
        return result;

    }

    @Override
    public void spaceUpdate() {

    }

}
