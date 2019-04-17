package app.views.spaces;

import app.engine.space.Property;
import app.engine.space.Space;
import app.views.popups.BuyAuctionView;
import javafx.scene.layout.StackPane;

public class PropertyView<M extends Property> extends SpaceView<M> {
    public PropertyView(M model){
        super(model.getName(), model);
    }

    @Override
    public void initialize() {
        myRoot = new StackPane();
        myRoot.setStyle("-fx-background-color: PINK");
    }

    @Override
    public void adjustSize() {
    }

    @Override
    public void offerPopUp() {
        BuyAuctionView bav = new BuyAuctionView(myModel);
    }

    @Override
    public void addPurchaseToLog(String purchase) {

    }

}
