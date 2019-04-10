package app.controller;

import app.engine.Config.GameFileHandler;
import app.engine.board.Board;
import app.views.MainView;
import app.views.SplashView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController {
    private SplashView mySplashView;
    private Stage myStage;

    private String myGameMode;
    private ArrayList<String> myPlayerNames;
    private ArrayList<String> myPlayerPieces;

    private Board myBoard;
    private MainView myMainView;
    private GameController myGameController;
    private GameFileHandler myGameFileHandler;

    public MainController(Stage stage){
        mySplashView = new SplashView(this);
        myStage = stage;
        myStage.setTitle("monopoly_tobe_replaced");
        myStage.setResizable(false);
        myStage.setScene(mySplashView.getMyScene());
        myStage.show();
    }

    public void setGameName(String gameMode){
        myGameMode = gameMode;
    }

    public void setPlayerInfo(ArrayList<String> playerNames, ArrayList<String> playerPieces ){
        myPlayerNames = playerNames;
        myPlayerPieces = playerPieces;
        setGame();
    }

    private void setGame(){
        ResourceBundle bundle =
                GameFileHandler.createPropertiesFile(
                        "Vanilla","Game1", myPlayerNames, myPlayerPieces, myGameMode,"vanillaRules");
        myBoard = new Board(bundle);
        myMainView = new MainView(myBoard);
        myGameController = new GameController(myBoard, myMainView);
        myGameController.initialize();
    }

    public SplashView splashView() { return mySplashView; }


}
