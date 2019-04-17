package app.views.IViews;


import app.engine.board.Board;
import app.engine.space.Space;
import app.views.spaces.SpaceViewFactory;
import app.views.spaces.SpaceView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;


public class VanillaBoardView extends BoardView {//need to be scrolled down automatically
    private Pane myRoot;
    private Board myBoard;
    private List<Space> mySpaces;
    private List<SpaceView> mySpaceViews;
    private SpaceViewFactory mySpaceFactory;
    private LogHistoryView myLogHistoryView;

    private ImageView myBoardLogo;

    public VanillaBoardView(Board board, LogHistoryView logHistoryView){
        myRoot = new Pane();
        myRoot.setStyle("-fx-background-color: BEIGE;");
        myBoard = board;
        mySpaceViews = new ArrayList<>();
        mySpaceFactory = new SpaceViewFactory();
        myLogHistoryView = logHistoryView;
        initializeBoard();
        setLogo();
    }
    public void initialSetting(){
        mySpaceViews.get(0).spaceUpdate();
    }

    private void initializeBoard(){
        mySpaces = myBoard.getSpaces();
        loadSpacesToList();
        deploySpacesOnBoard();

    }

    private void setLogo(){
        myBoardLogo = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("Monopoly_logo.png")));
        myBoardLogo.setFitWidth(400);
        myBoardLogo.setFitHeight(100);
        myBoardLogo.setX(140);
        myBoardLogo.setY(250);
        myBoardLogo.setRotate(-45);
        myRoot.getChildren().add(myBoardLogo);
    }

    private void loadSpacesToList(){
        for(Space space : mySpaces){
            mySpaceViews.add(mySpaceFactory.createSpace(space));
        }
    }

    private void deploySpacesOnBoard(){
        double xsize = 600;
        double ysize = 600;

        int numberOfCardsOnASide = 10;

        double dx = xsize/numberOfCardsOnASide;
        double dy = ysize/numberOfCardsOnASide;

        // add all views into myRoot
        mySpaceViews.forEach(view -> myRoot.getChildren().add(view.getMyRoot()));

        // assuming rectangular board,

        // bottom
        for(int i = 0 ; i < numberOfCardsOnASide ; i ++) {
            var root = mySpaceViews.get(i).getMyRoot();
            root.setLayoutX((numberOfCardsOnASide - i) * dx);
            root.setLayoutY(numberOfCardsOnASide * dy);
            root.setPrefWidth(dx);
            root.setPrefHeight(dy);
        }

        // left
        for(int i = numberOfCardsOnASide ; i < 2*numberOfCardsOnASide ; i ++) {
            var root = mySpaceViews.get(i).getMyRoot();
            root.setLayoutX(0);
            root.setLayoutY((numberOfCardsOnASide*2-i) * dy);
            root.setPrefWidth(dx);
            root.setPrefHeight(dy);
        }

        // top
        for(int i = 2*numberOfCardsOnASide ; i < 3*numberOfCardsOnASide ; i ++) {
            var root = mySpaceViews.get(i).getMyRoot();
            root.setLayoutX((i-2*numberOfCardsOnASide) * dx);
            root.setLayoutY(0);
            root.setPrefWidth(dx);
            root.setPrefHeight(dy);
        }

        // right
        for(int i = 3*numberOfCardsOnASide ; i < 4*numberOfCardsOnASide ; i ++) {
            var root = mySpaceViews.get(i).getMyRoot();
            root.setLayoutX(numberOfCardsOnASide * dx);
            root.setLayoutY((i-3*numberOfCardsOnASide) * dy);
            root.setPrefWidth(dx);
            root.setPrefHeight(dy);
        }

        mySpaceViews.forEach(SpaceView::adjustSize);

        // vertical
    }

    public List<SpaceView> getMySpaceViews(){
        return mySpaceViews;
    }


    @Override
    public Pane getMyRoot() {
        return myRoot;
    }

    @Override
    public void boardUpdate() {


    }

    @Override
    public void boardUpdate(Space start, Space end) {
        int startInd = mySpaces.indexOf(start);
        int endInd = mySpaces.indexOf(end);
        mySpaceViews.get(startInd).spaceUpdate();
        mySpaceViews.get(endInd).spaceUpdate();
        String strPlayerName = myBoard.getCurrentPlayer().getName();
        myLogHistoryView.addMovementLog(strPlayerName + " moved from " + myBoard.getSpaces().get(startInd).getName() + " to " + myBoard.getSpaces().get(endInd).getName() + ".");
        //System.out.println("Start index: " + startInd + ", End index: " + endInd);

    }

    public void logPurchase(String transaction) {
        myLogHistoryView.addTransactionLog(transaction);
    }


    @Override
    public void boardUpdate(Board board) {

    }

}
