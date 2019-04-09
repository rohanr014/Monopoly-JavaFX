package app.views;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

public class SplashView implements IView {
    public static final int SPLASH_WIDTH = 800;
    public static final int SPLASH_HEIGHT = 800;

    Pane myRoot;
    Scene myScene;
    HBox myButtons;
    //VanillaController myVanillaController;



    public SplashView(){
        //myVanillaController = vanilla_controller;
        myRoot = new Pane();
        myButtons = new HBox();
        setScene();
        myScene = new Scene(myRoot, SPLASH_WIDTH,  SPLASH_HEIGHT);

    }

    private void setScene(){
        setButtons("placeholder");
    }

    private void setButtons(String property){//later to be done with property
        myButtons.getChildren().add(makeButton("start game", e-> startGame()));
        myButtons.getChildren().add(makeButton("Load Game", e-> loadGame()));
        myButtons.getChildren().add(makeButton("Make Game", e-> makeGame()));
        myRoot.getChildren().add(myButtons);
    }



    private void startGame(){
    }

    private void loadGame(){

    }
    private void makeGame(){

    }

    //hard coded for now
    private Button makeButton(String property, EventHandler<ActionEvent> handler) {
        var result = new Button();
        var label = property;
        result.setText(label);
        result.setOnAction(handler);
        return result;
    }


    public Scene getMyScene(){
        return myScene;
    }

}
