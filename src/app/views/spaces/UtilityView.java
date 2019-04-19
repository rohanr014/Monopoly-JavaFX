package app.views.spaces;

import app.engine.space.Utility;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class UtilityView extends PropertyView<Utility> {
    private List<ImageView> myPlayerViews;
    ImageView myUtilityImageView;

    public UtilityView(Utility model) {
        super(model);
        myPlayerViews = new ArrayList<>();
        //myPlayerViews.addAll(gamePieceViews);
    }

    @Override
    public void initialize() {
        System.out.println("(U) Got to: " + this.getMyName());
        myRoot = new StackPane();
        //myRoot.setStyle("-fx-background-color: SKYBLUE");
        myUtilityImageView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(myModel.getImageName())));
        myUtilityImageView.setFitHeight(40);
        myUtilityImageView.setFitWidth(40);
        myRoot.getChildren().add(myUtilityImageView);
        myRoot.setStyle("-fx-background-color: white");
    }

    @Override
    public void adjustSize() {
    }

}
