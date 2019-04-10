package app.controller;

import app.engine.board.Board;
import app.views.MainView;
import app.views.SplashView;
import app.views.popups.GameSettingView;

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
