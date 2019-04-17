package app.controller;

import app.engine.Config.GameFileHandler;
import app.engine.board.Board;
import app.views.IViews.MainView;
import app.views.IViews.SplashView;
import app.views.spaces.ColorPropertyView;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
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
        myStage.setTitle("Monopoly");
        myStage.setResizable(false);

        //myStage.setScene(new Scene(CPV.initialize(), 100, 130, Color.CYAN));
        myStage.setScene(mySplashView.getMyScene());
        myStage.show();
    }

    public void setGameName(String gameMode){
        myGameMode = gameMode;
    }

    public void setPlayerInfo(ArrayList<String> playerNames, ArrayList<String> playerPieces ){
        myPlayerNames = playerNames;
        myPlayerPieces = playerPieces;
        setGame();//maybe this should not be in this function
    }

    private void setGame(){
        ResourceBundle bundle =
                GameFileHandler.createPropertiesFile(
                        "Vanilla","Game1", myPlayerNames, myPlayerPieces, myGameMode,"vanillaRules");
        myBoard = new Board(bundle);
        myMainView = new MainView(myBoard);
        myGameController = new GameController(myBoard, myMainView);
        myGameController.initialize();
        myStage.close();
        myStage.setTitle("Game_screen_replaced");
        myStage.setScene(myMainView.getMyScene());
        myStage.show();
    }

    public SplashView splashView() { return mySplashView; }


}
