package app.views.IViews;


import app.engine.board.Board;
import app.engine.space.ColorProperty;
import app.engine.space.Space;
import app.views.spaces.ColorPropertyView;
import app.views.spaces.SpaceFactory;
import app.views.spaces.SpaceView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;


public class VanillaBoardView extends BoardView {
    private Pane myRoot;
    private Board myBoard;
    private List<Space> mySpaces;
    private List<SpaceView> mySpaceViews;
    private SpaceFactory mySpaceFactory;

    public VanillaBoardView(Board board){
        myRoot = new Pane();

        myBoard = board;
        mySpaceViews = new ArrayList<>();
        mySpaceFactory = new SpaceFactory();
        initialize();
    }

    private void initialize(){
        mySpaces = myBoard.getSpaces();
        loadSpacesToList();

//        int i =0;
//        for(ColorPropertyView sv : mySpaceViews){
//
//            setLocation(i, sv.initialize());
//            i++;
//            myRoot.getChildren().add(sv.initialize());
//
//        }
    }

    private void loadSpacesToList(){
        for(Space space : mySpaces ){
            var list = space.getClass().toString().split(" ");//bad code
            var list2 = list[1].split("space.");
            var className = list2[1];
            //mySpaceFactory.createSpace(className, space.getClass().getName())
            //mySpaceViews.add();
        }

    }

    private void setLocation(int index, Pane pane){
        if(index<10) {
            pane.setLayoutX(index * 50);
            pane.setLayoutY(800);

        }
        else if(index>=10&&index<20){
            pane.setLayoutX(100);
            pane.setLayoutY(index * 50);

        }
        else if(index>=20 && index<30){
            pane.setLayoutX(index * 50);
            pane.setLayoutY(100);

        }
        else{
            pane.setLayoutX(500);
            pane.setLayoutY(index*50);

        }
    }
    private void createSpaceViews(){

    }




    @Override
    public Pane getMyRoot() {
        return myRoot;
    }
}
