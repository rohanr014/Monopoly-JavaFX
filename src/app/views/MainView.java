package app.views;


import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;



public class MainView{
    BorderPane myRoot;
    Scene myScene;

    public MainView(){
        myRoot = new BorderPane();
        myScene = new Scene(myRoot, 1000,1000);
    }



    public Scene getMyScene(){
        return myScene;
    }
}
