package app.controller;

import app.engine.board.Board;
import app.engine.space.Space;
import app.views.IViews.MainView;
import app.views.spaces.SpaceView;

import java.util.List;

public class GameController {
    private Board myBoard;
    private MainView myMainView;

    //need to add observers from view to board
    //add space observers to space object

    //roll dice,


    public GameController(Board board, MainView mainView) {
        myBoard = board;
        myMainView = mainView;
        registerBoardObservers();
        registerSpaceObservers();
    }


    public void initialize(){
        myBoard.startTurn();

    }


    private void registerSpaceObservers(){
        List<SpaceView> temp = myMainView.getMyVanillaBoardView().getMySpaceViews();
        myBoard.getSpaces().forEach(space -> {
            for (SpaceView spaceView : temp) {
                space.addSpaceObserver(spaceView);
            }
        });
        for(SpaceView spaceView : temp){
            spaceView.spaceUpdate();
        }

    }


    private void registerBoardObservers(){
        myBoard.addBoardObserver(myMainView.getMyVanillaBoardView());
        myBoard.addBoardObserver(myMainView.getMyControlView());

    }


}
