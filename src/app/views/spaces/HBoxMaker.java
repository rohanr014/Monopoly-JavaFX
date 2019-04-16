package app.views.spaces;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HBoxMaker {

    public static HBox makeHBoxSpace(String str1, String str2){
        var result = new HBox();
        var text1 = new Text(str1);
        var text2 = new Text(str2);
        result.getChildren().addAll(text1, text2);
        return result;
    }

    public static HBox makeHBoxSpace(String str1, Color color){
        var result = new HBox();
        var text1 = new Text(str1);
        result.getChildren().add(text1);
        result.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        return result;
    }
    public static HBox makeHBoxSpace(String str1, double doub){
        var result = new HBox();
        var text1 = new Text(str1);
        var text2 = new Text(Double.toString(doub));
        result.getChildren().addAll(text1,text2);
        return result;
    }

    public static HBox makeHBoxSpace(String str){
        var result = new HBox();
        var text = new Text(str);
        text.setTextOrigin(VPos.CENTER);
        result.getChildren().add(text);
        return result;
    }
}
