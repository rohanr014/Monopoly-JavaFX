package app.views.spaces;

import app.engine.space.ColorProperty;
import app.engine.space.Space;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DetailColorProperty {
    private Stage myStage;
    private Pane myRoot;
    private ColorProperty mySpace;
    private String myName;
    private Rectangle myHeader;
    private AnchorPane myAnchorPane;
    private Line myDivider;


    public DetailColorProperty(ColorProperty space) {
        myRoot = new Pane();
        myStage = new Stage();
        myDivider = new Line();
        mySpace = space;
        myName = mySpace.getName();
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
        myHeader = new Rectangle();
        System.out.println(mySpace.getMyColor());
        myHeader.setFill(Color.valueOf(mySpace.getMyColor()));
        myAnchorPane = new AnchorPane();
        myAnchorPane.getChildren().add(myHeader);
        myAnchorPane.setTopAnchor(myHeader, 10.0);
        myAnchorPane.setLeftAnchor(myHeader, 20.0);
        myAnchorPane.setRightAnchor(myHeader, 20.0);
        myAnchorPane.setBottomAnchor(myHeader, 10.0);




        myRoot.getChildren().add(myAnchorPane);

        myStage.setScene(new Scene(myRoot));
        myStage.show();

    }


}
