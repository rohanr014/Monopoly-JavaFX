package app.views.spaces;

import app.engine.space.CardSpace;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class CardSpaceView extends SpaceView<CardSpace> {
    private List<ImageView> myPlayerViews;
    private ImageView myCardImageView;
    private ImageView tempCard;
    private VBox myVBox;
    private Rectangle myContainer;

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

        tempCard = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("Vanilla/Space-Photos/community-chest-space.jpg")));
        tempCard.setFitWidth(40);
        tempCard.setFitHeight(40);//need to not hard code this
        myRoot.getChildren().add(tempCard);


    }


    @Override
    public void adjustSize() {


    }

    @Override
    public void offerPopUp() {

    }
}
