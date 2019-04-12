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
//        double height = myRoot.getBoundsInParent().getHeight();
//        double width = myRoot.getBoundsInParent().getWidth();
        double x0 =0;
        double y0 = 0;
        double xsize=800;
        double ysize = 800;
        double fractionNotSpaces = 0.2;
        double x = x0+xsize-(((1.0-fractionNotSpaces)/2)*xsize);
        double y = y0+ysize-(((1-fractionNotSpaces)/2)*ysize);
        for (int i=0; i<mySpaceViews.size();i++) {
//            System.out.println(i+": "+Double.toString(x)+","+Double.toString(y));
            var temp = mySpaceViews.get(i).initialize();
            temp.setLayoutX(x);
            temp.setLayoutY(y);
            myRoot.getChildren().add(temp);

            if (left(i)) {
//                System.out.println("left");
                x=x-((xsize-(((1-fractionNotSpaces)/2)*xsize))/(mySpaceViews.size()/4));
            }
            else if (up(i)) {
//                System.out.println("up");
                y=y-((ysize-(((1-fractionNotSpaces)/2)*ysize))/(mySpaceViews.size()/4));
            }
            else if (right(i)) {
//                System.out.println("right");
                x=x+((xsize-(((1-fractionNotSpaces)/2)*xsize))/(mySpaceViews.size()/4));
            }
            else if (down(i)) {
//                System.out.println("down");
                y=y+((ysize-(((1-fractionNotSpaces)/2)*ysize))/(mySpaceViews.size()/4));
            }
            System.out.println("");
        }
        //        else{
//            pane.setLayoutX(600);
//            pane.setLayoutY(index*50);
//
//        }
//        myRoot.getChildren().add(pane);
    }

    private boolean left(int index) {
        return (index>=0 && index<((mySpaceViews.size()-4)/4)+1);
    }

    private boolean up(int index) {
        return (index>=((mySpaceViews.size()-4)/4)+1 && index<(((mySpaceViews.size()-4)/4)+1)*2);
    }

    private boolean right(int index) {
        return (index>=(((mySpaceViews.size()-4)/4)+1)*2 && index<(((mySpaceViews.size()-4)/4)+1)*3);
    }

    private boolean down(int index) {
        return (index>=(((mySpaceViews.size()-4)/4)+1)*3 && index<(((mySpaceViews.size()-4)/4)+1)*4);

    }

//    private void deploySpacesOnBoard(){
//        for(int i =0; i<mySpaceViews.size();i++){
//            //need to fill in here
//            var temp = mySpaceViews.get(i).initialize();
//            setLocation(i, temp);
//
//
//        }
//    }
//
//    private void setLocation(int index, Pane pane){
//        if(index<10) {
//            pane.setLayoutX(600-((index) * 50));
//            pane.setLayoutY(600);
//
//        }
//        else if(index>=10&&index<20){
//            pane.setLayoutX(10);
//            pane.setLayoutY(600-(index * 50));
//
//        }
//        else if(index>=20 && index<30){
//            pane.setLayoutX(index * 50);
//            pane.setLayoutY(10);
//
//        }
//        else{
//            pane.setLayoutX(600);
//            pane.setLayoutY(index*50);
//
//        }
//        myRoot.getChildren().add(pane);
//    }
    private void createSpaceViews(){

    }




    @Override
    public Pane getMyRoot() {
        return myRoot;
    }
}
