package app.views.spaces;


import app.engine.board.Board;
import app.engine.space.Space;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class SpaceFactory {

    private List<ImageView> myImageViewList;

    public SpaceFactory() {

    }

    public SpaceView createSpace(Board board) {
        List<Space> spaceList = board.getSpaces();
        for(Space space : spaceList){
            //if(space.)
        }
        return null;

    }

    private SpaceView makeCorner() {
        return new SpaceView() {
            @Override
            public Pane initialize() {

                return null;
            }

            @Override
            public void spaceUpdate() {

            }
        };
    }
}

