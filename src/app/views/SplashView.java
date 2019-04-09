package app.views;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


import javafx.event.EventHandler;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SplashView implements IView {
    private static final int SPLASH_WIDTH = 800;
    private static final int SPLASH_HEIGHT = 800;

    Pane myRoot;
    Scene myScene;
    HBox myButtons;
    HBox myIconContainer;
    ImageView myIcon;
    VanillaController myVanillaController;



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
        myIcon = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("Monopoly_logo.png")));
        myIconContainer.getChildren().add(myIcon);
        myIconContainer.setAlignment(Pos.CENTER);
        myRoot.getChildren().add(myIconContainer);
    }

    private Button b;

    private void setButtons(String property){//later to be done with property
        myButtons = new HBox();
        b = makeButton("start game");
        myButtons.getChildren().add();
        myButtons.getChildren().add(makeButton("Load Game", e-> loadGame()));
        myButtons.getChildren().add(makeButton("Make Game", e-> makeGame()));
        myRoot.getChildren().add(myButtons);
    }

    public void setOnStartGamePressed(EventHandler<ActionEvent> handler) { b.setOnAction(handler); }

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


    public Scene getMyScene(){
        return myScene;
    }

}
