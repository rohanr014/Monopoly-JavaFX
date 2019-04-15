package app.views.spaces;

import app.engine.space.ColorProperty;
import javafx.geometry.HPos;
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
    private Stage myDialog;
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
    private Text rentPrice;

    public DetailColorProperty(ColorProperty colorProperty) {
        myRoot = new VBox(10);
        myDialog = new Stage();
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

    public Stage getMyDialog(){
        return myDialog;
    }

    private void initialize(){
        myRoot.setPrefSize(300, 400);
        makeHeader();
        makeBody();
        myDialog.setScene(new Scene(myRoot));
        myDialog.show();
    }

    private void makeBody(){
        myGridPane = new GridPane();
        //set basic rent in center
        setRentArea();
        //set prices for each houses,
        setHouseArea();
        // set price for hotel
        setOtherArea();

        myRoot.getChildren().add(myGridPane);
    }

    private void setRentArea(){
        rentPrice = new Text("Rent "  + Double.toString(myColorProperty.getAllRent()[0]));
        myGridPane.add(rentPrice, 0, 0);
    }

    private void setHouseArea(){
        var result = new VBox();
        for(int i = 1; i<myColorProperty.getAllRent().length;i++){
            var tempHBox = houseField(i, myColorProperty.getAllRent()[i]);
            result.getChildren().add(tempHBox);
        }
        myGridPane.add(result, 2,2);

    }

    private void setOtherArea(){//do stuff here later on

    }

    private HBox houseField(int number, double rent){//code is very redundant
        var result = new HBox();
        if(number==1){
            var tempText1 = new Text("With " + number + "House ");
            var tempText2 = new Text("$ " + rent);
            result.getChildren().addAll(tempText1,tempText2);
        }else if(number==5) {
            var tempText1 = new Text("With Hotel ");
            var tempText2 = new Text("$ " + rent);
            result.getChildren().addAll(tempText1,tempText2);
        }else{
            var tempText1 = new Text("With " + number + "Houses ");
            var tempText2 = new Text("$ " + rent);
            result.getChildren().addAll(tempText1,tempText2);
        }
        return result;
    }



    private void makeHeader() {
        myStackPane = new StackPane();
        myHeader = new Rectangle();
        myHeader.setFill(Color.valueOf(myColorProperty.getMyColor()));
        myHeader.setStroke(Color.BLACK);

        myAnchorPane = new AnchorPane();
        myStackPane.getChildren().add(myAnchorPane);

        setAnchorHeader();
        textEditor();
        placeText();
        setAnchorText();

        myRoot.getChildren().add(myStackPane);
    }

    private void setAnchorText(){
        myAnchorPane.getChildren().add(myTextVBox);
        AnchorPane.setTopAnchor(myTextVBox, 25.0);
        AnchorPane.setBottomAnchor(myTextVBox, 20.0);
        AnchorPane.setRightAnchor(myTextVBox,130.0);
        AnchorPane.setLeftAnchor(myTextVBox,100.0);
    }

    private void setAnchorHeader(){
        myAnchorPane.getChildren().add(myHeader);
        AnchorPane.setTopAnchor(myHeader, 10.0);
        AnchorPane.setLeftAnchor(myHeader, 20.0);
        AnchorPane.setRightAnchor(myHeader, 20.0);
        AnchorPane.setBottomAnchor(myHeader, 10.0);
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
