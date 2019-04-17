package app.views.popups;

import app.controller.MainController;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

abstract class PopUpView {
    private Stage myStage;
    private String myTitle;
    private Scene myScene;


    public PopUpView(String title){
        myTitle = title;
        myStage = new Stage();
        myStage.setTitle(myTitle);
        myStage.setResizable(false);
        myScene = myScene();
        myStage.setScene(myScene);
        myScene.getStylesheets().add(getClass().getResource("/popup.css").toExternalForm());
        myStage.show();
    }

    public Stage getMyStage(){
        return myStage;
    }

    protected abstract Scene myScene();
}
