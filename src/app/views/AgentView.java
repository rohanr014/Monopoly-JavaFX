package app.views;

import app.engine.agent.IAgentObserver;
import app.engine.board.Board;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class AgentView implements IAgentObserver, IView {
    private Pane myRoot;
    private double myWallet;
    private String myName;
    private int myHotelNum;
    private int myHouseNum;


    public AgentView(String name){
        myName = name;
        myWallet = 1500.00;
        myRoot = new Pane();
        setRoot();
    }

    private void setRoot(){
        var tempPane = new VBox();
        var tempPane1 = new HBox();
        var tempPane2 = new HBox();
        tempPane1.getChildren().add(new Text(myName));
        tempPane1.getChildren().add(new Text("$" + Integer.toString((int) myWallet) ));
        tempPane2.getChildren().add(new Text("Number of Hotels : " + Integer.toString(myHotelNum)));
        tempPane2.getChildren().add(new Text("Number of Houses : " + Integer.toString(myHouseNum)));
        tempPane.getChildren().add(tempPane1);
        tempPane.getChildren().add(tempPane2);
        tempPane.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        myRoot.getChildren().add(tempPane);

    }


    @Override
    public void agentUpdate(double wallet, Board board) {//why does this need board as an input?

    }

    @Override
    public Pane getMyRoot(){
        return myRoot;
    }
}
