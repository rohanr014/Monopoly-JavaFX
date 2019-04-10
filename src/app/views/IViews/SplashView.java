package app.views.IViews;

import app.controller.MainController;
import app.views.utility.ButtonMaker;
import app.views.popups.GameSettingView;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;



public class SplashView implements IView {
    private static final int SPLASH_WIDTH = 800;
    private static final int SPLASH_HEIGHT = 800;

    private ButtonMaker myButtonMaker;

    private Pane myRoot;
    private Scene myScene;
    private HBox myButtons;
    private HBox myIconContainer;
    private ImageView myIcon;

    private Button myStartGame;
    private Button myLoadGame;
    private Button myMakeGame;

    private MainController myMainController;

    public SplashView(MainController mainController){
        myMainController = mainController;
        myRoot = new Pane();
        myButtonMaker = new ButtonMaker();
        setIcon();
        setScene();
        myScene = new Scene(myRoot, SPLASH_WIDTH,  SPLASH_HEIGHT);

    }

    private void setScene(){
        setButtons("placeholder");
    }

    private void setIcon(){
        myIconContainer = new HBox();
        myIcon = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("Monopoly_logo.png")));
        myIconContainer.getChildren().add(myIcon);
        myIconContainer.setAlignment(Pos.CENTER);
        myRoot.getChildren().add(myIconContainer);
    }


    private void setButtons(String property){//later to be done with property
        myButtons = new HBox();
        myStartGame = myButtonMaker.makeButton("start game", e->this.startGame());
        myLoadGame = myButtonMaker.makeButton("load game", e->this.loadGame());
        myMakeGame = myButtonMaker.makeButton("make game", e->this.makeGame());
        myButtons.getChildren().addAll(myStartGame, myLoadGame, myMakeGame);
        myRoot.getChildren().add(myButtons);
    }


    private void startGame(){
        new GameSettingView(myMainController);
        System.out.println("start game pressed");
    }

    private void loadGame(){
        System.out.println("load game pressed");

    }
    private void makeGame(){
        System.out.println("make game pressed");

    }



    public Pane getMyRoot(){
        return myRoot;
    }

    public Scene getMyScene(){return myScene;}

}
