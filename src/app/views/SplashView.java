package app.views;

import app.views.popups.GameSettingView;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


import javafx.event.EventHandler;

public class SplashView implements IView {
    private static final int SPLASH_WIDTH = 800;
    private static final int SPLASH_HEIGHT = 800;

    private Pane myRoot;
    private Scene myScene;
    private HBox myButtons;
    private HBox myIconContainer;
    private ImageView myIcon;

    private Button myStartGame;
    private Button myLoadGame;
    private Button myMakeGame;




    public SplashView(){
        //myVanillaController = vanilla_controller;
        myRoot = new Pane();
        setIcon();
        setScene();
        myScene = new Scene(myRoot, SPLASH_WIDTH,  SPLASH_HEIGHT);

    }

    private void setScene(){
        setButtons("placeholder");
    }

    private void setIcon(){
        myIconContainer = new HBox();
        myIcon = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("data/Vanilla/Monopoly_logo.png")));
        myIconContainer.getChildren().add(myIcon);
        myIconContainer.setAlignment(Pos.CENTER);
        myRoot.getChildren().add(myIconContainer);
    }


    private void setButtons(String property){//later to be done with property
        myButtons = new HBox();
        myStartGame = makeButton("start game");
        myLoadGame = makeButton("load game");
        myMakeGame = makeButton("make game");

        myButtons.getChildren().addAll(myStartGame, myLoadGame, myMakeGame);
        myRoot.getChildren().add(myButtons);
    }

    public void setOnStartGamePressed(EventHandler<ActionEvent> handler) { myStartGame.setOnAction(handler); }
    public void setOnLoadGamePressed(EventHandler<ActionEvent> handler){myLoadGame.setOnAction(handler);}
    public void setOnMakeGamePressed(EventHandler<ActionEvent> handler){myMakeGame.setOnAction(handler);}

    private void startGame(){
        new GameSettingView();
        System.out.println("start game pressed");
    }

    private void loadGame(){
        System.out.println("load game pressed");

    }
    private void makeGame(){
        System.out.println("make game pressed");

    }

    //hard coded for now
    private Button makeButton(String property, EventHandler<ActionEvent> handler) {
        var result = new Button();
        var label = property;
        result.setText(label);
        result.setOnAction(handler);
        return result;
    }

    private Button makeButton(String property){
        var result = new Button(property);
        return result;
    }


    public Pane getMyRoot(){
        return myRoot;
    }

    public Scene getMyScene(){return myScene;}

}
