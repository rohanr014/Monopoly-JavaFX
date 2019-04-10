package app.views.IViews;


import app.engine.board.Board;
import app.engine.dice.IDiceObserver;
import app.views.utility.ButtonMaker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ResourceBundle;


public class ControlView implements IView, IDiceObserver {
    private Pane myRoot;
    private Board myBoard;

    private ResourceBundle myResources;

    private int diceValue;

    public ControlView(Board board){
        myBoard = board;
        myRoot = new Pane();
        diceValue = 0;
        setRoot();
    }

    private void setRoot(){
        var tempPane = new HBox();
        tempPane.getChildren().add(ButtonMaker.makeButton("Sell", e->pressedSell()));
        tempPane.getChildren().add(ButtonMaker.makeButton("Buy", e->pressedBuy()));
        tempPane.getChildren().add(ButtonMaker.makeButton("Mortgage", e->pressedMortgage()));
        tempPane.getChildren().add(ButtonMaker.makeButton("Unmortgage",e->pressedUnmortgage()));
        tempPane.getChildren().add(ButtonMaker.makeButton("Roll Dice", e-> rollDice()));
        tempPane.getChildren().add(new Text(Integer.toString(diceValue)));
        myRoot.getChildren().add(tempPane);
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
}
