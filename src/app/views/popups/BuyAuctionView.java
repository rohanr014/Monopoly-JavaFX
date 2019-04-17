package app.views.popups;

import app.views.utility.ButtonMaker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class BuyAuctionView extends PopUpView {
    private HBox myRoot;
    private Scene myScene;
    private AnchorPane myAnchor1;
    private AnchorPane myAnchor2;
    private Button myBuyButton;
    private Button myAutctionButton;



    public BuyAuctionView(String name){
        super(name);
    }


    @Override
    protected Scene myScene() {
        myRoot = new HBox();
        myAnchor1 = new AnchorPane();
        myAnchor2 = new AnchorPane();
        myBuyButton =  ButtonMaker.makeButton("Buy", e->pressedBuy());
        myAutctionButton = ButtonMaker.makeButton("Auction", e->pressedAuction());
        myAnchor1.getChildren().add(myBuyButton);
        myAnchor2.getChildren().add(myAutctionButton);
        myRoot.getChildren().addAll(myAnchor1, myAnchor2);
        return new Scene(myRoot, Color.DARKGREEN);
    }

    private void pressedBuy(){

    }
    private void pressedAuction(){

    }
}
