package app.views;

import javafx.scene.Scene;


public class GameSettingView extends PopUpView{

    private final String[] PieceList = {"Vanilla/Pieces/car.png", "Vanilla/Pieces/dog.png", "Vanilla/Pieces/hat.png", "Vanilla/Pieces/iron.png", "Vanilla/Pieces/ship.png", "Vanilla/Pieces/shoe.png", "Vanilla/Pieces/thimble.png", "Vanilla/Pieces/wheelbarrow.png"};


    public GameSettingView(){
        super("game setting");

    }


    @Override
    protected Scene myScene() {
        return null;
    }
}
