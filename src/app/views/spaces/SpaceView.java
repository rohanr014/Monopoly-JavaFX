package app.views.spaces;

import app.engine.agent.Player;
import app.engine.space.ISpaceObserver;
import app.engine.space.Space;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public abstract class SpaceView<M extends Space> implements ISpaceObserver {
    protected Pane myRoot;
    protected String myName;
    protected M myModel;
    protected Map<Player, ImageView> myPlayerPieces;
    private StackPane playerViews;

    public SpaceView(String name, M model) {
        myName = name;
        myModel = model;
        myPlayerPieces = new HashMap<>();
        playerViews = new StackPane();
        initialize();
        myRoot.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public abstract void initialize();
    public abstract void adjustSize();

    public Pane getMyRoot(){return myRoot;}
    public String getMyName(){return myName;}

    public void spaceUpdate() {
        //player.getChildren().remove();
        myRoot.getChildren().remove(playerViews);
        playerViews = new StackPane();
//        for (Player player : myPlayerPieces.keySet()) {
//            if (!(myModel.getCurrentOccupants().contains(player)) && playerViews.getChildren().contains(myPlayerPieces.get(player))) {
//                playerViews.getChildren().remove(myPlayerPieces.get(player));
//            }
//        }
        for (Player player : myModel.getCurrentOccupants()) {
            if (!(myPlayerPieces.containsKey(player))) {
                ImageView gamePiece = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("car.png"), 40, 40, false, false));
                myPlayerPieces.put(player, gamePiece);
            }
            if (!(playerViews.getChildren().contains(myPlayerPieces.get(player)))) {
                playerViews.getChildren().add(myPlayerPieces.get(player));
            }
        }
        myRoot.getChildren().add(playerViews);
    }
}