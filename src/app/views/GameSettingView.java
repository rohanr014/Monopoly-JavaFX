package app.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameSettingView extends PopUpView{

    private final String[] PieceList = {"car.png", "dog.png", "hat.png", "iron.png", "ship.png", "shoe.png", "thimble.png", "wheelbarrow.png"};


    public GameSettingView(){
        super("game setting");

    }


    @Override
    protected Scene myScene() {
        return null;
    }
}
