package app.views.spaces;

import app.engine.space.CardSpace;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CardSpaceView extends SpaceView<CardSpace> {
    private List<ImageView> myPlayerViews;
    private String tempCard;

    public CardSpaceView(CardSpace model){
        super(model.getName(),model);
        myPlayerViews = new ArrayList<>();
        //myPlayerViews.addAll(gamePieceViews);
    }
    public String getMyName(){return myName;}
    public Pane getMyRoot(){return myRoot;}

    @Override
    public void initialize() {
        myRoot = new StackPane();
        tempCard = "c";
        myRoot.getChildren().addAll(new Text(tempCard));
    }

    @Override
    public void adjustSize() {

    }
}
