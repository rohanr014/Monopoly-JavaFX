package app.views.spaces;


import javafx.scene.image.ImageView;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SpaceViewFactory {

    private List<ImageView> myImageViewList;

    public SpaceViewFactory() {

    }

    public SpaceView createSpace(String className, String propertyName){
        try{
            System.out.println(className);
            System.out.println(propertyName);
            Class cls = Class.forName("app.views.spaces." + className + "View");
            Constructor cons = cls.getConstructor(String.class);
            SpaceView result = (SpaceView) cons.newInstance(propertyName);
            System.out.println(result.getClass().getName());
            return result;
        }catch (ClassNotFoundException | NoSuchMethodException
                | IllegalAccessException | InvocationTargetException
                | InstantiationException e){
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

