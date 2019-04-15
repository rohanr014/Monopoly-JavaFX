package app.views.spaces;


import app.engine.space.Space;
import javafx.scene.image.ImageView;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SpaceViewFactory {

    private List<ImageView> myImageViewList;

    public SpaceViewFactory() {

    }

    public SpaceView createSpace(Space space){
        try{
            Class cls = Class.forName("app.views.spaces." + space.getClass().getSimpleName() + "View");
            Constructor cons = cls.getConstructors()[0];
            return (SpaceView) cons.newInstance(space);
        }catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException e){
            e.printStackTrace();
        }
        throw new RuntimeException();
    }


//    private SpaceView makeCorner() {
//        return new SpaceView() {
//            @Override
//            public Pane initialize() {
//
//                return null;
//            }
//
//            @Override
//            public void spaceUpdate() {
//
//            }
//        };
//    }
}

