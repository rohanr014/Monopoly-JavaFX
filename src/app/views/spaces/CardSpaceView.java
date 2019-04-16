package app.views.spaces;

import app.engine.space.CardSpace;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class CardSpaceView extends SpaceView<CardSpace> {
    private ImageView myCardImageView;
    private String tempCard;


    public CardSpaceView(CardSpace model){ super(model.getName(),model); }
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

    @Override
    public void spaceUpdate() {

    }
}
