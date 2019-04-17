package app.views.IViews;


import app.engine.board.Board;
import app.engine.board.IBoardObserver;
import app.engine.dice.IDiceObserver;
import app.engine.space.Space;
import app.views.utility.ButtonMaker;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ResourceBundle;


public class ControlView implements IView, IDiceObserver, IBoardObserver {
    private static final int DISPLAY_RADIUS = 10;
    private static final Color DISPLAY_COLOR = Color.BEIGE;

    private Pane myRoot;
    private Board myBoard;
    private Text myDiceText;
    private int diceValue;


    private Button mySellButton;
    private Button myMortgageButton;
    private Button myUnmortgageButton;
    private Button myRollDiceButton;
    private Button myEndTurnButton;
    private StackPane myDiceDisplay;
    private Circle myDiceContainer;

    private ResourceBundle myResources;



    public ControlView(Board board){
        myBoard = board;
        myRoot = new Pane();
        diceValue = 0;
        setRoot();
    }

    private void setRoot(){
        var tempPane = new HBox();
        mySellButton = ButtonMaker.makeButton("Sell", e->pressedSell());
        myMortgageButton = ButtonMaker.makeButton("Mortgage", e->pressedMortgage());
        myEndTurnButton = ButtonMaker.makeButton("End Turn", e-> pressedEndTurn());
        myUnmortgageButton = ButtonMaker.makeButton("Unmortgage",e->pressedUnmortgage());
        myRollDiceButton = ButtonMaker.makeButton("Roll Dice", e-> rollDice());
        tempPane.getChildren().addAll(mySellButton, myMortgageButton, myUnmortgageButton,  myEndTurnButton, myRollDiceButton, makeDiceDisplay());
        myRoot.getChildren().add(tempPane);
    }

    private StackPane makeDiceDisplay(){
        myDiceDisplay = new StackPane();
        myDiceContainer = new Circle(DISPLAY_RADIUS, DISPLAY_COLOR);
        myDiceText = new Text(Integer.toString(diceValue));
        myDiceText.setFont(new Font(20));
        myDiceDisplay.getChildren().addAll(myDiceContainer,myDiceText);

        return myDiceDisplay;

    }

    private void pressedSell(){


    }

    private void pressedEndTurn(){
        myBoard.endTurn();
        System.out.println("pressed End Turn");
    }

    private void pressedMortgage(){


    }

    private void pressedUnmortgage(){

    }

    private void rollDice(){
        myBoard.rollDice(myBoard.getCurrentPlayer());

    }




    @Override
    public Pane getMyRoot() {
        return myRoot;
    }

    @Override
    public void diceUpdate(int[] dice_value) {
        myDiceDisplay.getChildren().remove(myDiceText);
        int i = 0;
        for(int num : dice_value){
            System.out.println("came into forloop");
            i = i + num;
        }

        diceValue = i;
        resetDiceValue();
        //myBoard.move(myBoard.getCurrentPlayer(),diceValue);//need to check if its best to do it this way

    }

    private void resetDiceValue(){
        myDiceText = null;
        myDiceText = new Text(Integer.toString(diceValue));
        myDiceText.setFont(new Font(20));
        myDiceDisplay.getChildren().add(myDiceText);

    }


    @Override
    public void boardUpdate() {

    }

    @Override
    public void boardUpdate(Space start, Space end) {

    }

}
