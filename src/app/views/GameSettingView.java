package app.views;

import javafx.scene.Scene;


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
