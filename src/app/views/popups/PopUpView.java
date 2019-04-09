package app.views.popups;

import javafx.scene.Scene;
import javafx.stage.Stage;

abstract class PopUpView {
    private Stage myStage;
    private String myTitle;

    public PopUpView(String title){
        myTitle = title;
        myStage = new Stage();
        myStage.setTitle(myTitle);
        myStage.setResizable(false);
        myStage.setScene(myScene());
        myStage.show();
    }

    protected abstract Scene myScene();
}
