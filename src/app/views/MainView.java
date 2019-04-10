package app.views;


import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;



public class MainView{
    BorderPane myRoot;
    Scene myScene;

    public MainView(){
        myRoot = new BorderPane();
        setComponents();
        myScene = new Scene(myRoot, 1000,1000);
    }

    private void setComponents(){
        myRoot.setCenter(new VanillaBoardView().getMyRoot());
        myRoot.setRight(new AgentView().getMyRoot());
        myRoot.setTop(new ControlView().getMyRoot());
        myRoot.setBottom(new LogHistoryView().getMyRoot());
    }




    public Scene getMyScene(){
        return myScene;
    }
}
