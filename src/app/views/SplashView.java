package app.views;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class SplashView implements IView {
    Pane myRoot;
    Scene myScene;

    public SplashView(){
        myRoot = new Pane();
        myScene = new Scene(myRoot, 600, 600, Color.AQUA);

    }

    

    public Scene getMyScene(){
        return myScene;
    }

}
