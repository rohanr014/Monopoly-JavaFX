package app.views.IViews;


import app.engine.board.Board;
import app.engine.space.Space;
import app.views.spaces.SpaceViewFactory;
import app.views.spaces.SpaceView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;


public class VanillaBoardView extends BoardView {
    private Pane myRoot;
    private Board myBoard;
    private List<Space> mySpaces;
    private List<SpaceView> mySpaceViews;
    private SpaceViewFactory mySpaceFactory;

    public VanillaBoardView(Board board){
        myRoot = new Pane();
        myRoot.setStyle("-fx-background-color: BEIGE;");
        myBoard = board;
        mySpaceViews = new ArrayList<>();
        mySpaceFactory = new SpaceViewFactory();
        initializeBoard();
    }

    private void initializeBoard(){
        mySpaces = myBoard.getSpaces();
        loadSpacesToList();
        deploySpacesOnBoard();

    }

    private void loadSpacesToList(){
        for(Space space : mySpaces ){
            var list = space.getClass().toString().split(" ");//bad code
            var list2 = list[1].split("space.");
            var className = list2[1];
            mySpaceViews.add(mySpaceFactory.createSpace(className, space.getName()));
        }


    }
    private void deploySpacesOnBoard(){
        for(int i =0; i<mySpaceViews.size();i++){
            //need to fill in here
            var temp = mySpaceViews.get(i).initialize();
            setLocation(i, temp);


        }
    }

    private void setLocation(int index, Pane pane){
        if(index<10) {
            pane.setLayoutX(600-((index) * 50));
            pane.setLayoutY(600);

        }
        else if(index>=10&&index<20){
            pane.setLayoutX(10);
            pane.setLayoutY(600-(index * 50));

        }
        else if(index>=20 && index<30){
            pane.setLayoutX(index * 50);
            pane.setLayoutY(10);

        }
        else{
            pane.setLayoutX(600);
            pane.setLayoutY(index*50);

        }
        myRoot.getChildren().add(pane);
    }
    private void createSpaceViews(){

    }




    @Override
    public Pane getMyRoot() {
        return myRoot;
    }
}
