package app.views.IViews;


import app.engine.board.Board;
import app.engine.space.ColorProperty;
import app.engine.space.Space;
import app.views.spaces.ColorPropertyView;
import app.views.spaces.SpaceView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;


public class VanillaBoardView extends BoardView {
    private Pane myRoot;
    private Board myBoard;
    private List<Space> mySpaces;
    private List<ColorPropertyView> mySpaceViews;

    public VanillaBoardView(Board board){
        myRoot = new Pane();
        myRoot.setStyle("-fx-background-color: green;");
        myBoard = board;
        mySpaceViews = new ArrayList<>();
        initialize();
    }

    private void initialize(){
        mySpaces = myBoard.getSpaces();

        for(Space space : mySpaces){
            var list = space.getClass().toString().split(" ");//bad code
            var list2 = list[1].split("space.");
            var className = list2[1];
            mySpaceViews.add(new ColorPropertyView(className));
        }
        int i =0;
        for(ColorPropertyView sv : mySpaceViews){

            setLocation(i, sv.initialize());

            myRoot.getChildren().add(sv.initialize());

        }
    }

    private void setLocation(int index, Pane pane){
        if(index<10) {

            
        }
        else if(index>=10&&index<20){

        }
        else if(index>=20 && index<30){

        }
        else{

        }
    }
    private void createSpaceViews(){

    }




    @Override
    public Pane getMyRoot() {
        return myRoot;
    }
}
