package app.views.IViews;

import app.engine.agent.Agent;
import app.engine.agent.Bank;
import app.engine.agent.IAgentObserver;
import app.engine.agent.Player;
import app.engine.board.Board;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class AgentView implements IAgentObserver, IView {
    private Pane myRoot;
    private double myCash;
    private String myName;
    private int myHotelNum;
    private int myHouseNum;
    private String myPiece;

    private Agent myModel;


    public AgentView(String name,double cash,String piece){//take in correct paramaters
        myName = name;
        myCash = cash; //1500.00;
        myRoot = new Pane();
        myPiece = piece;
        setRoot();
    }


    public AgentView(Bank model){
        myModel = model;
    }

    public AgentView(Player model){
        myModel = model;
    }



    private void setRoot(){
        var tempPane = new VBox();
        var tempPane1 = new HBox();
        var tempPane2 = new HBox();
        tempPane1.getChildren().add(new Text(myName));
        tempPane1.getChildren().add(new Text("$" + Integer.toString((int) myCash) ));
        tempPane2.getChildren().add(new Text("Number of Hotels : " + Integer.toString(myHotelNum)));
        tempPane2.getChildren().add(new Text("Number of Houses : " + Integer.toString(myHouseNum)));
        tempPane.getChildren().add(tempPane1);
        tempPane.getChildren().add(tempPane2);
        tempPane.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        myRoot.getChildren().add(tempPane);

    }

    public double getMyCash() { return myCash; }

    public void setMyCash(double cash) { this.myCash = cash; }

    @Override
    public void agentUpdate(double wallet) {//why does this need board as an input?
        //change money amount

    }

    @Override
    public Pane getMyRoot(){
        return myRoot;
    }
}
