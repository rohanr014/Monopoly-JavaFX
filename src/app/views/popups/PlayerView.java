package app.views.popups;

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

    public PlayerView(){
        super("player setting");
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
        myName.setOnAction(e->submit());
        myRoot.getChildren().add(myName);
        return myName;

    }
    private void submit(){

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
        for (TextField player: myNames){
            System.out.println(player.getCharacters().toString());
        }
        for (ComboBox<String> choice: myGamePieceChoices){
            System.out.println(choice.getValue());
        }


    }


    @Override
    protected Scene myScene(){
        myRoot = new VBox();
        setPlayerTypes();
        setSubmitButton();
        myScene = new Scene(myRoot);
        return myScene;
    }

}

