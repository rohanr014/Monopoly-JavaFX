package app.views.IViews;

import app.engine.agent.Agent;
import app.engine.agent.Bank;
import app.engine.agent.IAgentObserver;
import app.engine.agent.Player;
import app.engine.board.Board;
import app.views.popups.PlayerDetailsView;

import javafx.scene.control.Button;
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
    protected Player myPlayer;
    private Bank myBank;



    public AgentView(Player agent){//take in correct paramaters
        myPlayer = agent;
        initializePlayer();
    }

    public AgentView(Bank bank){//take in correct paramaters
        myBank = bank;
        initializeBank();
    }

    private void initializePlayer(){
        initialize();
        setPlayerRoot();
    }

    private void initializeBank(){
        initialize();
        setBankRoot();
    }

    private void initialize(){
        myRoot = new Pane();

    }

    private void setBankRoot(){
        var tempPane = new VBox();
        tempPane.getChildren().add(new Text("Bank"));
        tempPane.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        myRoot.getChildren().add(tempPane);
    }


    private void setPlayerRoot(){
        var tempPane = new VBox();
        var tempPane1 = new HBox();
        var tempPane2 = new HBox();
        tempPane1.getChildren().add(new Text(myPlayer.getName()));
        //add "piece: "+piece image
        tempPane1.getChildren().add(new Text("$" + Integer.toString((int) myCash) ));
        Button info = new Button();
        info.setOnAction(e-> new PlayerDetailsView(myPlayer));
        tempPane1.getChildren().add(info);
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
