package app.views.utility;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonMaker {

    public ButtonMaker(){
    }

    public Button makeButton(String property, EventHandler<ActionEvent> handler) {
        var result = new Button();
        var label = property;
        result.setText(label);
        result.setOnAction(handler);
        return result;
    }

    public Button makeButton(String property){
        var result = new Button(property);
        return result;
    }


}
