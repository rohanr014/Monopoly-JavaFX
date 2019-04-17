package app.views.IViews;

import app.controller.MainController;
import app.views.utility.ButtonMaker;
import app.views.popups.GameSettingView;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;


public class SplashView implements IView {
    private static final int SPLASH_WIDTH = 800;
    private static final int SPLASH_HEIGHT = 800;

    private GridPane myRoot;
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
        myRoot = new GridPane();
        myRoot.setPadding(new Insets(200,200,200,220));
        myRoot.setVgap(20);
        myRoot.setHgap(10);
        setIcon();
        setScene();
        myScene = new Scene(myRoot, SPLASH_WIDTH,  SPLASH_HEIGHT);
        myScene.getStylesheets().add(getClass().getResource("/splashScreen.css").toExternalForm());

        VBox all = new VBox();

        HBox icon = new HBox();
        icon.getChildren().add(myIcon);
        icon.setAlignment(Pos.CENTER);

        HBox buttons = new HBox();
        buttons.getChildren().add(myButtons);
        buttons.setAlignment(Pos.CENTER);

        all.getChildren().addAll(icon, buttons);
        all.setAlignment(Pos.CENTER);

//        myRoot.getChildren().add(myIcon);
//        myRoot.getChildren().add(myButtons);
        //GridPane.setHalignment(all, HPos.CENTER);
        //GridPane.setHalignment(all, HPos.CENTER);
        myRoot.getChildren().add(all);
        myRoot.setAlignment(Pos.CENTER);

    }

    private void setScene(){
        setButtons("placeholder");
    }

    private void setIcon(){
        myIcon = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("Monopoly_logo.png")));
//        GridPane.setConstraints(myIcon,1,0);
//        GridPane.setColumnSpan(myIcon,3);
        //GridPane.setHalignment(myIcon, HPos.CENTER);
        //myRoot.getChildren().add(myIcon);
    }


    private void setButtons(String property){//later to be done with property
        myButtons = new HBox();
        myStartGame = ButtonMaker.makeButton("start game", e->this.startGame());
        myLoadGame = ButtonMaker.makeButton("load game", e->this.loadGame());
        myMakeGame = ButtonMaker.makeButton("make game", e->this.makeGame());
        GridPane.setConstraints(myStartGame,1,2);
        GridPane.setConstraints(myLoadGame,2,2);
        GridPane.setConstraints(myMakeGame,3,2);
        myButtons.getChildren().addAll(myStartGame,myLoadGame,myMakeGame);
        //myRoot.getChildren().add(myButtons);
    }


    private void startGame(){
        new GameSettingView(myMainController);

    }

    private void loadGame(){


    }
    private void makeGame(){


    }



    public Pane getMyRoot(){
        return myRoot;
    }

    public Scene getMyScene(){return myScene;}

}
