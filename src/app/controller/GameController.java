package app.controller;

import app.engine.board.Board;
import app.views.IViews.MainView;

public class GameController {
    private Board myBoard;
    private MainView myMainView;




    public GameController(Board board, MainView mainView) {
        myBoard = board;
        myMainView = mainView;
    }

    public void initialize(){

    }


}
