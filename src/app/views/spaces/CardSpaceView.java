package app.views.spaces;

import app.engine.space.CardSpace;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ResourceBundle;

public class CardSpaceView extends SpaceView<CardSpace> {
    private ImageView myCardImageView;
    private ImageView tempCard;
    private VBox myVBox;
    private Rectangle myContainer;


    public CardSpaceView(CardSpace model){ super(model.getName(),model); }
    public String getMyName(){return myName;}
    public Pane getMyRoot(){return myRoot;}

    @Override
    public void initialize() {
        myRoot = new StackPane();
        tempCard = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("monopoly-chance-clipart-1.jpg")));
        tempCard.setFitWidth(40);
        tempCard.setFitHeight(40);//need to not hard code this
        myRoot.getChildren().addAll(tempCard);


    }


    @Override
    public void adjustSize() {


    }

    @Override
    public void spaceUpdate() {

    }
}
