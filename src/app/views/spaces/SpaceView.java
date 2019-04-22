package app.views.spaces;

import app.engine.agent.Player;
import app.engine.space.ISpaceObserver;
import app.engine.space.Space;
import app.views.popups.BuyAuctionView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class SpaceView<M extends Space> implements ISpaceObserver {
    protected Pane myRoot;
    //private Pane blankRoot;
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
    //public abstract Pane initializePane(Pane pane);

    public Pane getMyRoot(){return myRoot;}
    public String getMyName(){return myName;}

    public void spaceUpdate() {
        myRoot.getChildren().removeAll(myPlayerPieces.values());
        playerViews = new StackPane();
        for (Player player : myModel.getCurrentOccupants()) {
            if (!(myPlayerPieces.containsKey(player))) {
                ImageView gamePiece = new ImageView(new Image(new File(player.getPieceFile()).toURI().toString() , 40, 40, false, false));
                myPlayerPieces.put(player, gamePiece);
            }
            playerViews.getChildren().add(myPlayerPieces.get(player));
        }
        myRoot.getChildren().addAll(playerViews.getChildren());
    }

    public void addPurchaseToLog(String purchase) {

    }
}