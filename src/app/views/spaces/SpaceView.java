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
import java.util.Map;

public abstract class SpaceView<M extends Space> implements ISpaceObserver {
    protected Pane myRoot;
    protected String myName;
    protected M myModel;
    protected Map<Player, ImageView> myPlayerPieces;

    public SpaceView(String name, M model) {
        myName = name;
        myModel = model;
        initialize();
        myRoot.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public abstract void initialize();
    public abstract void adjustSize();

    public Pane getMyRoot(){return myRoot;}
    public String getMyName(){return myName;}

    public void spaceUpdate() {
        StackPane playerViews = new StackPane();
        for (Player player : myModel.getCurrentOccupants()) {
            if (!(myPlayerPieces.containsKey(player))) {
                try {
                    FileInputStream inputStream = new FileInputStream(player.getPieceFile());
                    Image image = new Image(inputStream);
                    ImageView gamePiece = new ImageView(image);
                    myPlayerPieces.put(player, gamePiece);
                }
                catch (FileNotFoundException e) {
                    //DO SOMETHING HERE IDK?~?
                }
            }
            playerViews.getChildren().add(myPlayerPieces.get(player));
        }
        myRoot.getChildren().add(playerViews);
    }
}