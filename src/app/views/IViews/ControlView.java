package app.views.IViews;


import app.engine.board.Board;
import app.engine.board.IBoardObserver;
import app.engine.dice.IDiceObserver;
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
        myUnmortgageButton = ButtonMaker.makeButton("Unmortgage",e->pressedUnmortgage());
        myRollDiceButton = ButtonMaker.makeButton("Roll Dice", e-> rollDice());
        tempPane.getChildren().addAll(mySellButton, myMortgageButton, myUnmortgageButton, myRollDiceButton, makeDiceDisplay());
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
        System.out.println("pressed Sell");

    }

    private void pressedBuy(){
        System.out.println("pressed Buy");

    }

    private void pressedMortgage(){
        System.out.println("pressed Mortgage");


    }

    private void pressedUnmortgage(){
        System.out.println("Pressed unmortgage");

    }

    private void rollDice(){
        myBoard.rollDice(myBoard.getCurrentPlayer());

        System.out.println("pressed rolled dice");
    }




    @Override
    public Pane getMyRoot() {
        return myRoot;
    }

    @Override
    public void diceUpdate(int[] dice_value) {
        int i = 0;
        for(int num : dice_value){
            i = i + num;
        }
        this.diceValue = i;

    }

    public void boardUpdate() {
        diceValue = this.myBoard.getLastRollSum();
    }

    @Override
    public void boardUpdate(Board board) {

    }
}
