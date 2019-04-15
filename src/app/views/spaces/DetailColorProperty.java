package app.views.spaces;

import app.engine.space.ColorProperty;
import app.engine.space.Space;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DetailColorProperty {
    private Stage myStage;
    private Pane myRoot;
    private ColorProperty myColorProperty;
    private String myName;
    private Rectangle myHeader;
    private AnchorPane myAnchorPane;
    private Line myDivider;
    private StackPane myStackPane;
    private VBox myTextVBox;

    private GridPane myGridPane;

    private ArrayList<Text> myPropName;



    public DetailColorProperty(ColorProperty colorProperty) {
        myRoot = new Pane();
        myStage = new Stage();
        myDivider = new Line();
        myColorProperty = colorProperty;
        myName = myColorProperty.getName();
        initialize();
    }

    public void adjustSize(){
        myHeader.setHeight(myRoot.getHeight()/4);
        myHeader.setWidth(myRoot.getWidth()-40);

        myDivider.setStartY(0);
        myDivider.setStartY(myRoot.getHeight()/4 + 5);

        myDivider.setEndX(myRoot.getWidth()-20);
        myDivider.setEndY(myRoot.getHeight()/4 + 5);

    }

    public Stage getMyStage(){
        return myStage;
    }

    private void initialize(){
        myRoot.setPrefSize(300, 400);

        makeHeader();
        makeBody();
        myStage.setScene(new Scene(myRoot));
        myStage.show();

    }

    private void makeBody(){
        myGridPane = new GridPane();
        
        //set basic rent in center
        setRentArea();
        //set prices for each houses,
        setHouseArea();
        // set price for hotel
        setHotelArea();

    }

    private void setRentArea(){

    }

    private void setHouseArea(){

    }

    private void setHotelArea(){

    }

    private void makeHeader(){

        myStackPane = new StackPane();
        myHeader = new Rectangle();
        myHeader.setFill(Color.valueOf(myColorProperty.getMyColor()));
        myHeader.setStroke(Color.BLACK);

        setAnchorHeader();
        textEditor();
        placeText();
        setAnchorText();

        myRoot.getChildren().add(myStackPane);

    }

    private void setAnchorText(){
        myAnchorPane.getChildren().add(myTextVBox);
        myAnchorPane.setTopAnchor(myTextVBox, 25.0);
        myAnchorPane.setBottomAnchor(myTextVBox, 20.0);
        myAnchorPane.setRightAnchor(myTextVBox,130.0);
        myAnchorPane.setLeftAnchor(myTextVBox,100.0);
    }

    private void setAnchorHeader(){
        myAnchorPane = new AnchorPane();
        myAnchorPane.getChildren().add(myHeader);
        myAnchorPane.setTopAnchor(myHeader, 10.0);
        myAnchorPane.setLeftAnchor(myHeader, 20.0);
        myAnchorPane.setRightAnchor(myHeader, 20.0);
        myAnchorPane.setBottomAnchor(myHeader, 10.0);
        myStackPane.getChildren().add(myAnchorPane);
    }

    private void placeText(){
        myTextVBox = new VBox();
        if(myPropName.size()==1){
            myTextVBox.getChildren().add(myPropName.get(0));
        }else if(myPropName.size()==2){
            myTextVBox.getChildren().addAll(myPropName.get(0), myPropName.get(1));
        }else{//need to do something about this really, idk make the font small or something
            System.out.println("too long of prop name");
        }
        myStackPane.getChildren().add(myTextVBox);
    }

    private void textEditor(){
        myPropName = new ArrayList<>();
        String[] temp = myName.split(" ");
        if(temp.length>2){//this part might need to be reconsidered
            Text t1 = new Text(temp[0] + " " + temp[1]);
            Text t2 = new Text(temp[2]);
            setText(t1, t2);
        }
        else if(temp.length<=2){
            Text t1 = new Text(temp[0]);
            Text t2 = new Text(temp[1]);
            setText(t1, t2);

        }else{
            System.out.println("Too long of a name");
        }
    }

    private void setText(Text one, Text two){
        one.setFont(new Font(30));
        two.setFont(new Font(30));
        myPropName.add(one);
        myPropName.add(two);

    }


}
