package app.views.spaces;

import app.engine.space.Railroad;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class RailroadView extends PropertyView<Railroad> {
    private List<ImageView> myPlayerViews;
    private ImageView myRailroadImageView;

    public RailroadView(Railroad model){
        super(model);
        myPlayerViews = new ArrayList<>();


    }

    @Override
    public void initialize() {
        myRoot = new StackPane();
        myRailroadImageView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("railroad-space.png")));
        myRailroadImageView.setFitWidth(40);
        myRailroadImageView.setFitHeight(40);//need to not hard code this
        myRoot.getChildren().add(myRailroadImageView);
        myRoot.setStyle("-fx-background-color: PINK");
    }

    @Override
    public void adjustSize() {
    }

    public String getMyName(){return myName;}
    public Pane getMyRoot(){return myRoot;}
}
