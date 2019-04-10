package app.views;


import app.engine.dice.IDiceObserver;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ResourceBundle;


public class ControlView implements IView, IDiceObserver {
    private Pane myRoot;
    private ButtonMaker myButtonMaker;
    private ResourceBundle myResources;

    private int diceValue;

    public ControlView(){
        myRoot = new Pane();
        myButtonMaker = new ButtonMaker();
        diceValue = 0;
        setRoot();
    }

    private void setRoot(){
        var tempPane = new HBox();
        tempPane.getChildren().add(myButtonMaker.makeButton("Sell", e->pressedSell()));
        tempPane.getChildren().add(myButtonMaker.makeButton("Buy", e->pressedBuy()));
        tempPane.getChildren().add(myButtonMaker.makeButton("Mortgage", e->pressedMortgage()));
        tempPane.getChildren().add(myButtonMaker.makeButton("Unmortgage",e->pressedUnmortgage()));
        tempPane.getChildren().add(myButtonMaker.makeButton("Roll Dice", e-> rollDice()));
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
