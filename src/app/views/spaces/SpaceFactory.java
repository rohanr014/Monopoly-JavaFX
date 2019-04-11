package app.views.spaces;


import app.engine.board.Board;
import app.engine.space.Space;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SpaceFactory {

    private List<ImageView> myImageViewList;

    public SpaceFactory() {

    }

    public SpaceView createSpace(String className, String propertyName){
        try{
            Class cls = Class.forName("app.views.spaces." + className + "View");
            Constructor cons = cls.getConstructor(String.class);
            SpaceView result = (SpaceView) cons.newInstance(propertyName);
            System.out.println(result);
            return result;
        }catch (ClassNotFoundException | NoSuchMethodException
                | IllegalAccessException | InvocationTargetException
                | InstantiationException e){
            e.printStackTrace();
        }

        return null;
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

