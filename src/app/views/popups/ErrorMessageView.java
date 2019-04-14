package app.views.popups;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ErrorMessageView extends PopUpView {

    private VBox myRoot;
    private Scene myScene;

    public ErrorMessageView() {
        super("Error Message");
    }

    @Override
    protected Scene myScene(){
        myRoot = new VBox();
        myRoot.getChildren().add(new Text("All player names must be different and all pieces must be different "));
        myScene = new Scene(myRoot);
        return myScene;
    }


}
