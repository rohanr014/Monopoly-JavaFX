package app.views.popups;

import app.controller.MainController;
import app.views.popups.PopUpView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class PlayerView extends PopUpView {
    private final static int WIDTH = 400;
    private final static int HEIGHT = 600;
    private VBox myRoot;
    private Scene myScene;
    private Button mySubmitButton;
    private List<TextField> myNames;
    private List<String> myGamePieceOptions;
    private List<ComboBox<String>> myGamePieceChoices;
    private TextField myName;
    private int myNumberPlayers;
    ResourceBundle myBundle;
    private ComboBox<String> myGameOptions;
    private MainController myMainController;

    public PlayerView(MainController main_controller){
        super("player setting");
        myMainController = main_controller;
        System.out.println("Player setting called");
    }

    private void setPlayerTypes(){
        myNumberPlayers = 8;
        myNames = new ArrayList<>();
        myGamePieceChoices = new ArrayList<>();
        System.out.println(Integer.toString(myNumberPlayers));
        for (int i=1;i<=myNumberPlayers;i++){
            myNames.add(setPlayerEditable());
            myGamePieceChoices.add(setGamePieceOptions());
        }
    }

    private TextField setPlayerEditable(){
        myName = new TextField();
        myRoot.getChildren().add(myName);
        return myName;

    }


    private ComboBox<String> setGamePieceOptions(){
        myGameOptions = new ComboBox<>();
        readInGamePieces();
        for (String option: myGamePieceOptions) {
            myGameOptions.getItems().add(option);
        }
        myGameOptions.setPromptText("Choose a game piece");
        myRoot.getChildren().add(myGameOptions);
        return myGameOptions;
    }

//    private void changePiece(int i){
//
//        myGamePieceChoices.set(i,myGameOptions.getValue());
//    }

    private void readInGamePieces(){
//        myBundle = ResourceBundle.getBundle("/Vanilla/vanillaRules");
//        for (String key: myBundle.keySet()){
//            myGamePieceOptions.add(myBundle.getString(key));
//        }
        myGamePieceOptions = new ArrayList<>(Arrays.asList("car","dog","hat","iron","ship","shoe","thimble","wheelbarrow"));

    }


    private void setSubmitButton() {
        mySubmitButton = new Button("Submit");
        mySubmitButton.setOnAction(e->handleSubmit());
        myRoot.getChildren().add(mySubmitButton);
    }

    private void handleSubmit(){
        ArrayList<String> tempNames = new ArrayList<>();
        ArrayList<String> tempPiece = new ArrayList<>();
        for (TextField player: myNames){
            tempNames.add(player.getCharacters().toString());
        }
        for (ComboBox<String> choice: myGamePieceChoices){
            tempPiece.add(choice.getValue());
        }

        super.getMyStage().close();
        myMainController.setPlayerInfo(tempNames, tempPiece);

    }


    @Override
    protected Scene myScene(){
        myRoot = new VBox();
        setPlayerTypes();
        setSubmitButton();
        myScene = new Scene(myRoot, WIDTH, HEIGHT);
        return myScene;
    }

}

