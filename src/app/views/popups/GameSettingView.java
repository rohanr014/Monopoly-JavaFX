package app.views.popups;

import app.controller.MainController;
import app.views.popups.PopUpView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class GameSettingView extends PopUpView {
    private final static int WIDTH = 200;
    private final static int HEIGHT = 300;

    private ResourceBundle myProperties;
    private VBox myRoot;
    private ComboBox<String> myGameOptions;
    private List<String> myGameTypes = new ArrayList<>();
    private Button mySubmitButton;
    private Scene myScene;

    private MainController myMainController;

    private final String[] PieceList = {"Vanilla/Pieces/car.png", "Vanilla/Pieces/dog.png", "Vanilla/Pieces/hat.png", "Vanilla/Pieces/iron.png", "Vanilla/Pieces/ship.png", "Vanilla/Pieces/shoe.png", "Vanilla/Pieces/thimble.png", "Vanilla/Pieces/wheelbarrow.png"};


    public GameSettingView(MainController main_controller){
        super("game setting");
        myMainController = main_controller;

//        System.out.println("game setting called");
//        myProperties = ResourceBundle.getBundle("gamemodes");

    }

    private void setScene(){
        myRoot = new VBox();
        setGameTypes();
        setSubmitButton();

    }

    private void setGameTypes() {
        myGameOptions = new ComboBox();
        myGameOptions.getItems().addAll("vanilla");
        myGameOptions.setPromptText("Choose a game mode");
        myRoot.getChildren().add(myGameOptions);


    }

    private void setSubmitButton() {
        mySubmitButton = new Button("Submit");
        mySubmitButton.setOnAction(e->handleSubmit());
        myRoot.getChildren().add(mySubmitButton);
    }

    private void handleSubmit(){
        myMainController.setGameName(myGameOptions.getItems().get(0));
        System.out.println(myGameOptions.getItems().get(0));
        new PlayerView(myMainController);
        super.getMyStage().close();
    }


    @Override
    protected Scene myScene() {
        myRoot = new VBox();
        setGameTypes();
        setSubmitButton();
        myScene = new Scene(myRoot, WIDTH, HEIGHT);
        return myScene;
    }
}
