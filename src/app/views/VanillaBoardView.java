package app.views;


import app.views.BoardView;
import javafx.scene.layout.Pane;

public class VanillaBoardView extends BoardView {
    private Pane myRoot;

    public VanillaBoardView(){
        myRoot = new Pane();

    }


    @Override
    public Pane getMyRoot() {
        return myRoot;
    }
}
